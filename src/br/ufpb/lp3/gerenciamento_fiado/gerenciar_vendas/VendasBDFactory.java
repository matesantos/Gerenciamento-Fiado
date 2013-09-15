package br.ufpb.lp3.gerenciamento_fiado.gerenciar_vendas;

import br.ufpb.lp3.gerenciamento_fiado.BancoDados.BancoDadosFiado;
import android.content.Context;

public class VendasBDFactory {

	private static VendasBD vendas = null;
	
	public VendasBDFactory() {
	}
	
	public static VendasBD getVendasBD(Context context){
		
		if(vendas == null){
			vendas = new VendasBD(context, BancoDadosFiado.DATABASE_NAME, null, BancoDadosFiado.DATABASE_VERSION);
		}
		
		return vendas;
		
	}

}
