package br.ufpb.lp3.gerenciamento_fiado.menu_principal;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;
import br.ufpb.lp3.gerenciamento_fiado.R;
import br.ufpb.lp3.gerenciamento_fiado.configuracoes.Configuracoes;
import br.ufpb.lp3.gerenciamento_fiado.gerenciar_cliente.GerenciarClienteActivity;
import br.ufpb.lp3.gerenciamento_fiado.gerenciar_contas.GerenciarContasActivity;
import br.ufpb.lp3.gerenciamento_fiado.gerenciar_produto.GerenciarProdutoActivity;
import br.ufpb.lp3.gerenciamento_fiado.gerenciar_vendas.GerenciarVendaActivity;
import br.ufpb.lp3.gerenciamento_fiado.gerenciar_vendedor.GerenciarVendedorActivity;
import br.ufpb.lp3.gerenciamento_fiado.models.Vendedor;
import br.ufpb.lp3.gerenciamento_fiado.utils.Utils;

public class MenuPrincipalActivity extends Activity {
	private Vendedor vendedor = null;
	
	private RelativeLayout rl = null;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        
      //Linear Layout
        
        Bundle dados = getIntent().getExtras();
        vendedor = (Vendedor)dados.getSerializable("vendedor");
        
        Utils.mostrarMensagens(this, String.valueOf("Bem vindo "+vendedor.getNome()));
        
        //confugurações da tela
        rl = (RelativeLayout)findViewById(R.id.menuPrincipaRelativeLayout);
        
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
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }
     
    public void GerenciarCliente(View v){
    	Utils.goToActivity(this, GerenciarClienteActivity.class);
    }
    
    public void GerenciarVendedor (View v){
    	Utils.goToActivityVendedor(this, GerenciarVendedorActivity.class, vendedor);
    }
    
    public void GerenciarProduto(View v){
    	Utils.goToActivity(this, GerenciarProdutoActivity.class);
    }
    
    public void GerenciarContas(View v){
    	Utils.goToActivity(this, GerenciarContasActivity.class);
    }
    
    public void GerenciarVendas(View v){
    	Utils.goToActivityVendedor(this, GerenciarVendaActivity.class,vendedor);
    }
    
    public void Configuracoes(View v){
    	
    	goToActivityConf(Configuracoes.class);
    }
    
    //método que abrirá outras Activitys
    private void goToActivityConf (Class<? extends PreferenceActivity> activityClass2){
    	Intent newIntent = new Intent(this,activityClass2);
    	startActivity(newIntent);
    }
    
}
