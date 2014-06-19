CXF LB/Proxy Example
==================

Requirements
------------

* Apache Maven 3.x (http://maven.apache.org)
* JBoss Fuse 6.x (http://www.jboss.org/products/fuse/overview/)
* JBoss BPM Suite 6.0.1 (http://www.jboss.org/products/bpmsuite/overview/)

Building Example
----------------

From top level of example project directory, run

    mvn clean install

Running in JBoss Fuse
---------------------

Start JBoss Fuse

    <JBoss Fuse Home>/bin/fuse

From the JBoss Fuse console, enter the following to install the example application

    features:addurl mvn:org.fusebyexample.examples/kie-remote-example/1.0.0-SNAPSHOT/xml/features
    features:install kie-remote-example

To see what is happening within the JBoss Fuse server, you can continuously view the
log (tail) with the following command

    log:tail

WS Test
-------------------

Use your favorite WS testing tool (ie. SoapUI) and point it to http://localhost:9090/greetingService?wsdl. Send in multiple requests and you should see it load balance/proxy to the different implementations.
