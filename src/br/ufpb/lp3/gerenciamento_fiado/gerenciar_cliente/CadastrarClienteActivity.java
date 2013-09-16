package br.ufpb.lp3.gerenciamento_fiado.gerenciar_cliente;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import br.ufpb.gerenciamento_fiado.URL.HttpUtils;
import br.ufpb.lp3.gerenciamento_fiado.R;
import br.ufpb.lp3.gerenciamento_fiado.mesagens.Mensagens;
import br.ufpb.lp3.gerenciamento_fiado.models.Cliente;
import br.ufpb.lp3.gerenciamento_fiado.models.Endereco;
import br.ufpb.lp3.gerenciamento_fiado.pesistencia_dados.cliente.ClienteBDFactory;
import br.ufpb.lp3.gerenciamento_fiado.pesistencia_dados.cliente.ClienteDAO;
import br.ufpb.lp3.gerenciamento_fiado.utils.Utils;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class CadastrarClienteActivity extends Activity {

	private EditText nomeCliente = null;
	private EditText rgCliente = null;
	private EditText cpfCliente = null;
	private EditText telefoneCliente = null;
	private EditText enderecoCliente = null;
	private EditText numeroCliente = null;
	private EditText cepCliente = null;
	private EditText cidadeEdit = null;
	private EditText bairroCliente = null;

	private Spinner spinnerUF = null;
	private Button apagar = null;
	private Button cadastrarCliente = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_cliente);

		// edit text
		nomeCliente 		= (EditText) findViewById(R.id.editTextNomeClienteTelaCadastrarCliente);
		rgCliente 			= (EditText) findViewById(R.id.editTextRGClienteTelaCadastrarCliente);
		cpfCliente 			= (EditText) findViewById(R.id.editTextCPFClienteTelaCadastrarCliente);
		telefoneCliente 	= (EditText) findViewById(R.id.editTextTelefoneClienteTelaCadastrarCliente);
		enderecoCliente 	= (EditText) findViewById(R.id.editTextEnderecoClienteTelaCadastrarCliente);
		numeroCliente 		= (EditText) findViewById(R.id.editTextNumeroClienteTelaCadastrarCliente);
		cepCliente 			= (EditText) findViewById(R.id.editTextCEPClienteTelaCadastrarCliente);
		cidadeEdit 			= (EditText) findViewById(R.id.editTextCidadeClienteTelaCadastrarCliente);
		bairroCliente 		= (EditText) findViewById(R.id.editTextBairroClienteTelaCadastrarCliente);
		
		
		spinnerUF = (Spinner) findViewById(R.id.spinnerUFTelaCadastrarCliente);

		List<String> listUF = Utils.getUFList();
		ArrayAdapter<String> spinnerList = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listUF);
		spinnerList.setDropDownViewResource(android.R.layout.simple_spinner_item);
		spinnerUF.setAdapter(spinnerList);
		spinnerUF.setOnItemSelectedListener(new SpinnerUFInfo());
		
		//button
		cadastrarCliente 	= (Button) findViewById(R.id.buttonCadastrarTelaCadastrarCliente);
		cadastrarCliente.setOnClickListener(new CadastrarButton());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastrar_cliente, menu);
		return true;
	}
	
	private class CadastrarButton implements OnClickListener{

		@Override
		public void onClick(View v) {
			
			Endereco end = new Endereco(enderecoCliente.getText().toString(),numeroCliente.getText().toString(),cepCliente.getText().toString(),
					spinnerUF.getSelectedItem().toString(),cidadeEdit.getText().toString(), bairroCliente.getText().toString());
			
			if(cadastrarCliente(nomeCliente.getText().toString(),telefoneCliente.getText().toString(),rgCliente.getText().toString(),
							  cpfCliente.getText().toString(),end)){
				
				Utils.mostrarMensagens(CadastrarClienteActivity.this, "Cliente cadastrado com sucesso");
				
				if(cadastrarClienteNoServidor(nomeCliente.getText().toString(),telefoneCliente.getText().toString(),rgCliente.getText().toString(),
							  cpfCliente.getText().toString(),end)){
					Utils.mostrarMensagens(CadastrarClienteActivity.this, "Cliente cadastrado no servidor com sucesso");
					
					retornarTela();
				}
				
			}
		}
		
	}
	
	private void retornarTela(){
		Utils.goToActivity(this, GerenciarClienteActivity.class);
	}

	private class SpinnerUFInfo implements OnItemSelectedListener{
    	private boolean isFirst = true;
		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			
			if(isFirst){
				isFirst = false;
			}else{
				cidadeEdit = (EditText) findViewById(R.id.editTextCidadeClienteTelaCadastrarCliente);
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
	
	private boolean cadastrarCliente(String nome, String telefone, String rg, String cpf,Endereco endereco){
		
		if(validarCampos(nome, telefone, rg, cpf, endereco) == false){;
			return false;
		}
		
		Cliente cliente = new Cliente(nome, telefone, rg, cpf, endereco);
		
		try {
			
			ClienteDAO cadastrarCliente = ClienteBDFactory.getClienteBD(this);

			if(cadastrarCliente.salvar(cliente)){
				return true;
			}
			
		}catch (Exception e) {
			Log.e("Cadastrar Cliente", e.getMessage());
			Log.e("Cadastrar Cliente2",Log.getStackTraceString(e.fillInStackTrace()));
		}
		
		return false;
	}
	
	private boolean cadastrarClienteNoServidor(String nome, String telefone, String rg, String cpf,
			Endereco endereco){
		
		String baseURL = "http://192.168.0.166:8080/ServerGerenciamentoFiadoV1/gerenciarCliente";

		try {
			
				Cliente cliente = new Cliente(nome, telefone, rg, cpf, endereco);
				CadastrarClienteInput clienteInput = new CadastrarClienteInput(cliente);
				JSONObject inputStringCliente = new JSONObject(clienteInput.getInputMap());
				
				String jsonString = HttpUtils.urlContentPost(baseURL,"cliente",inputStringCliente.toString());
				
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
			Endereco endereco){
		
		if(nome.isEmpty()){
			Utils.mostrarMensagens(this, Mensagens.campoNomeClienteVazio);
			return false;
		}else if(rg.isEmpty()){
			Utils.mostrarMensagens(this, Mensagens.campoRGClienteVazio);
			return false;
		}else if(cpf.isEmpty()){
			Utils.mostrarMensagens(this, Mensagens.campoCPFClienteVazio);
			return false;
		}else if(telefone.isEmpty()){
			Utils.mostrarMensagens(this, Mensagens.campoTelefoneClienteVazio);
			return false;
		}else if(endereco.getRua().isEmpty()){
			Utils.mostrarMensagens(this, Mensagens.campoRuaClienteVazio);
			return false;
		}else if(endereco.getNumero().isEmpty()){
			Utils.mostrarMensagens(this, Mensagens.campoNumeroClienteVazio);
			return false;
		}else if(endereco.getCep().isEmpty()){
			Utils.mostrarMensagens(this, Mensagens.campoCEPClienteVazio);
			return false;
		}else{
			return true;
		}
		
	}
	
	private void publicarNomeRedoSocial(String nome){
		
		
		
	}


}
