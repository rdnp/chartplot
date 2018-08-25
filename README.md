Project Chartplot
=================

Goals
-----
Chartplot is a tool to plot geographic locations on a chart. The intention behind it is coming from my activities as a pilot. 
It is to plot the path of a flight monitored e.g. by radar or GPS or the output from a flight simulation session
onto an existing aviation chart. For instance, you can plot the points of your flight onto an approach plate to see how the
approach went during each phase. I personally find such a plot very useful especially for IFR training flights.

Using Chartplot
--------------------
Chartplot is a web application that can be deployed on a web server such as Tomcat. 

### Installation Requirements

Tomcat 8.4 was used during development.

### Usage Hints

Simply open the index page of the servlet and enter the values. Some example values are filled in.

A few hints:
+ to use an approach plate or a similar chart to plot onto, select that chart's PNG (on your local machine) as background image.
  The chart will be uploaded and used as a background to plot the flight path on. For this, width and height of the result image
  should be the same as the height and width of the background and the minimum and maximum latitude and longitude should exactly
  correspond to the values shown on the chart you want to print on.
+ the points can be outside the chart area, in this case they won't appear on the image. So you can simply paste the whole flight 
  path into the web UI and generate the chart not caring about filtering out points that are outside the area.
+ the points must be in the form "latitude;longitude;description" where description is an optional label printed next to the point.
  Each point must come in a new line. This is similar to a three-column CSV format, only that the separator is ";" instead of "," and no
  escape characters are supported. The latitude and longitude must be in decimal degree format.
  
Point input example:
> 49.4115;9.8133;Start Point
>
> 49.4118;9.8130
  
Project and Author
------------------
The project is currently in its initial phase, the software provided is an initial prototype.

### Planned Additions
+ Saving the background image with its latitude and longitude values on the server so that only the flight path has to be
uploaded each time a new plot should be created for the same chart
+ Different chart projections such as Lambert-conical to allow plotting on VFR charts
+ Generate custom charts, e.g. for user-defined approach procedures that can be trained in a simulator

You may contact me at richard.d.n.pohl (at) googlemail.com for any requests on the topic.
