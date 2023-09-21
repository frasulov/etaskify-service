FROM openjdk:14-slim-buster

COPY build/libs/etaskify-service-0.0.1-SNAPSHOT-plain.jar .

ENTRYPOINT java -jar etaskify-service-0.0.1-SNAPSHOT-plain.jar