package de.rdnp.chartplot.plotting;

/**
 * A point on a Chart. Has a relative (i.e. a double value within 0..1) horizontal and vertial position on the chart plane.  
 * It can also have an optional description.
 */
public class ChartPoint {

    private double relativeHorizontal;
    private double relativeVertical;
    private String description;

    /**
     * @param relativeHorizontal - the position of the point on the chart's horizontal axis, relative 
     *          from 0 (left) to 1 (right). Any value below zero indicates a point left of the chart's visible area, 
     *          any value above one is right of the chart's visible area.
     * @param relativeVertical - the position of the point on the chart's vertical axis, relative 
     *          from 0 (top) to 1 (bottom).  Any value below zero indicates a point above the chart's visible area, 
     *          any value above one is below the chart's visible area.
     * @param description - an optional description of the point, may be null.
     */
    public ChartPoint(double relativeHorizontal, double relativeVertical, String description) {
        this.relativeHorizontal = relativeHorizontal;
        this.relativeVertical = relativeVertical;
        this.description = description;
    }

    /**
     * @return the position of the point on the chart's horizontal axis, relative 
     *          from 0 (left) to 1 (right). Any value below zero indicates a point left of the chart's visible area, 
     *          any value above one is right of the chart's visible area.
     */
    public double getRelativeHorizontal() {
        return relativeHorizontal;
    }

    /**
     * @return relativeVertical - the position of the point on the chart's vertical axis, relative 
     *          from 0 (top) to 1 (bottom).  Any value below zero indicates a point above the chart's visible area, 
     *          any value above one is below the chart's visible area.
     */
    public double getRelativeVertical() {
        return relativeVertical;
    }

    /**
     * @return an optional description of the point, may be null.
     */
    public String getDescription() {
        return description;
    }
    
    /*
     * TODO implement / generate hashCode and equals methods. 
     */
}
