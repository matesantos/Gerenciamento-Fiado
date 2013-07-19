package br.ufpb.lp3.gerenciamento_fiado.gerenciar_contas;

import java.util.Arrays;
import java.util.List;

public class UltilsOpcoes {

	public static List<String> getListOpcao()
	{
		String[] list = {"Conta Vencidas", "Data de Hoje"};
		List<String> opcaoLista = Arrays.asList(list);
		
		return opcaoLista;
	}
	
}
