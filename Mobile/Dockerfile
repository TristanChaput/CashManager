FROM maven:alpine

RUN mkdir -p /usr/src/app

WORKDIR /usr/src/app

COPY. /usr/src/app

RUN mvn clean package

VOLUME["/kotlin-data"]

EXPOSE 8082

CMD["java", "-jar", "target/mobile.jar"]
