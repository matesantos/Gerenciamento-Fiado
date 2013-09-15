package br.ufpb.lp3.gerenciamento_fiado.gerenciar_vendas;

import java.util.List;

import android.database.Cursor;
import br.ufpb.lp3.gerenciamento_fiado.models.Venda;

public interface VendasDAOInterface {
	 //nome da tabela do vendedor
	  public final static String tabelaVendas = "vendas";
	  
	  //nome das colunas
	  public final static String id = "id";
	  public final static String cliente = "cliente";
	  public final static String produto = "produto";
	  public final static String valor = "valor";
	
	  // Insere o Vendedor
	  public boolean salvar(Venda venda);
	  
	  // Atualiza o Vendedor
	  public boolean atualizar(Venda venda);

	  // Deleta o Vendedor
	  public boolean deletar(Venda venda);

	  // Busca o Vendedor pelo id
	  public Venda getVenda(Long id);

	  // Retorna uma lista com todos os Vendedores
	  public List<Venda> listarVenda();

	  public Cursor buscarTodosVendas();
	  
	  //método para retornar os valores das colunas da tabela vendedor
	  public Long getID(Cursor c);
	  
	  public String getCliente(Cursor c);
	  
	  public String getProduto(Cursor c);
	  
	  public String getValor(Cursor c);
	  

}
