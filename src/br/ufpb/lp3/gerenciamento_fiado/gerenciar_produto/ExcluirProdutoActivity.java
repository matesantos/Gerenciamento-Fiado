package br.ufpb.lp3.gerenciamento_fiado.gerenciar_produto;

import br.ufpb.lp3.gerenciamento_fiado.R;
import br.ufpb.lp3.gerenciamento_fiado.gerenciar_cliente.BuscarClienteExcluir;
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

public class ExcluirProdutoActivity extends Activity {
	
	private EditText nomeProduto = null;
	private EditText precoProduto = null;
	
	private Button apagar = null;
	private Button excluirProduto = null;

	
	Produto produtoSerializabe = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_excluir_produto);
		
		Bundle dados = getIntent().getExtras();
		produtoSerializabe = (Produto) dados.getSerializable("produto");
		
		nomeProduto  = (EditText)findViewById(R.id.editTextNomeProdutoTelaExcluirProduto);
		precoProduto = (EditText)findViewById(R.id.editTextPrecoProdutoTelaExcluirProduto);
		
		excluirProduto =(Button)findViewById(R.id.buttonExcluirTelaExcluirProduto);
		excluirProduto.setOnClickListener(new ExcluirButton());

		preencherCampos();
	}
	
	private class ExcluirButton implements OnClickListener {

		@Override
		public void onClick(View v) {

			Produto pro = new Produto(produtoSerializabe.getCodigo(), produtoSerializabe.getNome(),produtoSerializabe.getPreco());
			if (excluirProdutos(pro)) {

				Utils.mostrarMensagens(ExcluirProdutoActivity.this,	"Produto excluído com sucesso");

				retornarTelaAnterior();

			}
		}

	}

	private void retornarTelaAnterior() {
		Utils.goToActivity(this, ExcluirProdutoList.class);
	}
	
	private boolean excluirProdutos(Produto produto){
		ProdutoDAO produtoDao = ProdutoBDFactory.getProdutoBD(this);
		
		if(produtoDao.deletar(produto)){
			return true;
		}
		
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.excluir_produto, menu);
		return true;
	}
	
	private void preencherCampos(){
		
		nomeProduto.setText(produtoSerializabe.getNome());
		precoProduto.setText(String.valueOf(produtoSerializabe.getPreco()));
	
		
	}

}
