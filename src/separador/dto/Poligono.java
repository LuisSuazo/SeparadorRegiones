package separador.dto;

import java.awt.geom.Point2D;
import java.util.Vector;

public class Poligono {
	private Vector<Point2D> Puntos=new Vector<Point2D>();
	private String nombreComuna;
	private String codigoComuna;
	private double Area;
	private double xCentro;
	private double yCentro;
	private int numPol;
	
	public Poligono(Vector<Point2D> puntos,String nomCom, String codigoComuna, int numPol) {
		super();
		this.Puntos.addAll(puntos);
		this.numPol=numPol;
		this.nombreComuna=nomCom;
		this.codigoComuna=codigoComuna;
	}
	
	public Poligono(Vector<Point2D> puntos, int numPol, String nomCom, String codigoComuna) {
		super();
		this.Puntos.addAll(puntos);
		this.numPol=numPol;
		this.nombreComuna=nomCom;
		this.codigoComuna=codigoComuna;
		this.Calcula_Area();
		this.Calcula_Centro_x();
		this.Calcula_Centro_y();
	}

	public Vector<Point2D> getPuntos() {
		return Puntos;
	}
	
	private void Calcula_Centro_x(){
		double suma=0;
		Point2D punto;
		Point2D punto1;
		for(int i=0; i<this.Puntos.size()-1; i++){
			punto=this.Puntos.elementAt(i);
			punto1=this.Puntos.elementAt(i+1);
			suma+=(Math.abs(punto.getX())+Math.abs(punto1.getX()))*((Math.abs(punto.getX())*Math.abs(punto1.getY()))-(Math.abs(punto1.getX())*Math.abs(punto.getY())));
			//suma+=(punto.getx()+punto1.getx())*(punto.getx()*punto1.gety()-(punto1.getx()*punto.gety()));
		}
		this.xCentro= suma/(6*this.Area)*-1;
    }

	private void Calcula_Centro_y(){
		double suma=0;
		Point2D punto;
		Point2D punto1;
		for(int i=0; i<this.Puntos.size()-1; i++){
			punto= this.Puntos.elementAt(i);
			punto1= this.Puntos.elementAt(i+1);
			suma+=(Math.abs(punto.getY())+Math.abs(punto1.getY()))*((Math.abs(punto.getX())*Math.abs(punto1.getY()))-(Math.abs(punto1.getX())*Math.abs(punto.getY())));
		}
		this.yCentro= suma/(6*this.Area)*-1;
    }

	private void Calcula_Area(){
		double Areaaux=0;
		Point2D punto;
		Point2D punto1;
		for(int i=0; i<this.Puntos.size()-1; i++){
			punto= this.Puntos.elementAt(i);
			punto1= this.Puntos.elementAt(i+1);
			Areaaux+=((Math.abs(punto.getX())*Math.abs(punto1.getY()))-(Math.abs(punto1.getX())*Math.abs(punto.getY())));
		}
		this.Area= Areaaux/2;
    }

	public double getArea() {
		return Area;
	}

	public double getxCentro() {
		return xCentro;
	}

	public double getyCentro() {
		return yCentro;
	}

	public int getNumPol() {
		return numPol;
	}

	public String getNombreComuna() {
		return nombreComuna;
	}

	public String getCodigoComuna() {
		return codigoComuna;
	}

	public void setCodigoComuna(String codigoComuna) {
		this.codigoComuna = codigoComuna;
	}
	
}
