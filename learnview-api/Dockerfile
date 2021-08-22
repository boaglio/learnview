#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY load /home/app/load
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package 

#
# Package stage
#
FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /opt/app
COPY --from=build /home/app/target/learnview-1.0.0-SNAPSHOT.jar /usr/local/lib/demo.jar
EXPOSE 9000
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","/usr/local/lib/demo.jar"] 