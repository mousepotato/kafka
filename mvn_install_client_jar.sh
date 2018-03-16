./gradlew clients:jar
cd clients/build/libs/
mvn install:install-file -Dfile=kafka-clients-1.0.0-cp1.jar -DgroupId=org.apache.kafka -DartifactId=kafka-clients -Dversion=1.0.0-cp1 -Dpackaging=jar
