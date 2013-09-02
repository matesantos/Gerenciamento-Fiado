package br.ufpb.lp3.gerenciamento_fiado.mesagens;

public interface Mensagens {
	
	//mensagem para a tela login
	public final static String campoLoginSenhaVazio = "Os campo login e senha não podem estar vazios!";
	
	//mensagens para a tela de Cadastro/Atualização/Exclusão de Vendedor
	public final static String campoNomeVendedorVazio = "O campo Nome não pode estar vazio!";
	public final static String campoTelefoneVendedorVazio = "O campo Telefone não pode estar vazio!";
	public final static String campoRGVendedorVazio = "O campo RG não pode estar vazio!";
	public final static String campoCPFVendedorVazio = "O campo CPF não pode estar vazio!";
	public final static String campoLoginVendedorVazio = "O campo Login não pode estar vazio!";
	public final static String campoSenhaVendedorVazio = "O campo Senha não pode estar vazio!";
	public final static String campoRuaVendedorVazio = "O campo Endereço não pode estar vazio!";
	public final static String campoNumeroVendedorVazio = "O campo Número não pode estar vazio!";
	public final static String campoCEPVendedorVazio = "O campo CEP não pode estar vazio!";
	
	
	//mensagens para a tela de Cadastro/Atualização/Exclusão de Cliente
	public final static String campoNomeClienteVazio = "O campo Nome não pode estar vazio!";
	public final static String campoTelefoneClienteVazio = "O campo Telefone não pode estar vazio!";
	public final static String campoRGClienteVazio = "O campo RG não pode estar vazio!";
	public final static String campoCPFClienteVazio = "O campo CPF não pode estar vazio!";
	public final static String campoLoginClienteVazio = "O campo Login não pode estar vazio!";
	public final static String campoSenhaClienteVazio = "O campo Senha não pode estar vazio!";
	public final static String campoRuaClienteVazio = "O campo Endereço não pode estar vazio!";
	public final static String campoNumeroClienteVazio = "O campo Número não pode estar vazio!";
	public final static String campoCEPClienteVazio = "O campo CEP não pode estar vazio!";
	
	//mensagens relacionadas ao Banco de Dados
	public final static String loginSenhaExistentes  = "Esse login ou senha já existe, escolhe outro login.";

}
