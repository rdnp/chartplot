package de.rdnp.chartplot.plotting;

import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import de.rdnp.chartplot.io.ConfigurationProvider;
import de.rdnp.chartplot.io.FlightRadarParser;
import de.rdnp.chartplot.model.GeoContent;
import de.rdnp.chartplot.model.GeoPoint;

public class PlottingWorkflowTest {

	@Test
	public void plotLayeredChart_ValidInput_ChartPlotted() {
		String sampleCsv = "csvPath,color,type,minLatitude,maxLatitude,minLongitude,maxLongitude,outputWidth\r\n" + 
				"./exists.csv,#ff0000,points,41,43,8,9,540\r\n" + 
				"./exists.csv,#0000ff,path";
	    ConfigurationProvider config = ConfigurationProvider.loadFromCsv(sampleCsv);
	    List<String> plottedLayers = new ArrayList<>();
	    PlottingWorkflow workflow = mockPlotter(config, plottedLayers);
		workflow.plotAllLayers();
		assertEquals(2, plottedLayers.size());
		assertEquals("540:1453:java.awt.Color[r=255,g=0,b=0]:1",plottedLayers.get(0));
		assertEquals("540:1453:java.awt.Color[r=0,g=0,b=255]:1",plottedLayers.get(1));
	}

	private PlottingWorkflow mockPlotter(ConfigurationProvider config, final List<String> plottedLayers) {
		FlightRadarParser mockLayerParser = new FlightRadarParser() {
			@Override
			public GeoContent parse(String input) {
				return new GeoContent(Collections.singletonList(new GeoPoint(42, 8.5, "mockPoint")));
			}
		};
		return new PlottingWorkflow(config, mockLayerParser) {
			@Override
			protected ChartPlotter createPlotter() {
				return new ChartPlotter(config.getChartConfiguration().getOutputWidth(), config.getChartConfiguration().getOutputHeight()) {
					@Override
					public BufferedImage plotChart(Chart chart) {
						plottedLayers.add(this.width+":"+this.height+":"+this.plotColor+":"+chart.getChartPoints().size());
						return null;
					}
				};
			}
			
			@Override
			protected String readCsv(File layerCsv) {
				return layerCsv.getName();
			}
		};
	}

}
