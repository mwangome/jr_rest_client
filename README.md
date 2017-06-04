# jr_rest_client

This client utilises jr_rest_client 6.3.1 (jar with dependencies) to run a client on Tomcat 8.
Install a local jar with the command 
mvn install:install-file -Durl=lib/ -Dfile=lib/jrs-rest-java-client-6.3.1-jar-with-dependencies.jar -DgroupId=jr-rest-client -DartifactId=com.jrrestclient -Dpackaging=jar -Dversion=1.0
Then access it on POM by adding the dependency as shown in this project's POM.
