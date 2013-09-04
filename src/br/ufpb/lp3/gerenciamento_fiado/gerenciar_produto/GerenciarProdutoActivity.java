package br.ufpb.lp3.gerenciamento_fiado.gerenciar_produto;

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

public class GerenciarProdutoActivity extends Activity {

	RelativeLayout rl = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gerenciar_produto);
		
		  //confugurações da tela
        rl = (RelativeLayout)findViewById(R.id.relativeLayoutGerenciarProduto);
        
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
		getMenuInflater().inflate(R.menu.gerenciar_produto, menu);
		return true;
	}
	
	public void CadastrarProduto(View v)
	{
		Utils.goToActivity(this, CadastrarProdutoActivity.class);
	}
	
	public void AtualizarProduto(View v)
	{
		Utils.mostrarMensagens(this, "Você só poderá atualizar produto " +
				"selecionando primeiro o produto na tela Buscar Produto.");	
	}
	
	public void BuscarProduto(View v)
	{
		Utils.goToActivity(this, BuscarProdutoActivity.class);
	}
	
	public void ExcluirProduto(View v)
	{
		Utils.goToActivity(this, ExcluirProdutoList.class);
	}
	
}
