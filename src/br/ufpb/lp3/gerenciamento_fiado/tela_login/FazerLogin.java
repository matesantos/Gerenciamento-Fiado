package br.ufpb.lp3.gerenciamento_fiado.tela_login;

import br.ufpb.lp3.gerenciamento_fiado.mesagens.Mensagens;
import android.view.View;
import android.view.View.OnClickListener;

public class FazerLogin implements OnClickListener{
	
	private String login;
	private String senha;
	private LoginActivity loginActivity;
	//variável que vai indicar qualqual botao foi clicado;
	private String botao;
	
	public FazerLogin(String login, String senha, LoginActivity loginActivity, String butao){
		this.login = login;
		this.senha = senha;
		this.loginActivity = loginActivity;
		this.botao = butao;
	}

	//para cadastrar um vendedor
	public FazerLogin(LoginActivity loginActivity, String butao){
		this.loginActivity = loginActivity;
		this.botao = butao;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	//método para fazer o login
	private void fazerLogin(String login, String senha){
		if (login.isEmpty()){
			String error = Mensagens.campoLoginSenhaVazio;
			loginActivity.mostrarError(error);
			
		}else{
			loginActivity.EntrarNoSistema();
			
		}
		
	}
	
	private void cadastrar(){
		loginActivity.CadastrarVendedor();
	}
	
	//método para cadastrar no sistema

	@Override
	public void onClick(View v) {
		
		this.login = loginActivity.loginEdit.getText().toString();
		this.senha = loginActivity.senhaEdit.getText().toString();
		
		if(botao.equalsIgnoreCase("entrar")){
			fazerLogin(login,senha);
		}else if (botao.equalsIgnoreCase("cadastrar")){
			cadastrar();
		}
	}
	

}
