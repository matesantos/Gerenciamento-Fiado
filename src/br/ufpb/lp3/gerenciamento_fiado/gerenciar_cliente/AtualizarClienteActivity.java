package br.ufpb.lp3.gerenciamento_fiado.gerenciar_cliente;

import java.util.List;

import br.ufpb.lp3.gerenciamento_fiado.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AtualizarClienteActivity extends Activity {

	private Spinner spinnerTelefone = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_atualizar_cliente);
        List<String> listColor = UtilsCliente.getTelefoneList();
        ArrayAdapter<String> spinnerList = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,listColor);
        spinnerList.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerTelefone.setAdapter(spinnerList);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.atualizar_cliente, menu);
		return true;
	}

}
