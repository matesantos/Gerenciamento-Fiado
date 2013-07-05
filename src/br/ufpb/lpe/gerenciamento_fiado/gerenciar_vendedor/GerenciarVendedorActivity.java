package br.ufpb.lpe.gerenciamento_fiado.gerenciar_vendedor;

import br.ufpb.lp3.gerenciamento_fiado.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class GerenciarVendedorActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gerenciar_vendedor);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gerenicar_vendedor, menu);
		return true;
	}
	
	public void casdastrarVendedor (View v){
		goToActivity(CadastrarVendorActivity.class);
	}
	
	public void atualizarVendedor (View v){
		goToActivity(AtualizarVendedorActivity.class);
	}
	
	public void excluirVendedor (View v){
		goToActivity(ExcluirVendedorActivity.class);
	}
	
	//método que abrirá outras Activitys
    private void goToActivity (Class<? extends Activity> activityClass2){
    	Intent newIntent = new Intent(this,activityClass2);
    	startActivity(newIntent);
    }

}
