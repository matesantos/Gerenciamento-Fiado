package br.ufpb.lp3.gerenciamento_fiado.gerenciar_produto;

import java.util.ArrayList;
import java.util.List;

import br.ufpb.lp3.gerenciamento_fiado.R;
import br.ufpb.lp3.gerenciamento_fiado.models.Produto;
import br.ufpb.lp3.gerenciamento_fiado.persistencia_dados.produtos.ProdutoBD;
import br.ufpb.lp3.gerenciamento_fiado.persistencia_dados.produtos.ProdutoBDFactory;
import br.ufpb.lp3.gerenciamento_fiado.utils.Utils;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ExcluirProdutoList extends Activity {

	EditText 	  buscarProduto = null;
	ImageButton   buscarButton = null;
	ListView 	  listProduto = null;
	Cursor		  produtoCurso = null;
	
	List<Produto> produtoLista = new ArrayList<Produto>();
	
	AdapterProduto adapterProduto = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_excluir_produto_list);
		
		buscarProduto = (EditText)findViewById(R.id.editTextBuscarProdutoTelaExcluirProdutoList);
		
		listProduto = (ListView)findViewById(R.id.listPesquisarProdutoTelaExcluirProdutoList);
		
		buscarButton  = (ImageButton)findViewById(R.id.imageButtonPesquisarProdutoTelaExcluirProdutoList);
		
		//listar todos os produtoLista 
		listarTodosProduto();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.excluir_produto_list, menu);
		return true;
	}
	
	private void listarTodosProduto(){
		
		ProdutoBD produtoDao = ProdutoBDFactory.getProdutoBD(this); 
		
//		produtoCurso = (Cursor) produtoDao.getProduto(2l);
		produtoCurso = produtoDao.buscarTodosOsProdutos();
		
		if(produtoCurso.isBeforeFirst()){
			produtoCurso.moveToFirst();
		}
		
		startManagingCursor(produtoCurso);
		
		for(int i = 0; i < produtoCurso.getCount(); i++){
			
			Produto pro = new Produto(produtoDao.getID(produtoCurso),produtoDao.getNome(produtoCurso), Float.parseFloat(produtoDao.getPreco(produtoCurso)));
			
			produtoLista.add(pro);
			
		}
		
		adapterProduto = new AdapterProduto(this, produtoCurso,produtoLista);
		
		listProduto.setAdapter(adapterProduto);
		listProduto.setOnItemClickListener(onListClick);
		
	}
	
	private OnItemClickListener onListClick = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
			
			mudarTela(position);
			
		}
	};
		
	private void mudarTela(int position){
		produtoCurso.moveToPosition(position);
		Log.i("Nome do cliente", produtoLista.get(position).getNome());
		Utils.goToActivityProduto(this, ExcluirProdutoActivity.class,produtoLista.get(position));
	}

}
