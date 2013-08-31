package br.ufpb.lp3.gerenciamento_fiado.persistencia_dados.vendedor;

import java.util.List;

import android.database.Cursor;
import br.ufpb.lp3.gerenciamento_fiado.models.Vendedor;


public interface VendedorDAO {
	
	  //nome da tabela do vendedor
	  public final static String tabelaVendedor = "vendedor";
	  
	  //nome das colunas
	  public final static String id = "id";
	  public final static String nome = "nome";
	  public final static String telefone = "telefone";
	  public final static String rg = "rg";
	  public final static String cpf = "cpf";
	  public final static String rua = "rua";
	  public final static String numero = "numero";
	  public final static String cep = "cep";
	  public final static String estado = "estado";
	  public final static String cidade = "cidade";
	  public final static String bairro = "bairro";
	  public final static String login = "login";
	  public final static String senha = "senha";
	
	  // Insere o Vendedor
	  public boolean salvar(Vendedor vendedor);
	  
	  // Atualiza o Vendedor
	  public boolean atualizar(Vendedor vendedor);

	  // Deleta o Vendedor
	  public boolean deletar(Vendedor vendedor);

	  // Busca o Vendedor pelo id
	  public Vendedor getVendedor(Long id);

	  // Retorna uma lista com todos os Vendedores
	  public List<Vendedor> listarVendedor();

	  // Busca o Vendedor pelo nome
	  public Vendedor buscarVendedorPorNome(String nome);
	  
	  public Cursor buscarTodosVendedores();
	  
	  public Cursor buscarVendedorPorLoginSenha(String login, String senha);
	  
	  //método para retornar os valores das colunas da tabela vendedor
	  public Long getID(Cursor c);
	  
	  public String getNome(Cursor c);
	  
	  public String getTelefone(Cursor c);
	  
	  public String getRG(Cursor c);
	  
	  public String getCPF(Cursor c);
	  
	  public String getRua(Cursor c);

	  public String getNumero(Cursor c); 

	  public String getCEP(Cursor c);

	  public String getEstado(Cursor c);
	  
	  public String getCidade(Cursor c);
	  
	  public String getBairro(Cursor c);
	  
	  public String getLogin(Cursor c);
	  
	  public String getSenha(Cursor c);
	  
	  
}
