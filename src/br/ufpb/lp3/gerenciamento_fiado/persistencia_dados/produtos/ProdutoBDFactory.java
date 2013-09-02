package br.ufpb.lp3.gerenciamento_fiado.persistencia_dados.produtos;

import android.content.Context;
import br.ufpb.lp3.gerenciamento_fiado.BancoDados.BancoDadosFiado;

public class ProdutoBDFactory {
	
private static ProdutoBD produtoBD;
	
	public static ProdutoBD getProdutoBD(Context context) {
	    if (produtoBD == null) {
	    	produtoBD = new ProdutoBD(context, BancoDadosFiado.DATABASE_NAME, null, BancoDadosFiado.DATABASE_VERSION);
	    }
	    return produtoBD;
	  }	
	

}
