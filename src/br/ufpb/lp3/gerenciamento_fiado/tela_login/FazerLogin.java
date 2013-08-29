package br.ufpb.lp3.gerenciamento_fiado.tela_login;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import br.ufpb.gerenciamento_fiado.URL.HttpUtils;
import br.ufpb.lp3.gerenciamento_fiado.mesagens.Mensagens;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class FazerLogin implements OnClickListener {

	private String login;
	private String senha;
	private LoginActivity loginActivity;

	// variável que vai indicar qualqual botao foi clicado;
	private String botao;
	
	public FazerLogin(String login, String senha, LoginActivity loginActivity,
			String butao) {
		this.login = login;
		this.senha = senha;
		this.loginActivity = loginActivity;
		this.botao = butao;
	}

	// para salvarDados um vendedor
	public FazerLogin(LoginActivity loginActivity, String butao) {
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

	// método para fazer o login
	private void fazerLogin(String login, String senha) {
		
//		String baseURL = "http://192.168.0.166:8080/ServerGerenciamentoFiadoV1/server";
		String baseURL = "http://192.168.0.166:8080/ServerGerenciamentoFiadoV1/server";

		try {
			if (login.isEmpty()) {
				String error = Mensagens.campoLoginSenhaVazio;
				loginActivity.mostrarError(error);
			} else {
				LoginInput loginInput = new LoginInput(login, "123");
				JSONObject inputString = new JSONObject(loginInput.getInputMap());
				
//				String jsonString = HttpUtils.urlContentPost(baseURL, "login",inputString.toString());
				
				String jsonString = HttpUtils.urlContentPost(baseURL, "login",inputString.toString());
				
				JSONObject jsonResult = new JSONObject(jsonString);
				
				loginActivity.EntrarNoSistema();

			}
		}
		catch (IOException exe){
			Log.e("IOException1", exe.getMessage());
			Log.e("IOException3", login);
			Log.e("IOException4",Log.getStackTraceString(exe.getCause()));
			loginActivity.mostrarError("error");
		}catch (Exception e) {
			Log.e("IOException", e.getMessage());
			loginActivity.mostrarError("error");
		}

	}

	private void cadastrar() {
		loginActivity.CadastrarVendedor();
	}

	// método para salvarDados no sistema

	@Override
	public void onClick(View v) {

		this.login = loginActivity.loginEdit.getText().toString();
		this.senha = loginActivity.senhaEdit.getText().toString();

		if (botao.equalsIgnoreCase("entrar")) {
			fazerLogin(login, senha);
		} else if (botao.equalsIgnoreCase("salvarDados")) {
			cadastrar();
		}
	}

}
