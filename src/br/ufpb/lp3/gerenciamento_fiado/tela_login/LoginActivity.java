package br.ufpb.lp3.gerenciamento_fiado.tela_login;

import br.ufpb.lp3.gerenciamento_fiado.R;
import br.ufpb.lp3.gerenciamento_fiado.gerenciar_vendedor.CadastrarVendedorActivity;
import br.ufpb.lp3.gerenciamento_fiado.menu_principal.MenuPrincipalActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	 public void EntrarNoSistema(View v){
	   	goToActivity(MenuPrincipalActivity.class);
	}
	    
	public void CadastrarVendedor (View v){
	  	goToActivity(CadastrarVendedorActivity.class);
	}
	
	//método que abrirá outras Activitys
    private void goToActivity (Class<? extends Activity> activityClass2){
    	Intent newIntent = new Intent(this,activityClass2);
    	startActivity(newIntent);
    }

}
