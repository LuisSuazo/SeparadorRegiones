package separador.util;

import java.util.ArrayList;

import geotransform.coords.Gdc_Coord_3d;
import geotransform.coords.Utm_Coord_3d;
import geotransform.coords.Mercator_Coord_3d;
import geotransform.ellipsoids.WE_Ellipsoid;
import geotransform.transforms.Gdc_To_Utm_Converter;
import geotransform.transforms.Mercator_To_Gdc_Converter;
import geotransform.transforms.Utm_To_Gdc_Converter;

public class Funciones {
	
	public static ArrayList<Double> conv_merc2gdc(double x, double y) {
		ArrayList<Double> lat_lon = new ArrayList<>();
		Gdc_Coord_3d gdc = new Gdc_Coord_3d();
		Mercator_Coord_3d mdc = new Mercator_Coord_3d(x,y,(byte) 19,false);
		Mercator_To_Gdc_Converter.Init(new WE_Ellipsoid());
		Mercator_To_Gdc_Converter.Convert(mdc, gdc);
		lat_lon.add(gdc.latitude);
		lat_lon.add(gdc.longitude);
		return lat_lon;
	}
	
	public static ArrayList<Double> conv_utm2latlon(double x, double y) {

		ArrayList<Double> lat_lon = new ArrayList<>();
		// Utm_To_Gdc_Converter.Convert(utm,gdc);
		Gdc_Coord_3d gdc = new Gdc_Coord_3d();
		Utm_Coord_3d utm = new Utm_Coord_3d(x, // x utm
				y, // y utm
				0, // z utm
				(byte) 19, // zona
				false); // hemisferio norte
		Utm_To_Gdc_Converter.Init(new WE_Ellipsoid());
		Utm_To_Gdc_Converter.Convert(utm, gdc);
		lat_lon.add(gdc.latitude);
		lat_lon.add(gdc.longitude);
		return lat_lon;
	}

	// Convertir coordenadas a UTM
	public static ArrayList<Double> conv_latlon2utm(Double latitud, Double longitud) {

		Gdc_Coord_3d puntoLatLon = new Gdc_Coord_3d(latitud, longitud, 100.0);
		Utm_Coord_3d puntoUtm = new Utm_Coord_3d();
		Gdc_To_Utm_Converter.Init(new WE_Ellipsoid());
		Gdc_To_Utm_Converter.Convert(puntoLatLon, puntoUtm);
		ArrayList<Double> utmXY = new ArrayList<>();
		utmXY.add(puntoUtm.x);
		utmXY.add(puntoUtm.y);
		return utmXY;
	}
}
