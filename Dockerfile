FROM openjdk:latest
COPY ./target/ JAR
WORKDIR /tmp
ENTRYPOINT ["java", "JAR"]