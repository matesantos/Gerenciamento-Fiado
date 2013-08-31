package br.ufpb.lp3.gerenciamento_fiado.tela_login;

import br.ufpb.lp3.gerenciamento_fiado.mesagens.Mensagens;
import br.ufpb.lp3.gerenciamento_fiado.models.Endereco;
import br.ufpb.lp3.gerenciamento_fiado.models.Vendedor;
import br.ufpb.lp3.gerenciamento_fiado.persistencia_dados.vendedor.VendedorBDFactory;
import br.ufpb.lp3.gerenciamento_fiado.persistencia_dados.vendedor.VendedorDAO;
import android.database.Cursor;
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
		try {
			if (login.isEmpty()) {
				String error = Mensagens.campoLoginSenhaVazio;
				loginActivity.mostrarError(error);
			} else {
				VendedorDAO cadastrarVendedor = VendedorBDFactory.getVendedorBD(loginActivity);
				
				Cursor cursor = cadastrarVendedor.buscarVendedorPorLoginSenha(login, senha);
				
				if(cursor.isBeforeFirst()){
					cursor.moveToFirst();
				}
				
				if(cursor.getCount() != 0){
					
					cursor.moveToFirst();
					
					long id = cadastrarVendedor.getID(cursor);
		            String nome = cadastrarVendedor.getNome(cursor);
		            String telefone = cadastrarVendedor.getTelefone(cursor);
		            String rg = cadastrarVendedor.getRG(cursor);
		            String cpf = cadastrarVendedor.getCPF(cursor);
		            String rua = cadastrarVendedor.getRua(cursor);
		            String numero = cadastrarVendedor.getNumero(cursor);
		            String cep = cadastrarVendedor.getCEP(cursor);
		            String estado = cadastrarVendedor.getEstado(cursor);
		            String cidade = cadastrarVendedor.getCidade(cursor);
		            String bairro = cadastrarVendedor.getBairro(cursor);
		            
		            Endereco endereco = new Endereco(rua, numero, cep, bairro, cidade, estado);
		            
		            Vendedor vend = new Vendedor(id, nome, telefone, rg, cpf, endereco, login, senha);
		         
		            cursor.close();
		            
					loginActivity.EntrarNoSistema(vend);
				}else{
					loginActivity.mostrarError("Vendedor não encontrado. Certifique-se se a senha e o login estão corretos");
					return;
				}
			}
		}catch (Exception e) {
			Log.e("Exception",Log.getStackTraceString(e.fillInStackTrace()));
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
