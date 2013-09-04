package br.ufpb.lp3.gerenciamento_fiado.gerenciar_cliente;

import java.util.HashMap;
import java.util.Map;

import br.ufpb.lp3.gerenciamento_fiado.BancoDados.OperacaoCrudInterface;
import br.ufpb.lp3.gerenciamento_fiado.models.Cliente;

public class CadastrarClienteInput {

private Map<String, String> cadastrarClienteInput;
	
	public CadastrarClienteInput(Cliente cliente) {
		cadastrarClienteInput = new HashMap<String, String>();
		cadastrarClienteInput.put("operacao", OperacaoCrudInterface.salvarDados);
		cadastrarClienteInput.put("nome",cliente.getNome());
		cadastrarClienteInput.put("rg",cliente.getRg());
		cadastrarClienteInput.put("cpf",cliente.getCpf());
		cadastrarClienteInput.put("telefone",cliente.getTelefone());
		cadastrarClienteInput.put("rua",cliente.getEndereco().getRua());
		cadastrarClienteInput.put("numero",cliente.getEndereco().getNumero());
		cadastrarClienteInput.put("cep",cliente.getEndereco().getCep());
		cadastrarClienteInput.put("uf",cliente.getEndereco().getEstado());
		cadastrarClienteInput.put("cidade",cliente.getEndereco().getCidade());
		cadastrarClienteInput.put("bairro",cliente.getEndereco().getBairro());
		
	}
	
	public Map<String,String> getInputMap() {
	   return(cadastrarClienteInput);
	}
}
