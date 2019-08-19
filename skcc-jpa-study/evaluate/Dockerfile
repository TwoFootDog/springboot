#FROM openjdk:8-jdk-alpine
FROM dep-dev-registry.cloudzcp.io/library/openjdk:8-jdk-alpine
VOLUME /tmp
ADD ./build/libs/evaluate-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=dev","-jar","/app.jar"]