package de.rdnp.chartplot.io;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import de.rdnp.chartplot.model.GeoContent;
import de.rdnp.chartplot.model.GeoPoint;

/**
 * Reads GeoContent from a CSV file downloaded from FlightRadar24
 */
public class FlightRadarParser {

	public GeoContent parse(String input) {
		List<GeoPoint> points = new ArrayList<>();
		try (CSVReader reader = new CSVReader(new StringReader(input))) {
			List<String[]> csvContent = reader.readAll();
			List<String> header = Arrays.asList(csvContent.remove(0));
			csvContent.forEach((line) -> {
				    String[] position = line[header.indexOf("Position")].split(",");
				    String description = "";
				    if (header.contains("Description")) {
				    	description = line[header.indexOf("Description")];
				    }
					points.add(new GeoPoint(position[0], position[1], description));
				});

		} catch (IOException | CsvException e) {
			e.printStackTrace();
			// TODO exception handling
			throw new RuntimeException(e);
		}
		return new GeoContent(points);
	}

}
