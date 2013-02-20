# Swagger Sample App

## Overview
This is an example of swagger integration with the [RESTEasy](http://www.jboss.org/resteasy) JAX-RS framework from
[JBoss](http://www.jboss.org/). It's a simple, modified example from the RESTEasy 2.3.5 release.

### To run (with Maven)
To run the server, run this task:
<pre>
mvn package -Dlog4j.configuration=file:./log4j.properties jetty:run
</pre>

This will start Jetty embedded on port 9095 and apply the logging configuration from ./log4j.properties

### Testing the server
Once started, you can navigate to http://localhost:9095/resteasy/api-docs to view the Swagger Resource Listing.
This tells you that the server is up and ready to demonstrate Swagger.

### Using the UI
There is an HTML5-based API tool available in a separate project. This lets you inspect the API using an
intuitive UI. You can pull this code from here: https://github.com/wordnik/swagger-ui

You can then open the dist/index.html file in any HTML5-enabled browser. Open opening, enter the
URL of your server in the top-centered input box. Click the "Explore"
button and you should see the resources available on the server.
