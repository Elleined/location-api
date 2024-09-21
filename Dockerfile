FROM openjdk:17-alpine
ADD ./target/*.jar philippine-location-api.jar
EXPOSE 8082
CMD ["java", "-jar", "philippine-location-api.jar"]