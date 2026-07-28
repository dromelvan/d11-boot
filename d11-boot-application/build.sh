#!/bin/sh
# Run ./gradlew :d11-boot-application:bootJar from repo root before running this script.

docker build -t d11/d11-boot-v4 .
docker image save -o d11-boot-v4.tar d11/d11-boot-v4
