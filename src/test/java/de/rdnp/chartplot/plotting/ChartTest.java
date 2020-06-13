package de.rdnp.chartplot.plotting;

import java.util.Collections;

import org.junit.Test;

import de.rdnp.chartplot.model.GeoContent;
import de.rdnp.chartplot.model.GeoPoint;
import de.rdnp.chartplot.plotting.Chart;
import de.rdnp.chartplot.plotting.LinearProjection;

import static org.junit.Assert.*;

public class ChartTest {

    @Test
    public void addPoints_Visible_Added() {
        Chart testChart = new Chart(new LinearProjection(4, 10, 4, 10), new GeoContent(Collections.singletonList(new GeoPoint(0,0,"null"))));
        assertEquals(0, testChart.getChartPoints().size());
        
        testChart.addGeoPoint(new GeoPoint(7, 7, "Mid"));
        
        assertEquals(1, testChart.getChartPoints().size());
        assertEquals("Mid",testChart.getChartPoints().get(0).getDescription());
        assertEquals(.5,testChart.getChartPoints().get(0).getRelativeHorizontal(), .01);
        assertEquals(.5,testChart.getChartPoints().get(0).getRelativeVertical(), .01);
    }

    @Test
    public void addPoints_Invisible_NotAdded() {
        Chart testChart = new Chart(new LinearProjection(4, 10, 4, 10), new GeoContent(Collections.emptyList()));
        assertEquals(0, testChart.getChartPoints().size());

        // Adding points outside of the chart area
        testChart.addGeoPoint(new GeoPoint(3, 7, "Left"));
        testChart.addGeoPoint(new GeoPoint(11, 7, "Right"));
        testChart.addGeoPoint(new GeoPoint(7, 3, "Bottom"));
        testChart.addGeoPoint(new GeoPoint(7, 11, "Top"));

        // No points should be added
        assertEquals(0, testChart.getChartPoints().size());
    }
}
