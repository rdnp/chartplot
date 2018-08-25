package de.rdnp.chartplot;

import java.util.List;

/**
 * Combines several locations on the earth to an object.
 */
public class GeoContent {
    
    private List<GeoPoint> points ;
    
    /**
     * @param points some locations to form a logical object on the earth, nonnull.
     */
    public GeoContent(List<GeoPoint> points) {
        this.points = points;
    }
    
    /**
     * @return the single locations of this GeoContent, nonnull.
     */
    public List<GeoPoint> getPoints() {
        return points;
    }
    
    /*
     * TODO implement hashCode / equals methods
     */
}
