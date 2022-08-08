# Start with a base image containing Java runtime
FROM java:8

#어떤 파일을 올릴건지
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar", "/app.jar"]
#Add Author name


#CMD ["./gradlew", "bootrun"]
##Add a volume to /tmp
#VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

LABEL maintainer="hseol96@naver.com"


#
## Add the application's jar to the container
#ADD ${JAR_FILE} test-springboot.jar



