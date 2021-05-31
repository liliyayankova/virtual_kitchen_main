FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD target/main-service.jar main-service.jar
ENTRYPOINT ["java","-jar","/main-service.jar"]