# Start with a base image containing Java runtime
FROM java:8

#어떤 파일을 올릴건지
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar", "/app.jar"]

EXPOSE 8080

LABEL maintainer="hseol96@naver.com"




