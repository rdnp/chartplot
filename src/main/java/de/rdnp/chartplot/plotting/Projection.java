package de.rdnp.chartplot.plotting;

import de.rdnp.chartplot.model.GeoPoint;

/**
 * A projection projects GeoContent (locations on the earth) onto a chart (two-dimensional rectangle).
 */
public interface Projection {
    
    /**
     * @param geoPoint - the location on earth to be projected on the chart area, nonnull.
     * @return the ChartPoint representing the relative location of the point on the chart area.
     */
    public ChartPoint createChartPoint(GeoPoint geoPoint);
}
