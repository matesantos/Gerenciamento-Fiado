package br.ufpb.lp3.gerenciamento_fiado.persistencia_dados.vendedor;

import android.content.Context;
import br.ufpb.lp3.gerenciamento_fiado.BancoDados.BancoDadosFiado;


public class VendedorBDFactory {
	
	private static VendedorBD vendedorBD;
	
	public static VendedorBD getVendedorBD(Context context) {
	    if (vendedorBD == null) {
	    	vendedorBD = new VendedorBD(context, BancoDadosFiado.DATABASE_NAME, null, BancoDadosFiado.DATABASE_VERSION);
	    }
	    return vendedorBD;
	  }

}
