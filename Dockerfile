FROM pandoc/latex:latest
RUN apk update \
&& apk add --no-cache openjdk11

# Install certbot
RUN apk add certbot

# Create user
ENV USER=cvapp
ENV GROUP=cvappgroup
RUN addgroup -S "$GROUP" && \
    adduser \
    --disabled-password \
    --gecos "" \
    --ingroup "$GROUP" "$USER"

# Install python
ENV PYTHONUNBUFFERED=1
RUN apk add --update --no-cache python3
RUN ln -sf "/run/python3" "/usr/bin/python"

# Install texliveonfly
RUN tlmgr update --self \
&& tlmgr install texliveonfly

# Change user
USER "$USER"

# Install pip
RUN export PATH="$HOME/.local/bin:$PATH" \
&& export PATH="/run/python3:$PATH" \
&& python3 -m ensurepip \
&& pip3 install --no-cache --upgrade pip setuptools

# Install required packages, to avoid executing `texliveonfly` during document creation
ENV HOME="/home/$USER"
WORKDIR $HOME
RUN mkdir "test"
ADD payload/dummyData test
RUN cd "test" && texliveonfly cv.tex
RUN cd / && rm -R test

# Set working directory and copy app
ENV APP_HOME="$HOME/cvapp/app/"
WORKDIR $APP_HOME
COPY build/libs/*.jar app.jar

# Copy certificates
RUN mkdir -p payload/cert
ADD payload/cert payload/cert

# Copy cv data
RUN mkdir data
ADD data/ data/

# Expose port and set entrypoint
EXPOSE 8443
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]