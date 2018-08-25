package de.rdnp.chartplot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeoContentParserTest {
    
    @Test
    public void parse_NoPoints_Success() {
        GeoContentParser testSubject = new GeoContentParser();
        GeoContent content = testSubject.parse("");
        assertEquals(Collections.emptyList(),content.getPoints());
    }
    
    
    @Test
    public void parse_OnePoint_Success() {
        GeoContentParser testSubject = new GeoContentParser();
        GeoContent content = testSubject.parse(createOnePoint());
        assertEquals(Collections.singletonList(new GeoPoint("49", "8", "test")),content.getPoints());
    }

    private String createOnePoint() {
        String onePoint = "49;8;test";
        return onePoint;
    }
    
    
    @Test
    public void parse_TwoPoints_Success() {
        GeoContentParser testSubject = new GeoContentParser();
        GeoContent content = testSubject.parse(createTwoPoints());
        List<GeoPoint> testPointList = new ArrayList<GeoPoint>();
        testPointList.add(new GeoPoint(49,8,"test"));
        testPointList.add(new GeoPoint(50,9,"testAnother"));
        assertEquals(testPointList,content.getPoints());
    }

    private String createTwoPoints() {
        String onePoint = "49;8;test";
        String anotherPoint = "50;9;testAnother";
        return onePoint + System.getProperty("line.separator") + anotherPoint;
    }
}
