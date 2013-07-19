package br.ufpb.lp3.gerenciamento_fiado.gerenciar_vendedor;

import br.ufpb.lp3.gerenciamento_fiado.R;
import br.ufpb.lp3.gerenciamento_fiado.R.layout;
import br.ufpb.lp3.gerenciamento_fiado.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CadastrarVendorActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_vendedor);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastrar_vendor, menu);
		return true;
	}

}
