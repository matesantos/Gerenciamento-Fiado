package br.ufpb.lp3.gerenciamento_fiado.mesagens;

public interface Mensagens {
	
	//mensagem para a tela login
	public final static String campoLoginSenhaVazio = "Os campo login e senha não podem estarem vazios!";
	
	//mensagens para a tela de Cadastro/Atualização/Exclusão de Vendedor
	public final static String campoNomeVendedorVazio = "O campo Nome não pode estar vazio!";
	public final static String campoTelefoneVendedorVazio = "O campo Telefone não pode estar vazio!";
	public final static String campoRGVendedorVazio = "O campo RG não pode estar vazio!";
	public final static String campoCPFVendedorVazio = "O campo CPF não pode estar vazio!";

}
