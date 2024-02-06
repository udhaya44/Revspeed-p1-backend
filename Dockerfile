FROM openjdk:17-jdk-alpine

EXPOSE 9090

ARG JAR_FILE=target/githubaction-GKS-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar

ENTRYPOINT exec java -jar /app.jar