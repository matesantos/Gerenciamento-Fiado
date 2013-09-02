package br.ufpb.lp3.gerenciamento_fiado.gerenciar_cliente;


import br.ufpb.lp3.gerenciamento_fiado.R;
import br.ufpb.lp3.gerenciamento_fiado.utils.Utils;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class GerenciarClienteActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gerenciar_cliente);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gerenciar_cliente, menu);
		return true;
	}
	
	public void casdastrarCLiente (View v){
		Utils.goToActivity(this, CadastrarClienteActivity.class);
	}
	
	public void atualizarCLiente (View v){
		Utils.mostrarMensagens(this, "Essa tela s� ser� poss�vel atualizar cliente " +
				"selecionando primeiro o cliente na tela Buscar CLiente.");	}
	
	public void buscarCLiente (View v){
		Utils.goToActivity(this, BuscarClienteActivity.class);
	}
	
	public void excluirCLiente (View v){
		Utils.goToActivity(this, ExcluirClienteActivity.class);
	}
	
}
