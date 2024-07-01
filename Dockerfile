FROM openjdk:17-alpine
MAINTAINER Elleined

# Docker MySQL Credentials
ENV MYSQL_HOST=mysql_server
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root
ENV MYSQL_PORT=3306
ENV MYSQL_DATABASE=pla_db
ENV PORT=8082

ADD ./target/*.jar philippine-location-api.jar
EXPOSE 8082
CMD ["java", "-jar", "philippine-location-api.jar"]