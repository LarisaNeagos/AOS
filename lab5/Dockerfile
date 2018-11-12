FROM openjdk:8
WORKDIR /app
EXPOSE 8080
COPY target/lab5-0.0.1-SNAPSHOT.jar /app/spring-boot-app.jar
ENTRYPOINT ["java","-jar","spring-boot-app.jar"]
