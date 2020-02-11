package separador;

import java.io.IOException;
import java.util.Vector;

import separador.dto.Region;
import separador.util.Escritura;
import separador.util.Lectura;

public class Main {
	
	public static void main(String args[]) throws IOException {
		Vector<Region> Regiones_UTM = new Vector<Region> ();
		Vector<Region>  Regiones_LATLON = new Vector<Region>();
		Lectura leer = new Lectura();
		Escritura escribir = new Escritura();
		leer.LeerRegiones("./Input/region.csv", Regiones_UTM, Regiones_LATLON);
		escribir.EscribirRegiones("Santiago_LatLon", Regiones_LATLON,"Chile_Regiones_latlon");
		escribir.EscribirKML("Santiago_LatLon_KML", Regiones_LATLON,"Chile_Regiones_latlon");
	}
}
