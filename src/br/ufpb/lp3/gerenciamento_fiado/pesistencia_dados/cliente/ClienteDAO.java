package br.ufpb.lp3.gerenciamento_fiado.pesistencia_dados.cliente;

import java.util.List;

import br.ufpb.lp3.gerenciamento_fiado.models.Cliente;

public interface ClienteDAO {
	 //nome da tabela do Cliente
	  public final static String tabelaCliente = "cliente";
	  
	  //nome das colunas
	  public final static String id = "id";
	  public final static String nome = "nome";
	  public final static String telefone = "nome";
	  public final static String rg = "rg";
	  public final static String cpf = "cpf";
	  public final static String rua = "rua";
	  public final static String numero = "numero";
	  public final static String cep = "cep";
	  public final static String bairro = "bairro";
	  public final static String cidade = "cidade";
	  public final static String estado = "estado";
	
	  // Insere ou atualiza o Cliente
	  public boolean salvar(Cliente carro);

	  // Deleta o Cliente
	  public boolean deletar(Cliente carro);

	  // Busca o Cliente pelo id
	  public Cliente getCliente(Long id);

	  // Retorna uma lista com todos os Clientees
	  public List<Cliente> listarCliente();

	  // Busca o Cliente pelo nome
	  public Cliente buscarClientePorNome(String nome);

}
