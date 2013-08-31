package br.ufpb.lp3.gerenciamento_fiado.utils;

public class ClasseGenerica <T>{
	
	T objeto;

	public ClasseGenerica(T objeto) {
		super();
		this.objeto = objeto;
	}

	public T getObjeto() {
		return objeto;
	}

	public void setObjeto(T objeto) {
		this.objeto = objeto;
	}
	
	

}
