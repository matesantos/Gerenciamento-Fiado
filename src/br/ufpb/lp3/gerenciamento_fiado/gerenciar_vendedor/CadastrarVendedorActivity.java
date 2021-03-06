package br.ufpb.lp3.gerenciamento_fiado.gerenciar_vendedor;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import br.ufpb.gerenciamento_fiado.URL.HttpUtils;
import br.ufpb.lp3.gerenciamento_fiado.R;
import br.ufpb.lp3.gerenciamento_fiado.menu_principal.MenuPrincipalActivity;
import br.ufpb.lp3.gerenciamento_fiado.mesagens.Mensagens;
import br.ufpb.lp3.gerenciamento_fiado.models.Endereco;
import br.ufpb.lp3.gerenciamento_fiado.models.Vendedor;
import br.ufpb.lp3.gerenciamento_fiado.persistencia_dados.vendedor.VendedorBDFactory;
import br.ufpb.lp3.gerenciamento_fiado.persistencia_dados.vendedor.VendedorDAO;
import br.ufpb.lp3.gerenciamento_fiado.tela_login.LoginActivity;
import br.ufpb.lp3.gerenciamento_fiado.utils.Utils;

public class CadastrarVendedorActivity extends Activity {
	
	private EditText nomeVendedor = null;
	private EditText rgVendedor = null;
	private EditText cpfVendedor = null;
	private EditText telefoneVendedor = null;
	private EditText loginVendedor = null;
	private EditText senhaVendedor = null;
	private EditText enderecoVendedor = null;
	private EditText numeroVendedor = null;
	private EditText cepVendedor = null;
	private EditText cidadeEdit = null;
	private EditText bairroVendedor = null;
	
	private Spinner spinnerUF = null;
	private Button cadastrarRemotamente = null;
	private Button cadastrar = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_vendedor);
		
		//SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(this);
	        
	    //boolean salvarRemotamente = preference.getBoolean(getString(R.string.salvarDadosRemotamenteKey), false);
		
		//edit text
		nomeVendedor 			= (EditText)findViewById(R.id.editTextNomeVendedorTelaCadastrarVendedor);
		rgVendedor   			= (EditText)findViewById(R.id.editTextRGVendedorTelaCadastrarVendedor);
		cpfVendedor  			= (EditText)findViewById(R.id.editTextCPFVendedorTelaCadastrarVendedor);
		telefoneVendedor 		= (EditText)findViewById(R.id.editTextTelefoneVendedorTelaCadastrarVendedor);
		loginVendedor			= (EditText)findViewById(R.id.editTextLoginVendedorTelaCadastrarVendedor);
		senhaVendedor			= (EditText)findViewById(R.id.editTextSenhaVendedorTelaCadastrarVendedor);
		enderecoVendedor		= (EditText)findViewById(R.id.editTextEnderecoVendedorTelaCadastrarVendedor);
		numeroVendedor			= (EditText)findViewById(R.id.editTextNumeroVendedorTelaCadastrarVendedor);
		cepVendedor				= (EditText)findViewById(R.id.editTextCEPVendedorTelaCadastrarVendedor);
		cidadeEdit				= (EditText)findViewById(R.id.editTextCidadeVendedorTelaCadastrarVendedor);
		bairroVendedor			= (EditText)findViewById(R.id.editTextBairroVendedorTelaCadastrarVendedor);
		

		spinnerUF 				= (Spinner) findViewById(R.id.spinnerUFTelaCadastrarVendedor);

		List<String> listUF = Utils.getUFList();
		ArrayAdapter<String> spinnerList = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, listUF);
		spinnerList
				.setDropDownViewResource(android.R.layout.simple_spinner_item);
		spinnerUF.setAdapter(spinnerList);
		spinnerUF.setOnItemSelectedListener(new SpinnerUFInfo());
		
		//button
		
		/*cadastrarRemotamente = (Button) findViewById(R.id.buttonApagarTelaCadastrarVendedor);
		cadastrarRemotamente.setOnClickListener(new salvaDadosRemotamente());*/
		/*
		if(!salvarRemotamente){
			cadastrarRemotamente.setEnabled(true);
		}else{
			cadastrarRemotamente.setEnabled(true);
		}
		*/
		cadastrar = (Button) findViewById(R.id.buttonCadastrarTelaCadastrarVendedor);
		cadastrar.setOnClickListener(new CadastrarButton());
	}
	
	private class salvaDadosRemotamente implements OnClickListener{

		@Override
		public void onClick(View v) {
		}
		
	}
	
	private class CadastrarButton implements OnClickListener{

		@Override
		public void onClick(View v) {
			
			Endereco end = new Endereco(enderecoVendedor.getText().toString(),numeroVendedor.getText().toString(),cepVendedor.getText().toString(),
					bairroVendedor.getText().toString(),cidadeEdit.getText().toString(), spinnerUF.getSelectedItem().toString());
			
			if(cadastrarVendedor(nomeVendedor.getText().toString(),telefoneVendedor.getText().toString(),rgVendedor.getText().toString(),
							  cpfVendedor.getText().toString(),end,loginVendedor.getText().toString(),senhaVendedor.getText().toString())){
				
				Utils.mostrarMensagens(CadastrarVendedorActivity.this, "Vendedor cadastrado neste dispositivo com Sucesso. Fa�a o seu login agora.");
				
				retornarTelaLogin();
				
				/*
				if(cadastrarVendedorNoServidor(nomeVendedor.getText().toString(),telefoneVendedor.getText().toString(),rgVendedor.getText().toString(),
							  cpfVendedor.getText().toString(),end,loginVendedor.getText().toString(),senhaVendedor.getText().toString())){
					
					Utils.mostrarMensagens(CadastrarVendedorActivity.this, "Vendedor cadastrado no servidor com sucesso.");
					
				}*/
				
			}
		}

	}
	
    private class SpinnerUFInfo implements OnItemSelectedListener{
    	private boolean isFirst = true;
		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			
			if(isFirst){
				isFirst = false;
			}else{
				cidadeEdit = (EditText) findViewById(R.id.editTextCidadeVendedorTelaCadastrarVendedor);
				if(isFirst){
					isFirst = false;
				}else{
					int cidade = (int) spinnerUF.getSelectedItemId(); 
					switch (cidade) {
					case 0:
						
					break;
					
					case 1:
						
					break;
					
					case 2:
						
					break;
					
					case 3:
						
					break;
					
					case 14:
						cidadeEdit.setText("JOAO PESSOA");
					break;

					default:
						
					}
				}			
				
			}
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastrar_vendor, menu);
		return true;
	}
	
	private boolean cadastrarVendedor(String nome, String telefone, String rg, String cpf,
			Endereco endereco,String login, String senha){
		
		if(validarCampos(nome, telefone, rg, cpf, endereco, login, senha) == false){;
			return false;
		}
		
		Vendedor vendedor = new Vendedor(nome, telefone, rg, cpf, endereco, login, senha);
		
		try {
			
			VendedorDAO cadastrarVendedor = VendedorBDFactory.getVendedorBD(this);

			//primeiro, vamos checar se n�o existe um outro login e senha igual na hora do cadastro
			Cursor curso = cadastrarVendedor.buscarVendedorPorLoginSenha(vendedor.getLogin(), vendedor.getSenha());
			if(curso.getCount() != 0){
				Utils.mostrarMensagens(this, Mensagens.loginSenhaExistentes);
				return false;
			}
			
			if(cadastrarVendedor.salvar(vendedor)){
				return true;
			}
			
		}catch (Exception e) {
			Log.e("Exception1", e.getMessage());
			Log.e("Exception2",Log.getStackTraceString(e.fillInStackTrace()));
		}
		
		return true;
	}

	
	private boolean cadastrarVendedorNoServidor(String nome, String telefone, String rg, String cpf,
			Endereco endereco,String login, String senha){
		
		String baseURL = "http://192.168.0.166:8080/ServerGerenciamentoFiadoV1/gerenciarVendedor";

		try {
			
				Vendedor vendedor = new Vendedor(nome, telefone, rg, cpf, endereco, login, senha);
				CadastrarVendedorInput vendedorInput = new CadastrarVendedorInput(vendedor);
				JSONObject inputStringVendedor = new JSONObject(vendedorInput.getInputMap());
				
				String jsonString = HttpUtils.urlContentPost(baseURL,"vendedor",inputStringVendedor.toString());
				
				JSONObject jsonResult = new JSONObject(jsonString);
				
				Log.i("jsonResult", jsonResult.toString());
				
				if(jsonResult.getBoolean("resposta")){
					return true;
				}
				

		}catch (IOException exe){
			Log.e("IOException1", exe.getMessage());
			Log.e("IOException2",Log.getStackTraceString(exe.fillInStackTrace()));
		}catch (JSONException json) {
			Log.e("JSONException1", json.getMessage());
			Log.e("JSONException2",Log.getStackTraceString(json.fillInStackTrace()));
		}catch (Exception e) {
			Log.e("Exception1", e.getMessage());
			Log.e("Exception2",Log.getStackTraceString(e.fillInStackTrace()));
		}
		
		return false;
	}
	
	
	private boolean validarCampos(String nome, String telefone, String rg, String cpf,
			Endereco endereco,String login, String senha){
		
		if(nome.isEmpty()){
			Utils.mostrarMensagens(this, Mensagens.campoNomeVendedorVazio);
			return false;
		}else if(rg.isEmpty()){
			Utils.mostrarMensagens(this, Mensagens.campoRGVendedorVazio);
			return false;
		}else if(cpf.isEmpty()){
			Utils.mostrarMensagens(this, Mensagens.campoCPFVendedorVazio);
			return false;
		}else if(telefone.isEmpty()){
			Utils.mostrarMensagens(this, Mensagens.campoTelefoneVendedorVazio);
			return false;
		}else if(login.isEmpty()){
			Utils.mostrarMensagens(this, Mensagens.campoLoginVendedorVazio);
			return false;
		}else if(senha.isEmpty()){
			Utils.mostrarMensagens(this, Mensagens.campoSenhaVendedorVazio);
			return false;
		}else if(endereco.getRua().isEmpty()){
			Utils.mostrarMensagens(this, Mensagens.campoRuaVendedorVazio);
			return false;
		}else if(endereco.getNumero().isEmpty()){
			Utils.mostrarMensagens(this, Mensagens.campoNumeroVendedorVazio);
			return false;
		}else if(endereco.getCep().isEmpty()){
			Utils.mostrarMensagens(this, Mensagens.campoCEPVendedorVazio);
			return false;
		}else{
			return true;
		}
		
	}
	
	private void retornarTelaLogin() {
		Utils.goToActivity(this, LoginActivity.class);
	}

}
