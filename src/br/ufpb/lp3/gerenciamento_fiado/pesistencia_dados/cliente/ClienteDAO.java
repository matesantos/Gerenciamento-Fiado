package br.ufpb.lp3.gerenciamento_fiado.pesistencia_dados.cliente;

import java.util.List;

import android.database.Cursor;
import br.ufpb.lp3.gerenciamento_fiado.models.Cliente;

public interface ClienteDAO {
	 //nome da tabela do Cliente
	  public final static String tabelaCliente = "cliente";
	  
	  //nome das colunas
	  public final static String id = "id";
	  public final static String nome = "nome";
	  public final static String telefone = "telefone";
	  public final static String rg = "rg";
	  public final static String cpf = "cpf";
	  public final static String rua = "rua";
	  public final static String numero = "numero";
	  public final static String cep = "cep";
	  public final static String bairro = "bairro";
	  public final static String cidade = "cidade";
	  public final static String estado = "estado";
	
	  // Insere o Cliente
	  public boolean salvar(Cliente cliente);

	  // Atualizar os dados do Cliente
	  public boolean atualizar(Cliente cliente);

	  // Deleta o Cliente
	  public boolean deletar(Cliente cliente);

	  // Busca o Cliente pelo id
	  public Cliente getCliente(Long id);

	  // Retorna uma lista com todos os Clientees
	  public List<Cliente> listarCliente();

	  // Busca o Cliente pelo nome
	  public Cliente buscarClientePorNome(String nome);

	  
	  // Busca o Cliente pelo nome
	  public Cursor buscarTodosOsClientes();
	  
	  //método para retornar os valores das colunas da tabela cliente
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

}
