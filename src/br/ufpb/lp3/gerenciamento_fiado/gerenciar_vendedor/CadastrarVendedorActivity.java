package br.ufpb.lp3.gerenciamento_fiado.gerenciar_vendedor;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
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
import br.ufpb.lp3.gerenciamento_fiado.BancoDados.OperacaoCrudInterface;
import br.ufpb.lp3.gerenciamento_fiado.mesagens.Mensagens;
import br.ufpb.lp3.gerenciamento_fiado.models.Endereco;
import br.ufpb.lp3.gerenciamento_fiado.models.Vendedor;
import br.ufpb.lp3.gerenciamento_fiado.operacao_crud.OperacaoCrud;
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
	private Button apagar = null;
	private Button cadastrar = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_vendedor);
		
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
		

		spinnerUF = (Spinner) findViewById(R.id.spinnerUFTelaCadastrarVendedor);

		List<String> listColor = Utils.getUFList();
		ArrayAdapter<String> spinnerList = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, listColor);
		spinnerList
				.setDropDownViewResource(android.R.layout.simple_spinner_item);
		spinnerUF.setAdapter(spinnerList);
		spinnerUF.setOnItemSelectedListener(new SpinnerUFInfo());
		
		//button
		apagar = (Button) findViewById(R.id.buttonApagarTelaCadastrarVendedor);
		apagar.setOnClickListener(new ApagarButton());
		
		cadastrar = (Button) findViewById(R.id.buttonCadastrarTelaCadastrarVendedor);
		cadastrar.setOnClickListener(new CadastrarButton());
	}
	
	private class ApagarButton implements OnClickListener{

		@Override
		public void onClick(View v) {
			apagarCampos(v);			
		}
		
	}
	
	private class CadastrarButton implements OnClickListener{

		@Override
		public void onClick(View v) {
			
			Endereco end = new Endereco(enderecoVendedor.getText().toString(),numeroVendedor.getText().toString(),cepVendedor.getText().toString(),
					spinnerUF.getSelectedItem().toString(),cidadeEdit.getText().toString(), bairroVendedor.getText().toString());
			
			if(cadastrarVendedor(1l,nomeVendedor.getText().toString(),telefoneVendedor.getText().toString(),rgVendedor.getText().toString(),
							  cpfVendedor.getText().toString(),end,loginVendedor.getText().toString(),senhaVendedor.getText().toString())){
				
				Utils.mostrarError(CadastrarVendedorActivity.this, "Vendedor cadastrado com Sucesso");
				
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
						cidadeEdit.setText("JO�O PESSOA");
					break;
					
					case 1:
						
					break;
					
					case 2:
						
					break;
					
					case 3:
						
					break;

					default:
						cidadeEdit.setText("JO�O PESSOA");
					}
				}			
				
			}
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			
		}}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastrar_vendor, menu);
		return true;
	}
	
	private void apagarCampos(View v){
		
//		 int count = v.getChildCount();
//		    for (int i = 0; i < count; i++) {
//		        View view = ((ViewGroup) v).getChildAt(i);
//		        if (view instanceof EditText) {
//		            ((EditText)view).setText("");
//		        }
//		    }
		
	}
	
	private boolean cadastrarVendedor(Long id,String nome, String telefone, String rg, String cpf,
			Endereco endereco,String login, String senha){
		
		if(validarCampos(id, nome, telefone, rg, cpf, endereco, login, senha) == false){;
			return false;
		}
		
		String baseURL = "http://192.168.0.166:8080/ServerGerenciamentoFiadoV1/gerenciarVendedor";

		try {
			
				Vendedor vendedor = new Vendedor(id, nome, telefone, rg, cpf, endereco, login, senha);
				CadastrarVendedorInput vendedorInput = new CadastrarVendedorInput(vendedor);
				JSONObject inputStringVendedor = new JSONObject(vendedorInput.getInputMap());
				
				String jsonString = HttpUtils.urlContentPost(baseURL,"vendedor",inputStringVendedor.toString());
				
				JSONObject jsonResult = new JSONObject(jsonString);
				
				Log.i("jsonResult", jsonResult.toString());
				
				if(jsonResult.toString().equalsIgnoreCase("true")){
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
		
		return true;
	}
	
	private boolean validarCampos(Long id,String nome, String telefone, String rg, String cpf,
			Endereco endereco,String login, String senha){
		
		if(nome.isEmpty()){
			Log.i("vendedorNome", nome);
			Utils.mostrarError(this, Mensagens.campoNomeVendedorVazio);
			return false;
		}else if(rg.isEmpty()){
			Log.i("vendedorRG", rg);
			Utils.mostrarError(this, Mensagens.campoRGVendedorVazio);
			return false;
		}else{
			return true;
		}
		
	}

}
