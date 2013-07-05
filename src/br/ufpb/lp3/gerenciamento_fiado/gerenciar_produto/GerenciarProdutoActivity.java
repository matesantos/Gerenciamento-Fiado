package br.ufpb.lp3.gerenciamento_fiado.gerenciar_produto;

import br.ufpb.lp3.gerenciamento_fiado.R;
import br.ufpb.lp3.gerenciamento_fiado.R.layout;
import br.ufpb.lp3.gerenciamento_fiado.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
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
		goToActivity(CadastrarProdutoActivity.class);
	}
	
	
	private void goToActivity (Class<? extends Activity> activityClass2){
    	Intent newIntent = new Intent(this,activityClass2);
    	startActivity(newIntent);
    }

}
