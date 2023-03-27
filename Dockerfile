FROM openjdk:17
MAINTAINER diego.cl
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} balance-sales-app-0.0.1-SNAPSHOT.jar
ENTRYPOINT java -jar "/balance-sales-app-0.0.1-SNAPSHOT.jar"