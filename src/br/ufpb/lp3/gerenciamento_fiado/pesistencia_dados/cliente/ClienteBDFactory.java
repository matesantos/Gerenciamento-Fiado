package br.ufpb.lp3.gerenciamento_fiado.pesistencia_dados.cliente;

import android.content.Context;
import br.ufpb.lp3.gerenciamento_fiado.BancoDados.BancoDadosFiado;


public class ClienteBDFactory {
	
private static ClienteBD clienteBD;
	
	public static ClienteBD getClienteBD(Context context) {
	    if (clienteBD == null) {
	    	clienteBD = new ClienteBD(context, BancoDadosFiado.DATABASE_NAME, null, BancoDadosFiado.DATABASE_VERSION);
	    }
	    return clienteBD;
	  }

}
