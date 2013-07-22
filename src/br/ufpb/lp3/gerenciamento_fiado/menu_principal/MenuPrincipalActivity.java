package br.ufpb.lp3.gerenciamento_fiado.menu_principal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import br.ufpb.lp3.gerenciamento_fiado.R;
import br.ufpb.lp3.gerenciamento_fiado.gerenciar_cliente.GerenciarClienteActivity;
import br.ufpb.lp3.gerenciamento_fiado.gerenciar_contas.GerenciarContasActivity;
import br.ufpb.lp3.gerenciamento_fiado.gerenciar_produto.GerenciarProdutoActivity;

public class MenuPrincipalActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }
     
    public void GerenciarCliente(View v){
    	goToActivity(GerenciarClienteActivity.class);
    }
    
    
    
    public void GerenciarProduto(View v){
    	goToActivity(GerenciarProdutoActivity.class);
    }
    
    public void GerenciarContas(View v){
    	goToActivity(GerenciarContasActivity.class);
    }    	

    
   
    
    public void Configuracoes(View v){
    	
    }
    
    //método que abrirá outras Activitys
    private void goToActivity (Class<? extends Activity> activityClass2){
    	Intent newIntent = new Intent(this,activityClass2);
    	startActivity(newIntent);
    }
    
}
