package br.ufpb.lp3.gerenciamento_fiado.gerenciar_produto;

import br.ufpb.lp3.gerenciamento_fiado.R;
import br.ufpb.lp3.gerenciamento_fiado.utils.Utils;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class GerenciarProdutoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gerenciar_produto);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gerenciar_produto, menu);
		return true;
	}
	
	public void CadastrarProduto(View v)
	{
		Utils.goToActivity(this, CadastrarProdutoActivity.class);
	}
	
	public void AtualizarProduto(View v)
	{
		Utils.mostrarMensagens(this, "Você só poderá atualizar produto " +
				"selecionando primeiro o produto na tela Buscar Produto.");	
	}
	
	public void BuscarProduto(View v)
	{
		Utils.goToActivity(this, AtualizarProdutoActivity.class);
	}
	
	public void ExcluirProduto(View v)
	{
		Utils.goToActivity(this, AtualizarProdutoActivity.class);
	}
	
}
