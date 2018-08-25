package de.rdnp.chartplot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple parser to parse a simplified CSV file with ";" separator into GeoContent. 
 * Does not support full CSV such as escape characters.
 */
public class GeoContentParser {
    
    /**
     * @param contentString the string, each point in a line in the form 
     *          "&lt;latitude&gt;;&lt;longitude&gt;;&lt;description&gt;" (separated by ";").
     *          The description part is optional. Parameter must be nonnull.
     * @return The GeoContent containing the points of the string.
     */
    public GeoContent parse(String contentString) {
        if (contentString == null) {
            return new GeoContent(Collections.emptyList());
        }
        
        List<GeoPoint> points = new ArrayList<>();
        
        String[] lines = contentString.split(System.getProperty("line.separator"));
        for (String pointLine: lines) {
            String[] properties = pointLine.split(";");
            if (properties.length >= 2) {
                GeoPoint linePoint = new GeoPoint(properties[0], properties[1], 
                  properties.length >= 3? properties[2] : "");
                points.add(linePoint);
            }
        }        
        
        return new GeoContent(points);
    }
}
