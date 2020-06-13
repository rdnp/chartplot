package de.rdnp.chartplot;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import de.rdnp.chartplot.io.ConfigurationProvider;
import de.rdnp.chartplot.io.FlightRadarParser;
import de.rdnp.chartplot.plotting.PlottingWorkflow;

/**
 * Experimental, only used to check new features
 */
public class CommandLineApp {

	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			System.out.println("Must provide config file as first argument");
		}
		ConfigurationProvider config = ConfigurationProvider
				.loadFromCsv(FileUtils.readFileToString(new File(args[0]), Charset.defaultCharset()));
		PlottingWorkflow workflow = new PlottingWorkflow(config, new FlightRadarParser());
		ImageIO.write(workflow.plotAllLayers(), "png", new File(args[0].replace(".csv", ".png")));
	}
}
