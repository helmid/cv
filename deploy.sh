#!/bin/bash

username=""
repository=""
version=""

while getopts u:r:v: flag
do
    case "${flag}" in
        u) username=${OPTARG};;
        r) repository=${OPTARG};;
        v) version=${OPTARG};;
    esac
done

if [ -z $username ]
then
  echo "Username '-u value' is mandatory."
fi
if [ -z $repository ]
then
  echo "Repository '-r value' is mandatory."
fi
if [ -z $version ]
then
  echo "Version '-v value' is mandatory."
fi

if [ -n "$username" ] && [ -n "$repository" ] && [ -n "$version" ]
then
  docker pull $username/$repository:$version
  docker container kill $(docker container ls -q)
  docker run -d -p 443:8443 -p 80:8080 $username/$repository:$version
fi