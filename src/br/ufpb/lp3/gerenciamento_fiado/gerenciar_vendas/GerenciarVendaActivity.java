package br.ufpb.lp3.gerenciamento_fiado.gerenciar_vendas;

import br.ufpb.lp3.gerenciamento_fiado.R;
import br.ufpb.lp3.gerenciamento_fiado.utils.Utils;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GerenciarVendaActivity extends Activity {
	
	private Button cadastrar = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gerenciar_venda);
		
		cadastrar = (Button) findViewById(R.id.buttonCadastrarTelaGerenciarVenda);
		cadastrar.setOnClickListener(new Cadastrar());
	}
	
	private class Cadastrar implements OnClickListener{

		@Override
		public void onClick(View v) {
		
			CadastrarVenda();
			
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gerenciar_venda, menu);
		return true;
	}
	
	public void CadastrarVenda(){
		Utils.goToActivity(this, CadastrarVendas.class);
	}

}
