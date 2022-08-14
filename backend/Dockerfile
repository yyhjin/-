# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

#어떤 파일을 올릴건지
ARG JAR_FILE=build/libs/backend-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar", "/app.jar"]

EXPOSE 8080

#LABEL maintainer="hseol96@naver.com"




