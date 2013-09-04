package br.ufpb.lp3.gerenciamento_fiado.gerenciar_vendedor;

import br.ufpb.lp3.gerenciamento_fiado.R;
import br.ufpb.lp3.gerenciamento_fiado.models.Vendedor;
import br.ufpb.lp3.gerenciamento_fiado.utils.Utils;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;

public class GerenciarVendedorActivity extends Activity {
	
	Vendedor vendedor = null;
	
	private RelativeLayout rl = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gerenciar_vendedor);
		
		Bundle dados = getIntent().getExtras();
		
		vendedor = (Vendedor) dados.getSerializable("vendedor");
		
		//confugurações da tela
        rl = (RelativeLayout)findViewById(R.id.relativeLayoutGerenciarVendedor);
        
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
		getMenuInflater().inflate(R.menu.gerenicar_vendedor, menu);
		return true;
	}
	
	public void casdastrarVendedor (View v){
		goToActivity(CadastrarVendedorActivity.class);
	}
	
	public void atualizarVendedor (View v){
		Utils.goToActivityVendedor(this, AtualizarVendedorActivity.class, vendedor);
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
