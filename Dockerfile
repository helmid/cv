FROM pandoc/latex
RUN apk update \
&& apk --no-cache add openjdk11

ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build/libs/*.jar app.jar
EXPOSE 8080