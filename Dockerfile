FROM openjdk:21-jdk
ARG JAR_FILE=./build/libs/GDGoC_2025_WhiteSheep-Server-0.0.1-SNAPSHOT.jar.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar", "/app.jar"]