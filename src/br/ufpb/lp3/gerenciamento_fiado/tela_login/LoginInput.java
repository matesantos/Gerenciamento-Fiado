package br.ufpb.lp3.gerenciamento_fiado.tela_login;

import java.util.HashMap;
import java.util.Map;

public class LoginInput {
	
	private Map<String, String> loginInput;
	
	public LoginInput(String login, String senha) {
		loginInput = new HashMap<String, String>();
		loginInput.put("login", login);
	}
	
	 public Map<String,String> getInputMap() {
	        return(loginInput);
	    }

}
