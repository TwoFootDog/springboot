#FROM openjdk:8-jdk-alpine
FROM dep-dev-registry.cloudzcp.io/library/openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080/tcp
#ADD target/gs-spring-boot-docker-0.1.0.jar app.jar
ADD target/evaluate-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]