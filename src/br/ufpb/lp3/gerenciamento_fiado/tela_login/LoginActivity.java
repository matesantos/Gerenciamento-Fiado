package br.ufpb.lp3.gerenciamento_fiado.tela_login;

import br.ufpb.lp3.gerenciamento_fiado.R;
import br.ufpb.lp3.gerenciamento_fiado.gerenciar_vendedor.CadastrarVendedorActivity;
import br.ufpb.lp3.gerenciamento_fiado.menu_principal.MenuPrincipalActivity;
import br.ufpb.lp3.gerenciamento_fiado.models.Vendedor;
import br.ufpb.lp3.gerenciamento_fiado.utils.Utils;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	//edit
	public EditText loginEdit = null;
	public EditText senhaEdit = null;
	
	//buttons
	private Button cadastrarButton = null;
	private Button entrarButton = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		//edit's fields  
		loginEdit = (EditText)findViewById(R.id.editTextLoginTelaLogin);
		senhaEdit = (EditText)findViewById(R.id.editTextSenhaTelaLogin);
		
		//button's fields 
		cadastrarButton = (Button)findViewById(R.id.buttonCadastrarTelaLogin);
		entrarButton = (Button)findViewById(R.id.buttonEntrarTelaLogin);
		
		cadastrarButton.setOnClickListener(new FazerLogin(this, "salvarDados"));
//		entrarButton.setOnClickListener(new FazerLogin("mateus","123", this, "entrar"));

		entrarButton.setOnClickListener(new FazerLogin(loginEdit.getText().toString().trim(),
				   senhaEdit.getText().toString(),
				   this, "entrar"));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	public void EntrarNoSistema(Vendedor vendedor){
		
		Utils.goToActivityVendedor(this, MenuPrincipalActivity.class, vendedor);
		
	}
	
	public void mostrarError(String error){
    	Toast msg = Toast.makeText(this, error, Toast.LENGTH_SHORT);
    	msg.show();
	}
	    
	public void CadastrarVendedor (){
	  	Utils.goToActivity(this, CadastrarVendedorActivity.class);
	}
	
}
