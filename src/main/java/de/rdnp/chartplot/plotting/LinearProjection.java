package de.rdnp.chartplot.plotting;

import de.rdnp.chartplot.model.GeoPoint;

/**
 * Projects the part of the earth that is between a particular latitude and longitude linearly onto a plane.
 */
public class LinearProjection implements Projection {

    private double minLatitude;
    
    private double maxLatitude;

    private double minLongitude;
    
    private double maxLongitude;

    /**
     * @param minLatitude - southern limit of the chart area.
     * @param maxLatitude - northern limit of the chart area.
     * @param minLongitude - western limit of the chart area.
     * @param maxLongitude - eastern limit of the chart area.
     */
    public LinearProjection(double minLatitude, double maxLatitude, double minLongitude, double maxLongitude) {
        this.minLatitude = minLatitude;
        this.maxLatitude = maxLatitude;
        this.minLongitude = minLongitude;
        this.maxLongitude = maxLongitude;
    }

    @Override
    public ChartPoint createChartPoint(GeoPoint geoPoint) {
        return new ChartPoint((geoPoint.getLongitude() - minLongitude) / (maxLongitude - minLongitude), 
          (geoPoint.getLatitude() - minLatitude) / (maxLatitude - minLatitude), geoPoint.getName());
    }
    
}
