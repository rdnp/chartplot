package de.rdnp.chartplot.io;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ConfigurationProvider {

	public static enum LayerType {
		POINTS, PATH;

		public static LayerType fromName(String name) {
			if (name.equalsIgnoreCase("path")) {
				return PATH;
			}
			return POINTS;
		}
	}

	public static class LayerConfiguration {

		private File layerCsv;

		private Color color;

		private LayerType type;

		public LayerConfiguration(String layerCsvPath, String color, String type) {
			super();
			this.layerCsv = new File(layerCsvPath);
			if (!layerCsv.exists() || layerCsv.isDirectory()) {
				throw new IllegalStateException(
						"CSV file of layer does not exist or is a directory at \"" + layerCsvPath + "\"");
			}
			this.color = Color.decode(color);
			this.type = LayerType.fromName(type);
		}

		public File getLayerCsv() {
			return layerCsv;
		}

		public Color getColor() {
			return color;
		}

		public LayerType getType() {
			return type;
		}

	}

	public static class ChartConfiguration {

		private double minLatitude;

		private double maxLatitude;

		private double minLongitude;

		private double maxLongitude;

		private int outputWidth;

		public ChartConfiguration(String minLatitude, String maxLatitude, String minLongitude, String maxLongitude,
				String outputWidth) {
			super();
			this.minLatitude = Double.parseDouble(minLatitude);
			this.maxLatitude = Double.parseDouble(maxLatitude);
			this.minLongitude = Double.parseDouble(minLongitude);
			this.maxLongitude = Double.parseDouble(maxLongitude);
			this.outputWidth = Integer.parseInt(outputWidth);
		}

		public double getMinLatitude() {
			return minLatitude;
		}

		public double getMaxLatitude() {
			return maxLatitude;
		}

		public double getMinLongitude() {
			return minLongitude;
		}

		public double getMaxLongitude() {
			return maxLongitude;
		}

		public int getOutputWidth() {
			return outputWidth;
		}

		public int getOutputHeight() {
			double averageLatitude = (getMaxLatitude() - getMinLatitude()) / 2;
			averageLatitude += getMinLatitude();
			double widthRatio = (getMaxLatitude() - getMinLatitude()) / (getMaxLongitude() - getMinLongitude());
			return (int) Math.round(widthRatio * getOutputWidth() / Math.cos(Math.toRadians(averageLatitude)));
		}
	}

	private List<LayerConfiguration> layerConfiguration;

	private ChartConfiguration chartConfiguration;

	private ConfigurationProvider(List<LayerConfiguration> layerConfiguration, ChartConfiguration chartConfiguration) {
		super();
		this.layerConfiguration = layerConfiguration;
		this.chartConfiguration = chartConfiguration;
	}

	public static ConfigurationProvider loadFromCsv(String configurationCsv) {
		try (CSVReader reader = new CSVReader(new StringReader(configurationCsv))) {
			List<String[]> rawConfiguration = reader.readAll();
			List<String> header = Arrays.asList(rawConfiguration.remove(0));
			String[] firstLine = rawConfiguration.get(0);
			ChartConfiguration chartConfig = new ChartConfiguration(firstLine[header.indexOf("minLatitude")],
					firstLine[header.indexOf("maxLatitude")], firstLine[header.indexOf("minLongitude")],
					firstLine[header.indexOf("maxLongitude")], firstLine[header.indexOf("outputWidth")]);
			List<LayerConfiguration> layers = new ArrayList<>();
			for (String[] configLine : rawConfiguration) {
				layers.add(new LayerConfiguration(configLine[header.indexOf("csvPath")],
						configLine[header.indexOf("color")], configLine[header.indexOf("type")]));
			}
			return new ConfigurationProvider(layers, chartConfig);
		} catch (IOException | CsvException e) {
			e.printStackTrace();
			// TODO error handling for CSV content
			throw new RuntimeException(e);
		}
	}

	public List<LayerConfiguration> getLayerConfiguration() {
		return layerConfiguration;
	}

	public ChartConfiguration getChartConfiguration() {
		return chartConfiguration;
	}
}
