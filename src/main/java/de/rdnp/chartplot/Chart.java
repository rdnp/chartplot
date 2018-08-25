package de.rdnp.chartplot;

import java.util.ArrayList;
import java.util.List;

/**
 * A chart that accepts GeoPoints to be added to it. Has a projection (e.g. linear, Lambert conical, ...) 
 * and maintains the added points as ChartPoint instances, i.e. with x and y coordinates on the chart as a plane.
 */
public class Chart {

    private Projection       projection;

    private List<ChartPoint> chartPoints;

    /**
     * @param projection - the Projection the chart uses to transform GeoPoints into coordinates on a 2D plane, nonnull.
     * @param content - the content of the chart, nonnull.
     */
    public Chart(Projection projection, GeoContent content) {
        this.projection = projection;
        this.chartPoints = new ArrayList<ChartPoint>();
        for (GeoPoint point : content.getPoints()) {
            addGeoPoint(point);
        }
    }

    /**
     * @param point - a GeoPoint of a location to add to the chart, nonnull.
     */
    public void addGeoPoint(GeoPoint point) {
        ChartPoint chartPoint = projection.createChartPoint(point);
        if (withinChartArea(chartPoint)) {
            this.chartPoints.add(chartPoint);
        }
    }

    /**
     * Checks if a point is within the visible area of the chart that extends from 0 to 1 on both axis.
     */
    private boolean withinChartArea(ChartPoint chartPoint) {
        return chartPoint.getRelativeHorizontal() > 0 && chartPoint.getRelativeVertical() > 0 && chartPoint.getRelativeHorizontal() < 1
               && chartPoint.getRelativeVertical() < 1;
    }

    /**
     * @return the list of ChartPoint instances maintained by this chart.
     */
    public List<ChartPoint> getChartPoints() {
        return chartPoints;
    }

    /**
     * TODO consider implementing or generating hashCode / equals methods
     */

}
