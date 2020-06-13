package de.rdnp.chartplot.io;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.rdnp.chartplot.io.FlightRadarParser;
import de.rdnp.chartplot.model.GeoContent;

public class FlightRadarParserTest {

	@Test
	public void parse_DefaultContent_PointsIncluded() {
		String input = "Timestamp,UTC,Callsign,Position,Altitude,Speed,Direction\r\n" + 
				"1588686878,2020-05-05T13:54:38Z,DESAE,\"48.865311,9.221543\",850,0,117\r\n" + 
				"1588687049,2020-05-05T13:57:29Z,DESAE,\"48.865093,9.221938\",850,5,111";
		FlightRadarParser parser = new FlightRadarParser();
		GeoContent content = parser.parse(input);
		assertEquals(2,content.getPoints().size());
		assertEquals(48.865311, content.getPoints().get(0).getLatitude(), 0.00001);
		assertEquals(9.221938, content.getPoints().get(1).getLongitude(), 0.00001);
	}
	

	@Test
	public void parse_ContentWithDescriptions_PointsIncluded() {
		String input = "Description,UTC,Callsign,Position,Altitude,Speed,Direction\r\n" + 
				"PointA,2020-05-05T13:54:38Z,DESAE,\"48.865311,9.221543\",850,0,117\r\n" + 
				"PointB,2020-05-05T13:57:29Z,DESAE,\"48.865093,9.221938\",850,5,111";
		FlightRadarParser parser = new FlightRadarParser();
		GeoContent content = parser.parse(input);
		assertEquals(2,content.getPoints().size());
		assertEquals(48.865311, content.getPoints().get(0).getLatitude(), 0.00001);
		assertEquals("PointA", content.getPoints().get(0).getName());
		assertEquals(9.221938, content.getPoints().get(1).getLongitude(), 0.00001);
		assertEquals("PointB", content.getPoints().get(1).getName());
	}
	
}
