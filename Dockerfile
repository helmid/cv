FROM pandoc/latex:latest
RUN apk update \
&& apk add --no-cache openjdk11

# Environment variables
ENV PYTHONUNBUFFERED=1
ENV USER="cvapp"
ENV GROUP="cvappgrp"
ENV HOME="/home/$USER"
ENV TEXLIVE_HOME="/opt/texlive/texdir/"
ENV APP_HOME="$HOME/app"

# Install python
RUN apk add --update --no-cache python3
RUN ln -sf "/usr/bin/python3" "/usr/bin/python"

# Install certbot
RUN apk add certbot

# Create user
RUN addgroup -S "$GROUP" && \
    adduser \
    --disabled-password \
    --gecos "" \
    --ingroup "$GROUP" "$USER"

WORKDIR $APP_HOME

# Copy certificates
COPY --chown=$USER:$GROUP payload/cert payload/cert

# Copy cv data
COPY --chown=$USER:$GROUP data/ data/

# Copy app
COPY --chown=$USER:$GROUP build/libs/*.jar app.jar

RUN chown -R $USER $TEXLIVE_HOME
RUN chown -R $USER:$GROUP $APP_HOME

# Change user
USER $USER

# Install texliveonfly
RUN tlmgr update --self \
&& tlmgr install texliveonfly

# Install pip
RUN export PATH="$HOME/.local/bin:$PATH" \
&& export PATH="/usr/bin/python3:$PATH" \
&& python3 -m ensurepip \
&& pip3 install --no-cache --upgrade pip setuptools

# Install required packages, to avoid executing `texliveonfly` during document creation
WORKDIR $HOME
RUN mkdir "test"
ADD payload/dummyData test
RUN cd "test" && texliveonfly cv.tex
RUN cd $HOME && rm -R test

# Expose port and set entrypoint
WORKDIR $APP_HOME
EXPOSE 8443
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]