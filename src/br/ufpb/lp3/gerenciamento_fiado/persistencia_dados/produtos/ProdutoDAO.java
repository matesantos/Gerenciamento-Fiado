package br.ufpb.lp3.gerenciamento_fiado.persistencia_dados.produtos;

import java.util.List;

import br.ufpb.lp3.gerenciamento_fiado.models.Produto;

import android.database.Cursor;

public interface ProdutoDAO {
	
	  //nome da tabela do produto
	  public final static String tabelaProduto = "produto";
	  
	  //nome das colunas
	  public final static String codigo = "codigo";
	  public final static String nome = "nome";
	  public final static String preco = "preco";
	
	  // Insere o produto
	  public boolean salvar(Produto produto);
	  
	  // Atualiza o produto
	  public boolean atualizar(Produto produto);

	  // Deleta o produto
	  public boolean deletar(Produto produto);

	  // Busca o produto pelo id
	  public Cursor getProduto(Long id);

	  // Retorna uma lista com todos os produtoes
	  public List<Produto> listarproduto();

	  // Busca o produto pelo nome
	  public Cursor buscarprodutoPorNome(String nome);
	  
	  public Cursor buscarTodosOsProdutos();
	  
	  //método para retornar os valores das colunas da tabela produto
	  public Long getID(Cursor c);
	  
	  public String getNome(Cursor c);
	  
	  public String getPreco(Cursor c);
	  
}
