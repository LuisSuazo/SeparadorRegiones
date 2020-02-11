package separador.util;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Vector;

import separador.dto.Poligono;
import separador.dto.Region;

public class Lectura {

	public Lectura() {

	}

	public void LeerRegiones(String input,Vector<Region>  Regiones_UTM,Vector<Region>  Regiones_LATLON) throws IOException {
		//CsvReader archivo = new CsvReader(new InputStreamReader(new FileInputStream(input), "UTF-8"));
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(input), "UTF-8"));
		String strCurrentLine=in.readLine();
		while ((strCurrentLine = in.readLine()) != null) {
			String []archivo = strCurrentLine.split(";");
			Vector<Poligono> utm = new Vector<Poligono>();
			Vector<Poligono> latLon = new Vector<Poligono>();
			String Nombre="";
			String ID="";
			Nombre=archivo[2];
			ID=archivo[1];
			if(archivo[0].contains("MULTIPOLYGON")){
				archivo[0] = archivo[0].replace(")),((",";");
				String[] temp = archivo[0].split(";");
				for(int i = 0 ; i < temp.length ; i++) {
					Vector<Point2D> vector=new Vector<Point2D>();
					Vector<Point2D> vectorUTM=new Vector<Point2D>();
					archivo[0] = temp[i].replace("MULTIPOLYGON ","");
					archivo[0] = archivo[0].replace(" ",";");
					archivo[0] = archivo[0].replaceAll("[^-?\\d,.;]", "");
					String[] coordenada = archivo[0].split(",");
					for(String it : coordenada) {
						String[] auxCoordenada = it.split(";");
						String X = auxCoordenada[1];
						String Y = auxCoordenada[0];
						ArrayList<Double> COORD=Funciones.conv_latlon2utm(Double.parseDouble(X),Double.parseDouble(Y));
						Point2D coordUTM = new Point2D.Double(COORD.get(0),COORD.get(1));
						Point2D coordLat_Lon = new Point2D.Double(Double.parseDouble(X),Double.parseDouble(Y)); 
						vectorUTM.add(coordUTM);
						vector.add(coordLat_Lon);
						COORD.clear();
					}
					Poligono poligono_lat_lon=new Poligono(vector,Nombre,ID,i+1);
					Poligono poligono_utm=new Poligono(vectorUTM,Nombre,ID,i+1);
					latLon.add(poligono_lat_lon);
					utm.add(poligono_utm);
					vector.clear();
					vectorUTM.clear();
				}
				Region regionLatLon = new Region(latLon,Nombre,ID);
				Region regionUtm = new Region(utm,Nombre,ID);
				latLon.clear();
				utm.clear();
				Regiones_UTM.add(regionUtm);
				Regiones_LATLON.add(regionLatLon);
			}else{
				Vector<Point2D> vector=new Vector<Point2D>();
				Vector<Point2D> vectorUTM=new Vector<Point2D>();
				archivo[0] = archivo[0].replace("POLYGON ","");
				archivo[0] = archivo[0].replace(" ",";");
				archivo[0] = archivo[0].replaceAll("[^-?\\d,.;]", "");
				String[] temp = archivo[0].split(",");
				for(String it : temp) {
					String[] auxCoordenada = it.split(";");
					String X = auxCoordenada[1];
					String Y = auxCoordenada[0];
					ArrayList<Double> COORD=Funciones.conv_latlon2utm(Double.parseDouble(X),Double.parseDouble(Y));
					Point2D coordUTM = new Point2D.Double(COORD.get(0),COORD.get(1));
					Point2D coordLat_Lon = new Point2D.Double(Double.parseDouble(X),Double.parseDouble(Y)); 
					vector.add(coordLat_Lon);
					vectorUTM.add(coordUTM);
					COORD.clear();
				}
				Poligono poligono_lat_lon=new Poligono(vector,Nombre,ID,1);
				Poligono poligono_utm=new Poligono(vectorUTM,Nombre,ID,1);
				latLon.add(poligono_lat_lon);
				utm.add(poligono_utm);
				Region regionLatLon = new Region(latLon,Nombre,ID);
				Region regionUtm = new Region(utm,Nombre,ID);
				latLon.clear();
				utm.clear();
				Regiones_UTM.add(regionUtm);
				Regiones_LATLON.add(regionLatLon);
				vector.clear();
				vectorUTM.clear();
			}
		}
		in.close();
	}
}
