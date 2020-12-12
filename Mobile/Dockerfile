FROM gradle:4.2.1-jdk8-alpine

USER root

WORKDIR /srv/app

COPY . /srv/app/

ENV ANDROID_HOME /opt/android-sdk-linux

ENV GLIBC_VERSION 2.32-r0

RUN echo "Installing standard dependencies..." \
  && apk add --no-cache --update --virtual=.build-dependencies \
    ca-certificates \
    wget \
  # ---------------------------------------------
  && echo "Installing glibc..." \
  && wget https://alpine-pkgs.sgerrand.com/sgerrand.rsa.pub -O /etc/apk/keys/sgerrand.rsa.pub \
  && wget https://github.com/sgerrand/alpine-pkg-glibc/releases/download/${GLIBC_VERSION}/glibc-${GLIBC_VERSION}.apk -O /tmp/glibc.apk \
  && wget https://github.com/sgerrand/alpine-pkg-glibc/releases/download/${GLIBC_VERSION}/glibc-bin-${GLIBC_VERSION}.apk -O /tmp/glibc-bin.apk \
  && apk add --no-cache /tmp/glibc.apk /tmp/glibc-bin.apk

RUN mkdir -p ${ANDROID_HOME} && \
    cd ${ANDROID_HOME} && \
    wget -q https://dl.google.com/android/repository/sdk-tools-linux-3859397.zip -O android_tools.zip && \
    unzip android_tools.zip && \
    rm android_tools.zip

RUN yes | ${ANDROID_HOME}/tools/bin/sdkmanager --licenses

RUN ./gradlew build --stacktrace --no-build-cache
