package br.ufpb.lp3.gerenciamento_fiado.operacao_crud;

import java.util.HashMap;
import java.util.Map;

//classe que receber� indica��o de qual operecao ser� realizado remotamente com o json
public class OperacaoCrud {
private Map<String, Integer> operacaoInput;
	
	public OperacaoCrud(int op) {
		operacaoInput = new HashMap<String, Integer>();
		operacaoInput.put("op", op);
	}
	
	 public Map<String, Integer> getInputMap() {
	        return(operacaoInput);
	    }
}
