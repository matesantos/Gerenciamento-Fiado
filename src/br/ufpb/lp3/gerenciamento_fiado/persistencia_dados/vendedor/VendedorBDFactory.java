package br.ufpb.lp3.gerenciamento_fiado.persistencia_dados.vendedor;


public class VendedorBDFactory {
	
	private static VendedorBD vendedorBD;
	
	public static VendedorBD getVendedorBD() {
	    if (vendedorBD == null) {
	    	vendedorBD = new VendedorBD(null, null, null, 0);
	    }
	    return vendedorBD;
	  }

}
