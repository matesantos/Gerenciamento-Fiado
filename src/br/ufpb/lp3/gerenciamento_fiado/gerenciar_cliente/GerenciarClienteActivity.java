package br.ufpb.lp3.gerenciamento_fiado.gerenciar_cliente;


import br.ufpb.lp3.gerenciamento_fiado.R;
import br.ufpb.lp3.gerenciamento_fiado.utils.Utils;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;

public class GerenciarClienteActivity extends Activity {
	
	private RelativeLayout rl = null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gerenciar_cliente);
		
		  //confugurações da tela
        rl = (RelativeLayout)findViewById(R.id.relativeLayoutGerenciarCliente);
        
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
		getMenuInflater().inflate(R.menu.gerenciar_cliente, menu);
		return true;
	}
	
	public void casdastrarCLiente (View v){
		Utils.goToActivity(this, CadastrarClienteActivity.class);
	}
	
	public void atualizarCLiente (View v){
		Utils.mostrarMensagens(this, "Você só poderá atualizar cliente selecionando primeiro o cliente na tela Buscar Cliente.");	
	}
	
	public void buscarCLiente (View v){
		Utils.goToActivity(this, BuscarClienteActivity.class);
	}
	
	public void excluirCLiente (View v){
		Utils.goToActivity(this, ExcluirClienteActivity.class);
	}
	
}
