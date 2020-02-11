package separador.dto;

import java.util.Vector;

public class Region {
	private Vector<Poligono> poligono = new Vector<Poligono>();
	private String nomReg;
	private String codReg;
	
	public Region(Vector<Poligono> poligono, String nomReg, String codReg) {
		super();
		this.poligono.addAll(poligono);
		this.nomReg = nomReg;
		this.codReg = codReg;
	}

	public Vector<Poligono> getPoligono() {
		return poligono;
	}
	
	public String getNomReg() {
		return nomReg;
	}
	
	
	public String getCodReg() {
		return codReg;
	}

}
