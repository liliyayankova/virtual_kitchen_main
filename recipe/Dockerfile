FROM openjdk:8-jdk-alpine
EXPOSE 8085
ADD target/recipe-service.jar recipe-service.jar
ENTRYPOINT ["java","-jar","/recipe-service.jar"]