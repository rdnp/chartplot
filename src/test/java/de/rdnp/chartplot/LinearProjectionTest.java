package de.rdnp.chartplot;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinearProjectionTest {

    @Test
    public void projectLinearly_PointWithinArea_Success() {
        GeoPoint location = new GeoPoint(52, 8, "");
        LinearProjection projection = new LinearProjection(50, 54, 5, 9);
        
        ChartPoint chartPoint = projection.createChartPoint(location);
        assertEquals("", chartPoint.getDescription());
        assertEquals(.75, chartPoint.getRelativeHorizontal(), .005);
        assertEquals(.5, chartPoint.getRelativeVertical(), .005);
        
    }
}
