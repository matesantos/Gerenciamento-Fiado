package br.ufpb.lp3.gerenciamento_fiado.gerenciar_cliente;

import br.ufpb.lp3.gerenciamento_fiado.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class BuscarClienteActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buscar_cliente);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.buscar_cliente, menu);
		return true;
	}
		

}
