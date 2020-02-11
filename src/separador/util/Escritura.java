package separador.util;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import com.csvreader.CsvWriter;

import separador.dto.Poligono;
import separador.dto.Region;

public class Escritura {
	
	public void EscribirRegiones(String output,Vector<Region> Regiones,String filename) throws IOException {
		String Salida="Output/"+output+"/";
		File folder = new File(Salida);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		String SalidaCSV=Salida+filename+".csv";
		final CsvWriter csvOutput = this.InicializarCsvWriter(SalidaCSV); 
		Regiones.forEach(it -> {
			it.getPoligono().forEach(kt->{
				try {
					this.EscribirPolReg(csvOutput, kt.getPuntos(), it.getNomReg(),it.getCodReg(), kt.getNumPol());
					csvOutput.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
		});
		csvOutput.close();
	}
	
	private CsvWriter InicializarCsvWriter(String SalidaCSV) {
		CsvWriter csvOutput = null;
		try {
			csvOutput=new CsvWriter(new FileWriter(SalidaCSV), ';');
			csvOutput.write("STATE");
			csvOutput.write("IDSTATE");
			csvOutput.write("PUNTO X");
			csvOutput.write("PUNTO Y");
			csvOutput.write("NUMERO POLIGONO");
			csvOutput.endRecord();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return csvOutput;
	}


	private void EscribirPolReg(final CsvWriter csvOutput, Vector<Point2D> it,
			String NombreReg, String codRegion, Integer numPol) {
		it.forEach(gt -> {
			try {
				csvOutput.write(NombreReg);
				csvOutput.write(codRegion);
				csvOutput.write(String.valueOf(gt.getX()));
				csvOutput.write(String.valueOf(gt.getY()));
				csvOutput.write(String.valueOf(numPol));
				csvOutput.flush();
				csvOutput.endRecord();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	/**
	 *Metodo que escribe la cabezera del archivo KML
	 *@param pw PrintWriter del archivo que se esta escribiendo
	 *@param nombre String que contiene el nombre de la comuna
	 **/
	

	private void Escribir_Cabezera(PrintWriter pw,String nombre){
		pw.println("<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n" +
				"\t"+"<Document id=\"root_doc\">\n"+
				"\t\t"+"<Schema name=\"Poligonos\" id=\"Poligonos\">\n" +
				"\t\t\t"+"<SimpleField name=\"id\" type=\"int\"></SimpleField>\n" +
				"\t\t"+"</Schema>");
		Escribir_Style(pw);
		pw.println("\t\t"+"<Folder>\n+"
				+  "\t\t\t"+"<name>"+nombre+"</name>");
	}

	
	/**
	 *Metodo que escribe el cierre del archivo KML
	 *@param pw PrintWriter del archivo que se esta escribiendo
	 */
	private void Escribir_Cierre(PrintWriter pw){
		pw.print("\t\t"+"</Folder>\n" +
				"\t"+"</Document>\n" +
				"</kml>");
	}

	/**
	 * Metodo que escribe los Poligonos
	 * @param pw PrintWriter del archivo que se esta escribiendo
	 * @param Ptos Cuadrantes
	 **/    
	
	private void Escribir_folder(PrintWriter pw,String nombre){
		pw.println("\t\t"+"<Folder>\n+"
				+  "\t\t\t"+"<name>"+nombre+"</name>");
	}
	
	
	private void Escribir_Cierre_folder(PrintWriter pw){
		pw.print("\t\t"+"</Folder>\n");
	}

	private void Escribir_Poligonos(PrintWriter pw,Poligono Ptos){
		pw.print("\t\t\t"+"<Placemark>\n"+
				"\t\t\t\t"+"<name>"+Ptos.getNombreComuna()+"</name>\n" +
				"\t\t\t\t"+"<visibility>0</visibility>\n" +
				"\t\t\t\t"+"<styleUrl>#stylemap_id1</styleUrl>\n" +
				"\t\t\t\t"+"<ExtendedData><SchemaData schemaUrl=\"Poligonos\">\n" +
				"\t\t\t\t\t"+"<SimpleData name=\"id\">"+Ptos.getCodigoComuna()+"</SimpleData>\n" +
				"\t\t\t\t"+"</SchemaData></ExtendedData>\n" +
				"\t\t\t\t"+"<Polygon>\n" +
				"\t\t\t\t\t"+"<outerBoundaryIs>\n" +
				"\t\t\t\t\t\t"+"<LinearRing>\n" +
				"\t\t\t\t\t\t\t"+"<coordinates>\n");
		for(Point2D aux: Ptos.getPuntos()) {
			pw.print("\t\t\t\t\t\t\t\t"+aux.getY()+","+aux.getX()+"\n");
		}
		pw.print("\t\t\t\t\t\t\t"+"</coordinates>\n" +
				"\t\t\t\t\t\t"+"</LinearRing>\n" +
				"\t\t\t\t\t"+"</outerBoundaryIs>\n" +
				"\t\t\t\t"+"</Polygon>\n" +
				"\t\t\t"+"</Placemark>\n"
				);
	}

	/**
	 *Metodo que escribe el stilo del archivo KML
	 *@param pw PrintWriter del archivo que se esta escribiendo
	 **/

	private void Escribir_Style(PrintWriter pw){
		pw.print("\t\t"+"<StyleMap id=\"stylemap_id1\">\n" +
				"\t\t\t"+"<Pair>\n" +
				"\t\t\t\t"+"<styleUrl>#style</styleUrl>\n" +
				"\t\t\t"+"</Pair>\n" +
				"\t\t\t"+"<Pair>\n" +
				"\t\t\t\t"+"<key>highlight</key>\n" +
				"\t\t\t\t"+"<styleUrl>#style0</styleUrl>\n" +
				"\t\t\t"+"</Pair>\n" +
				"\t\t"+"</StyleMap>\n" +
				"\t\t"+"<Style id=\"style\">\n" +
				"\t\t\t"+"<LineStyle>\n" +
				"\t\t\t\t"+"<color>ffffffff</color>\n" +
				"\t\t\t"+"</LineStyle>\n" +
				"\t\t\t"+"<PolyStyle>\n" +
				"\t\t\t\t"+"<color>ff7fffff</color>\n" +
				"\t\t\t"+"</PolyStyle>\n" +
				"\t\t"+"</Style>\n" +
				"\t\t"+"<Style id=\"style0\">\n" +
				"\t\t\t"+"<LineStyle>\n" +
				"\t\t\t\t"+"<color>ffffffff</color>\n" +
				"\t\t\t"+"</LineStyle>\n" +
				"\t\t\t"+"<PolyStyle>\n" +
				"\t\t\t\t"+"<color>ff7fff55</color>\n" +
				"\t\t\t"+"</PolyStyle>\n" +
				"\t\t"+"</Style>\n"
				);
	}

	/**
	 * Metodo principal que escribe el archivo KML
	 * @param poligonos Lista de poligonos
	 * @see Escribir_Cierre
	 * @see Escribir_Cabezera
	 * @see Escribir_Style
	 **/  
	

	public void EscribirKML(String out,Vector<Region> Regiones, String filename){
		try{
			String Salida="Output/"+out+"/";
			File folder = new File(Salida);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			FileWriter fichero = new FileWriter(Salida+filename+".kml");
			final PrintWriter pw  = new PrintWriter(fichero);
			Escribir_Cabezera(pw,filename);
			Regiones.forEach(it -> {
				Escribir_folder(pw,it.getNomReg());
				it.getPoligono().forEach(us ->{
					Escribir_Poligonos(pw,us); 
				});
				Escribir_Cierre_folder(pw);
			});
			Escribir_Cierre(pw);
			pw.close();
			fichero.close();
		}catch (Exception e) {
			e.printStackTrace();
		} 

	}
	


}
