FROM alpine/java:21-jdk
ADD ./target/*.jar philippine-location-api.jar
EXPOSE 8082
CMD ["java", "-jar", "philippine-location-api.jar"]