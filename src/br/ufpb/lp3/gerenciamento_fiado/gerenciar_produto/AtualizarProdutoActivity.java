package br.ufpb.lp3.gerenciamento_fiado.gerenciar_produto;

import br.ufpb.lp3.gerenciamento_fiado.R;
import br.ufpb.lp3.gerenciamento_fiado.mesagens.Mensagens;
import br.ufpb.lp3.gerenciamento_fiado.models.Produto;
import br.ufpb.lp3.gerenciamento_fiado.persistencia_dados.produtos.ProdutoBDFactory;
import br.ufpb.lp3.gerenciamento_fiado.persistencia_dados.produtos.ProdutoDAO;
import br.ufpb.lp3.gerenciamento_fiado.utils.Utils;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AtualizarProdutoActivity extends Activity {
	
	private EditText nomeProduto  = null;
	private EditText precoProduto = null;
	
	private Button atualizarProduto = null;
	
	Produto produtoSerializable = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_atualizar_produto);
		
		Bundle dados = getIntent().getExtras();
		produtoSerializable = (Produto)dados.getSerializable("produto");
		
		// edit text
		nomeProduto 		= (EditText) findViewById(R.id.editTextNomeProdutoTelaAtualizarProduto);
		precoProduto		= (EditText) findViewById(R.id.editTextPrecoProdutoTelaAtualizarProduto);
		
		
		//button
		atualizarProduto  	= (Button) findViewById(R.id.buttonAtualizarTelaAtualizarProduto);
		atualizarProduto .setOnClickListener(new AtualizarButton());
		
		preencherCampos();
	}
	
	private class AtualizarButton implements OnClickListener{

		@Override
		public void onClick(View v) {
			
			Produto produto = new Produto(produtoSerializable.getCodigo(),nomeProduto.getText().toString(), Float.parseFloat(precoProduto.getText().toString()));
			
			if(atualizarProduto(produto)){
				
				mostraMensagem();
				
				retornarTela();
				
			}
		}

	}
	
	private void mostraMensagem() {
		
		Utils.mostrarMensagens(this, "Produto atualizado com sucesso.");
		
	}
	
	private void retornarTela() {
		
		Utils.goToActivity(this, GerenciarProdutoActivity.class);
		
	}
	
	private boolean atualizarProduto(Produto produto) {

		if(validarCampos(produto.getNome(), String.valueOf(produto.getPreco())) == false){
			return false;
		}
		
//		try {
			
			ProdutoDAO atualizarProduto = ProdutoBDFactory.getProdutoBD(this);

			if(atualizarProduto.atualizar(produto)){
				return true;
			}
			
//		}catch (Exception e) {
//			Log.e("Cadastrar Cliente", e.getMessage());
//			Log.e("Cadastrar Cliente2",Log.getStackTraceString(e.fillInStackTrace()));
//		}
//		
		return false;
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

	private void preencherCampos(){
		nomeProduto.setText(produtoSerializable.getNome());
		precoProduto.setText(String.valueOf(produtoSerializable.getPreco()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.atualizar_produto, menu);
		return true;
	}

}
