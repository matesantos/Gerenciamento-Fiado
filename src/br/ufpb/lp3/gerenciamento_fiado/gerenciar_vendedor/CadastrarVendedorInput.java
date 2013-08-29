package br.ufpb.lp3.gerenciamento_fiado.gerenciar_vendedor;

import java.util.HashMap;
import java.util.Map;

import br.ufpb.lp3.gerenciamento_fiado.BancoDados.OperacaoCrudInterface;
import br.ufpb.lp3.gerenciamento_fiado.models.Vendedor;

public class CadastrarVendedorInput {
	
	private Map<String, String> cadastrarVendedorInput;
	
	public CadastrarVendedorInput(Vendedor vendedor) {
		cadastrarVendedorInput = new HashMap<String, String>();
		cadastrarVendedorInput.put("operacao", OperacaoCrudInterface.salvarDados);
		cadastrarVendedorInput.put("id", String.valueOf(vendedor.getId()));
		cadastrarVendedorInput.put("nome",vendedor.getNome());
		cadastrarVendedorInput.put("rg",vendedor.getNome());
		cadastrarVendedorInput.put("cpf",vendedor.getCpf());
		cadastrarVendedorInput.put("telefone",vendedor.getTelefone());
		cadastrarVendedorInput.put("login",vendedor.getLogin());
		cadastrarVendedorInput.put("senha",vendedor.getSenha());
		cadastrarVendedorInput.put("rua",vendedor.getEndereco().getRua());
		cadastrarVendedorInput.put("numero",vendedor.getEndereco().getNumero());
		cadastrarVendedorInput.put("cep",vendedor.getEndereco().getCep());
		cadastrarVendedorInput.put("uf",vendedor.getEndereco().getEstado());
		cadastrarVendedorInput.put("cidade",vendedor.getEndereco().getCidade());
		cadastrarVendedorInput.put("bairro",vendedor.getEndereco().getBairro());
		
	}
	
	public Map<String,String> getInputMap() {
	   return(cadastrarVendedorInput);
	}

}
