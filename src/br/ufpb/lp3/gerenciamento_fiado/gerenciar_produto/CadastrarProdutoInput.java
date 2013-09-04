package br.ufpb.lp3.gerenciamento_fiado.gerenciar_produto;

import java.util.HashMap;
import java.util.Map;

import br.ufpb.lp3.gerenciamento_fiado.BancoDados.OperacaoCrudInterface;
import br.ufpb.lp3.gerenciamento_fiado.models.Produto;

public class CadastrarProdutoInput {
private Map<String, String> cadastrarProdutoInput;
	
	public CadastrarProdutoInput(Produto produto) {
		cadastrarProdutoInput = new HashMap<String, String>();
		cadastrarProdutoInput.put("operacao", OperacaoCrudInterface.salvarDados);
		cadastrarProdutoInput.put("nome",produto.getNome());
		cadastrarProdutoInput.put("preco",String.valueOf(produto.getPreco()));
		
	}
	
	public Map<String,String> getInputMap() {
	   return(cadastrarProdutoInput);
	}
}
