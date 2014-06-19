KIE Remote Example
==================

This is an example of remotely invoking a jBPM process running in a separate BPMS server using the REST APIs.

Requirements
------------

* Apache Maven 3.x (http://maven.apache.org)
* JBoss Fuse 6.x (http://www.jboss.org/products/fuse/overview/)
* JBoss BPM Suite 6.0.1 (http://www.jboss.org/products/bpmsuite/overview/)

Building Example
----------------

From the project root, run

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

Running in JBoss BPM Suite
---------------------

Create an application user and give them an "analyst" role

    <JBoss BPMS Home>/bin/add-user.sh

Copy the work item handler and service callback api jars into the business central lib folder

    cp <Project Root>/jbpm-wih/target/jbpm-wih-*.jar <JBoss BPMS Home>/standalone/deployments/business-central.war/WEB-INF/lib/
    cp <Project Root>/servicecb-api/target/servicecb-api-*.jar <JBoss BPMS Home>/standalone/deployments/business-central.war/WEB-INF/lib/

Start JBoss BPMS

    <JBoss BPMS Home>/bin/standalone.sh -c standalone-full.xml

Browse to the JBoss BPMS console (http://localhost:8080/business-central)

Import the Git repository (https://github.com/FuseByExample/kie-remote-example-jbpm)

Build & Deploy the project

WS Test
-------------------

There is a SoapUI test suite in <Project Root>/service-impl/src/test/soapui. It contains a test case for sending in a request as well as a mock service for receiving the async callback. Once a process is kicked off, you will need to log into the BPMS business central as the user you created with "analyst" role and complete the greeting task. Once completed, the BPM process will call back to the mock service hosted by SoapUI.
