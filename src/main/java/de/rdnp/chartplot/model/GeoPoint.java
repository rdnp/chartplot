package de.rdnp.chartplot.model;

import java.util.Objects;

/**
 * Represents a location on earth.
 */
public class GeoPoint {
    
    private double latitude;
    private double longitude;
    private String name;
    
    /**
     * @param latitude - the latitude of the location as a double (decimal form in degrees, no minutes or seconds).
     *                    Positive values are north, negative values are south.
     * @param longitude - the longitode of the location as a double (decimal form in degrees, no minutes or seconds).
     *                    Positive values are east, negative values are west.
     * @param name - the name / description of the point, may be null.
     */
    public GeoPoint(double latitude, double longitude, String name) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }
    
    /**
     * @param latitude - the latitude of the location as a String representation of a double value ("." decimal separator)
     *                    (decimal form in degrees, no minutes or seconds).
     *                    Positive values are north, negative values are south, nonnull.
     * @param longitude - the longitode of the location as a String representation of a double value ("." decimal separator)
     *                    (decimal form in degrees, no minutes or seconds).
     *                    Positive values are east, negative values are west, nonnull.
     * @param name - the name / description of the point, may be null.
     */
    public GeoPoint(String latitude, String longitude, String name) {
        this.latitude = Double.parseDouble(latitude);
        this.longitude = Double.parseDouble(longitude);
        this.name = name;
    }
    
    /**
     * @return the latitude of the location as a double (decimal form in degrees, no minutes or seconds).
     *                    Positive values are north, negative values are south.
     */
    public double getLatitude() {
        return latitude;
    }
    
    /**
     * @return the longitode of the location as a double (decimal form in degrees, no minutes or seconds).
     *                    Positive values are east, negative values are west.
     */
    public double getLongitude() {
        return longitude;
    }
    
    /**
     * @return name - the name / description of the point, may be null.
     */
    public String getName() {
        return name;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude, name);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof GeoPoint) {
            GeoPoint that = (GeoPoint)obj;
            return Objects.equals(this.latitude,that.latitude) && Objects.equals(this.longitude, that.longitude) && Objects.equals(this.name, that.name);
        }
        return false;
    }
    
}
