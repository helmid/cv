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

# Install required packages, to avoid executing `texliveonfly` during document creation
WORKDIR /
RUN mkdir test
ADD payload/dummyData test
RUN cd test && texliveonfly cv.tex
RUN cd / && rm -R test

# Copy app, keystores, expose port and set entrypoint
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build/libs/*.jar app.jar
RUN mkdir -p payload/cert
COPY payload/cert payload/cert
EXPOSE 8443
ENTRYPOINT ["java", "-jar", "app.jar"]