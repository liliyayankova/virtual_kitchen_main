FROM openjdk:8-jdk-alpine
EXPOSE 8086
ADD target/kitchen-service.jar kitchen-service.jar
ENTRYPOINT ["java","-jar","/kitchen-service.jar"]