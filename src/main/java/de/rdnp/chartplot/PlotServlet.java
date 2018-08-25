package de.rdnp.chartplot;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet to plot a chart from various user inputs:
 * <ul>
 * <li>the chart area delimited by minLat, maxLat, minLon and maxLon in the south, north, west and east respectively.</li>
 * <li>the chart content as CSV-like string in the form</li>
 * <li>the size of the result image in pixels (width, height)</li>
 * <li>a background image as a base64 encoded string</li>
 * </ul>
 * Will return the chart image as a PNG download at the end.
 */
public class PlotServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GeoContent content = new GeoContentParser().parse(stringParameter(req, "content"));

        Projection linearProjection =
                                      new LinearProjection(doubleParameter(req, "minLat"), doubleParameter(req, "maxLat"),
                                                           doubleParameter(req, "minLon"), doubleParameter(req, "maxLon"));
        Chart chart = new Chart(linearProjection, content);
        ChartPlotter plotter = new ChartPlotter(intParameter(req, "width"), intParameter(req, "height"));
        plotter.setBackgroundImage(req.getParameter("background"));
        BufferedImage image = plotter.plotChart(chart);

        resp.setContentType("image/png");
        ImageIO.write(image, "PNG", resp.getOutputStream());

        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private String stringParameter(HttpServletRequest req, String string) {
        return req.getParameter(string) == null ? "" : req.getParameter(string);
    }

    private int intParameter(HttpServletRequest req, String key) {
        return Integer.parseInt(stringParameter(req, key));
    }

    private double doubleParameter(HttpServletRequest req, String key) {
        return Double.parseDouble(stringParameter(req, key));
    }
}
