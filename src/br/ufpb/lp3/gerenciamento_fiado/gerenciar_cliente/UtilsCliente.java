package br.ufpb.lp3.gerenciamento_fiado.gerenciar_cliente;

import java.util.Arrays;
import java.util.List;

public class UtilsCliente {

	public static List<String> getTelefoneList(){
    	String [] list = {"Casa","Celular","Trabalho","Fax do Trabalho", "Fax de casa"};
    	List<String> telefoneList = Arrays.asList(list);
    	
    	return telefoneList;
    }
	
}
