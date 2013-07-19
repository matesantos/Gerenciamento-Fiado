package br.ufpb.lp3.gerenciamento_fiado.gerenciar_contas;

import java.util.List;

import br.ufpb.lp3.gerenciamento_fiado.R;
import br.ufpb.lp3.gerenciamento_fiado.R.layout;
import br.ufpb.lp3.gerenciamento_fiado.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class PesquisaContas extends Activity {

	private Spinner spinerOpcao = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pesquisa_contas);
		List<String> listas = UltilsOpcoes.getListOpcao();
		ArrayAdapter<String> sppinerOpcao = new ArrayAdapter<String>(this, android.R.layout.activity_list_item,listas);
		sppinerOpcao.setDropDownViewResource(android.R.layout.activity_list_item);
		spinerOpcao.setAdapter(sppinerOpcao);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pesquisa_contas, menu);
		return true;
	}

}
