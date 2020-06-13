package de.rdnp.chartplot.io;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Test;

import de.rdnp.chartplot.io.ConfigurationProvider.LayerType;

public class ConfigurationProviderTest {

	@Test
	public void loadFromCsv_ValidSampleCSV_ProviderCreated() {
		String sampleCsv = "csvPath,color,type,minLatitude,maxLatitude,minLongitude,maxLongitude,outputWidth\r\n" + 
				"./exists.csv,#ff0000,points,41,43,8,9,540\r\n" + 
				"./exists.csv,#0000ff,path";
	    ConfigurationProvider configuration = ConfigurationProvider.loadFromCsv(sampleCsv);
	    assertEquals(41, configuration.getChartConfiguration().getMinLatitude(), 0.00001);
	    assertEquals(43, configuration.getChartConfiguration().getMaxLatitude(), 0.00001);
	    assertEquals(8, configuration.getChartConfiguration().getMinLongitude(), 0.00001);
	    assertEquals(9, configuration.getChartConfiguration().getMaxLongitude(), 0.00001);
	    assertEquals(540, configuration.getChartConfiguration().getOutputWidth());
	    assertEquals(1453, configuration.getChartConfiguration().getOutputHeight());
	    assertEquals(2, configuration.getLayerConfiguration().size());
	    assertEquals("exists.csv", configuration.getLayerConfiguration().get(0).getLayerCsv().getName()); 
	    assertEquals(Color.red, configuration.getLayerConfiguration().get(0).getColor()); 
	    assertEquals("exists.csv", configuration.getLayerConfiguration().get(1).getLayerCsv().getName()); 
	    assertEquals(LayerType.PATH, configuration.getLayerConfiguration().get(1).getType()); 
	}

	@Test(expected=IllegalStateException.class)
	public void loadFromCsv_NonExistingFileSampleCSV_IllegalStateException() {
		String sampleCsv = "csvPath,color,type,minLatitude,maxLatitude,minLongitude,maxLongitude,outputWidth\r\n" + 
				"./notexists.csv,#ff0000,points,41,43,8,9,540\r\n" + 
				"./notexists.csv,#0000ff,path";
	    ConfigurationProvider.loadFromCsv(sampleCsv);
	}
	
}
