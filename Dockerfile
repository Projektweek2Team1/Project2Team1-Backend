FROM quay.io/zenlab/openjdk:13
VOLUME /tmp
EXPOSE 8000
RUN mkdir -p /app/
RUN mkdir -p /app/logs/
ADD target/demo-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container", "-jar", "/app/app.jar"]

