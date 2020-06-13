package de.rdnp.chartplot.plotting;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;

import de.rdnp.chartplot.io.ConfigurationProvider;
import de.rdnp.chartplot.io.FlightRadarParser;
import de.rdnp.chartplot.io.ConfigurationProvider.LayerConfiguration;
import de.rdnp.chartplot.model.GeoContent;

/**
 * Plots a multi-layered chart based on a CSV configuration.
 */
public class PlottingWorkflow {

	private ConfigurationProvider config;
	
	private FlightRadarParser contentParser;

	public PlottingWorkflow(ConfigurationProvider config, FlightRadarParser contentParser) {
		super();
		this.config = config;
		this.contentParser = contentParser;
	}

	public BufferedImage plotAllLayers() {
		ChartPlotter plotter = createPlotter();
		BufferedImage compositeImage = null;
		for (LayerConfiguration layer: config.getLayerConfiguration()) {
			GeoContent content = contentParser.parse(readCsv(layer.getLayerCsv()));
			Chart layerChart = new Chart(new LinearProjection(config.getChartConfiguration().getMinLatitude(),
					config.getChartConfiguration().getMaxLatitude(), config.getChartConfiguration().getMinLongitude(),
					config.getChartConfiguration().getMaxLongitude()), content);
			plotter.setPlotColor(layer.getColor());
			// TODO allow paths to be plotted
			compositeImage = plotter.plotChart(layerChart );
			plotter.setBackground(compositeImage);
		}
		return compositeImage;
	}

	protected String readCsv(File layerCsv) {
		try {
			return FileUtils.readFileToString(layerCsv, Charset.defaultCharset());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Could not read CSV file at "+layerCsv.getAbsolutePath(), e);
		}
	}

	protected ChartPlotter createPlotter() {
		return new ChartPlotter(config.getChartConfiguration().getOutputWidth(),
				config.getChartConfiguration().getOutputHeight());
	}

}
