package br.ufpb.lp3.gerenciamento_fiado.gerenciar_contas;

import br.ufpb.lp3.gerenciamento_fiado.R;
import br.ufpb.lp3.gerenciamento_fiado.R.layout;
import br.ufpb.lp3.gerenciamento_fiado.R.menu;
import br.ufpb.lp3.gerenciamento_fiado.gerenciar_cliente.ExcluirClienteActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class GerenciarContasActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gerenciar_contas);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gerenciar_contas, menu);
		return true;
	}
	
	public void cadastrarContas(View v){
		goToActivity(CadastrarContasActivity.class);
	}
	
	//método que abrirá outras Activitys
    private void goToActivity (Class<? extends Activity> activityClass2){
    	Intent newIntent = new Intent(this,activityClass2);
    	startActivity(newIntent);
    }
	

}
