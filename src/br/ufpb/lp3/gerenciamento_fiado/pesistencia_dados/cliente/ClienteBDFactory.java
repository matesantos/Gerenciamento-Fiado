package br.ufpb.lp3.gerenciamento_fiado.pesistencia_dados.cliente;


public class ClienteBDFactory {
	
private static ClienteBD clienteBD;
	
	public static ClienteBD getVendedorBD() {
	    if (clienteBD == null) {
	    	clienteBD = new ClienteBD(null, null, null, 0);
	    }
	    return clienteBD;
	  }

}
