FROM openjdk:17

LABEL authors="sathishsakshi"

COPY target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]