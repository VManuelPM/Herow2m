FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=build/libs/w2m.jar
COPY ${JAR_FILE} w2m.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/w2m.jar"]