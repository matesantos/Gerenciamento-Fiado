package br.ufpb.lp3.gerenciamento_fiado.persistencia_dados.vendedor;

import java.util.List;

import br.ufpb.lp3.gerenciamento_fiado.models.Vendedor;


public interface VendedorDAO {
	
	  //nome da tabela do vendedor
	  public final static String tabelaVendedor = "vendedor";
	  
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
	  public final static String login = "login";
	  public final static String senha = "senha";
	
	  // Insere ou atualiza o Vendedor
	  public boolean salvar(Vendedor carro);

	  // Deleta o Vendedor
	  public boolean deletar(Vendedor carro);

	  // Busca o Vendedor pelo id
	  public Vendedor getVendedor(Long id);

	  // Retorna uma lista com todos os Vendedores
	  public List<Vendedor> listarVendedor();

	  // Busca o Vendedor pelo nome
	  public Vendedor buscarVendedorPorNome(String nome);
}
