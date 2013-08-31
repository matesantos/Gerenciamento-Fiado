package br.ufpb.lp3.gerenciamento_fiado.mesagens;

public interface Mensagens {
	
	//mensagem para a tela login
	public final static String campoLoginSenhaVazio = "Os campo login e senha n�o podem estar vazios!";
	
	//mensagens para a tela de Cadastro/Atualiza��o/Exclus�o de Vendedor
	public final static String campoNomeVendedorVazio = "O campo Nome n�o pode estar vazio!";
	public final static String campoTelefoneVendedorVazio = "O campo Telefone n�o pode estar vazio!";
	public final static String campoRGVendedorVazio = "O campo RG n�o pode estar vazio!";
	public final static String campoCPFVendedorVazio = "O campo CPF n�o pode estar vazio!";
	public final static String campoLoginVendedorVazio = "O campo Login n�o pode estar vazio!";
	public final static String campoSenhaVendedorVazio = "O campo Senha n�o pode estar vazio!";
	public final static String campoRuaVendedorVazio = "O campo Endere�o n�o pode estar vazio!";
	public final static String campoNumeroVendedorVazio = "O campo N�mero n�o pode estar vazio!";
	public final static String campoCEPVendedorVazio = "O campo CEP n�o pode estar vazio!";
	
	
	//mensagens relacionadas ao Banco de Dados
	public final static String loginSenhaExistentes  = "Esse login ou senha j� existe, escolhe outro login.";

}
