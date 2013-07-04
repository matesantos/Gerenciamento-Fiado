package br.ufpb.lp3.gerenciamento_fiado.gerenciar_cliente;


import br.ufpb.lp3.gerenciamento_fiado.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class GerenciarClienteActivity extends Activity {

	
	private Button atualizarClienteButton = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gerenciar_cliente);
		
		atualizarClienteButton = (Button)findViewById(R.id.AtualizarClienteBotao);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gerenciar_cliente, menu);
		return true;
	}
	
	public void casdastrarCLiente (View v){
		goToActivity(CadastrarClienteActivity.class);
	}
	
	public void atualizarCLiente (View v){
		goToActivity(AtualizarClienteActivity.class);
	}
	
	//método que abrirá outras Activitys
    private void goToActivity (Class<? extends Activity> activityClass2){
    	Intent newIntent = new Intent(this,activityClass2);
    	startActivity(newIntent);
    }

}
