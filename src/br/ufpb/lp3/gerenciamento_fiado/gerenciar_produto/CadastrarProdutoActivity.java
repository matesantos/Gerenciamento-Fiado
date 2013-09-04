package br.ufpb.lp3.gerenciamento_fiado.gerenciar_produto;


import java.io.IOException;
import java.text.NumberFormat;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import br.ufpb.gerenciamento_fiado.URL.HttpUtils;
import br.ufpb.lp3.gerenciamento_fiado.R;
import br.ufpb.lp3.gerenciamento_fiado.mesagens.Mensagens;
import br.ufpb.lp3.gerenciamento_fiado.models.Produto;
import br.ufpb.lp3.gerenciamento_fiado.persistencia_dados.produtos.ProdutoBDFactory;
import br.ufpb.lp3.gerenciamento_fiado.persistencia_dados.produtos.ProdutoDAO;
import br.ufpb.lp3.gerenciamento_fiado.utils.Utils;

public class CadastrarProdutoActivity extends Activity implements TextWatcher{
	
	private EditText nomeProduto = null;
	private EditText precoProduto = null;
	
	private Button cadastrarProduto = null;
	
	//váriaveis para a máscara de preço
	private boolean isUpdating;  
	private NumberFormat mNF = NumberFormat.getCurrencyInstance();  
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_produto);

		// edit text
		nomeProduto 		= (EditText) findViewById(R.id.editTextNomeProdutoTelaCadastratarProduto);
		precoProduto		= (EditText) findViewById(R.id.editTextPrecoProdutoTelaCadastratProduto);
		
		
		//button
		cadastrarProduto 	= (Button) findViewById(R.id.buttonCadastrarTelaCadastrarProduto);
		cadastrarProduto.setOnClickListener(new CadastrarButton());

	}
	
	private class CadastrarButton implements OnClickListener{

		@Override
		public void onClick(View v) {
			
			Produto produto = new Produto(nomeProduto.getText().toString(), Float.parseFloat(precoProduto.getText().toString()));
			
			if(cadastrarProduto(produto)){
				
				mostraMensagem();
				
				if(cadastrarProdutoNoServidor(produto)){
					mostraMensagem2();
				}
				
			}
		}
		
	}
	
	private void mostraMensagem(){
		Utils.mostrarMensagens(this, "Produto cadastrado no seu dispositivo com sucesso.");
	}
	
	private void mostraMensagem2(){
		Utils.mostrarMensagens(this, "Produto cadastrado no servidor com sucesso.");
	}
	
	private boolean cadastrarProduto(Produto produto){
		
		if(validarCampos(produto.getNome(), precoProduto.getText().toString()) == false){
			return false;
		}
		
//		try {
			
			ProdutoDAO cadastrarProduto = ProdutoBDFactory.getProdutoBD(this);

			if(cadastrarProduto.salvar(produto)){
				return true;
			}
			
//		}catch (Exception e) {
//			Log.e("Cadastrar Cliente", e.getMessage());
//			Log.e("Cadastrar Cliente2",Log.getStackTraceString(e.fillInStackTrace()));
//		}
//		
		return false;
		
		
	}
	
	private boolean cadastrarProdutoNoServidor(Produto produto){
		
		String baseURL = "http://192.168.0.166:8080/ServerGerenciamentoFiadoV1/gerenciarProdutos";

		try {
			
				CadastrarProdutoInput produtoInput = new CadastrarProdutoInput(produto);
				JSONObject inputStringProduto = new JSONObject(produtoInput.getInputMap());
				
				String jsonString = HttpUtils.urlContentPost(baseURL,"produto",inputStringProduto.toString());
				
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastrar_cliente, menu);
		return true;
	}
	
	private boolean validarCampos(String nome, String preco){
		
		if(nome.isEmpty()){
			Utils.mostrarMensagens(this, Mensagens.campoNomeProdutoVazio);
			return false;
		}else if(preco.isEmpty()){
			Utils.mostrarMensagens(this, Mensagens.campoPrecoProdutoVazio);
			return false;
		}else{
			return true;
		}
		
	}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}
	
	//método para fazer a máscara de preço do produto
	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		if (isUpdating) {  
	         isUpdating = false;  
	         return;  
	      }  
	  
	      isUpdating = true;  
	      String str = s.toString();  
	      boolean hasMask = (str.indexOf("R$") >= 0 && str.indexOf(".") >= 0 && str.indexOf(",") >= 0);  
	  
	      if (hasMask) {  
	         str = str.replaceAll("[R$]", "").replaceAll("[.]", "").replaceAll("[,]", "");  
	      }  
	  
	      try {  
	         double value = (Double.parseDouble(str) / 100);  
	         str = mNF.format(value);  
	         precoProduto.setText(str);  
	         precoProduto.setSelection(str.length());  
	      } catch (Exception e) {  
	         e.printStackTrace();  
	      }  		
	}

}
