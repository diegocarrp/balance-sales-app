FROM openjdk:17
MAINTAINER diego.cl
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} /app/balance-sales-app-0.0.1-SNAPSHOT.jar
WORKDIR /app
ENTRYPOINT java -jar "/app/balance-sales-app-0.0.1-SNAPSHOT.jar"