package de.rdnp.chartplot;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

/**
 * Plots a Chart onto an image of a defined width and height.
 */
public class ChartPlotter {

    private int    width;

    private int    height;

    private String backgroundImage;

    /**
     * Creates the ChartPlotter
     * @param width - the width of the image to plot to, in pixels.
     * @param height - the height of the image to plot to, in pixels.
     */
    public ChartPlotter(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Sets an optional background image to the image to plot.
     * @param backgroundImage - the background image, base-64-encoded in a String, nullable.
     */
    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    /**
     * Creates a BufferedImage with the chart plotted. Each ChartPoint is marked as a small red cirle with its description printed
     * next to it (if it has one).
     * 
     * @param chart - the Chart containing the data to be plotted.
     */
    public BufferedImage plotChart(Chart chart) {
        byte[] backgroundData = Base64.getDecoder().decode(backgroundImage.substring(backgroundImage.indexOf(",") + 1).getBytes());
        /*
         * TODO move the string operations somewhere else, this class should accept a bufferedImage or a byte[], not a string
         * 
         */
        BufferedImage background = null;
        try (ByteArrayInputStream in = new ByteArrayInputStream(backgroundData)) {
            background = ImageIO.read(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();

        Font font = new Font("Arial", Font.PLAIN, 14);
        graphics.setFont(font);

        graphics.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        graphics.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        graphics.setFont(font);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);
        if (background != null) {
            graphics.drawImage(background, 0, 0, null);
        }
        graphics.setColor(Color.RED);
        for (ChartPoint point : chart.getChartPoints()) {
            int horizontalPosition = (int)Math.round(point.getRelativeHorizontal() * width);
            int verticalPosition = height - (int)Math.round(point.getRelativeVertical() * height);
            graphics.drawRect(horizontalPosition - 1, verticalPosition - 1, 3, 3);

            if (point.getDescription() != null && !point.getDescription().isEmpty()) {
                graphics.drawString(point.getDescription(), horizontalPosition + 5f, verticalPosition + 5f);
            }
        }

        graphics.dispose();
        return image;
    }
}
