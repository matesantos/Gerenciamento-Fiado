package br.ufpb.lp3.gerenciamento_fiado.gerenciar_contas;

import br.ufpb.lp3.gerenciamento_fiado.R;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.Menu;
import android.widget.RelativeLayout;

public class GerenciarContasActivity extends Activity {
	
	private RelativeLayout rl = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gerenciar_contas);
		
		//confugurações da tela
        rl = (RelativeLayout)findViewById(R.id.relativeLayoutGerenciarConta);
        
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(this);
        
        boolean fundoImagem = preference.getBoolean(getString(R.string.backgroundRosa), false);
        
        if(fundoImagem){
			rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_image));
		}else{
			rl.setBackgroundColor(Color.WHITE);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gerenciar_contas, menu);
		return true;
	}

}
