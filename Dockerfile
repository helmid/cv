FROM pandoc/latex:latest
RUN apk update \
&& apk add --no-cache openjdk11

# Install python/pip
ENV PYTHONUNBUFFERED=1
RUN apk add --update --no-cache python3 && ln -sf python3 /usr/bin/python \
&& python3 -m ensurepip \
&& pip3 install --no-cache --upgrade pip setuptools

# Install texliveonfly
RUN tlmgr update --self \
&& tlmgr install texliveonfly


WORKDIR /
RUN mkdir test
ADD initialcompile test
RUN cd test && texliveonfly cv.tex
RUN cd / && rm -R test

ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]