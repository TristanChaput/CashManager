FROM gradle:4.2.1-jdk8-alpine

USER root

WORKDIR /srv/app

COPY . /srv/app/

RUN rm -rf /srv/app/.gradle

ENV ANDROID_HOME /opt/android-sdk-linux

RUN apk update \
  && apk add ca-certificates wget \
  && update-ca-certificates

RUN mkdir -p ${ANDROID_HOME} && \
    cd ${ANDROID_HOME} && \
    wget -q https://dl.google.com/android/repository/sdk-tools-linux-3859397.zip -O android_tools.zip && \
    unzip android_tools.zip && \
    rm android_tools.zip

RUN yes | ${ANDROID_HOME}/tools/bin/sdkmanager --licenses

RUN ./gradlew build --stacktrace
