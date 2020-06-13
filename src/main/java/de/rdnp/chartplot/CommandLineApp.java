package de.rdnp.chartplot;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

/**
 * Experimental, only used to check new features
 */
public class CommandLineApp {

	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			System.out.println("Must provide at least one layer to print");
		}
		FlightRadarParser flightRadarParser = new FlightRadarParser();
		ChartPlotter chartPlotter = new ChartPlotter(480, 576);
		chartPlotter.setPlotColor(Color.BLACK);
		for (String layer: args) {
			GeoContent locations = flightRadarParser.parse(FileUtils.readFileToString(new File(layer), Charset.defaultCharset()));
			Chart layerChart = new Chart(new LinearProjection(48.8, 49.2, 9.2, 10), locations);
			  // TODO replace fixed locations
			BufferedImage plot = chartPlotter.plotChart(layerChart);
			chartPlotter.setBackground(plot);
			ImageIO.write(plot, "png", new File(layer.replace(".csv", ".png")));
			chartPlotter.setPlotColor(Color.RED);
		}
	}
}
