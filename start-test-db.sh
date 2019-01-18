#!/bin/bash


echo "Start mysql1"

docker stop mysql1

docker run \
    --rm \
    -d \
    --name mysql1 \
    -p 3301:3306 \
    -e MYSQL_DATABASE=ds1 \
    -e MYSQL_ROOT_PASSWORD=beyondops1234 \
    -v $PWD/src/test/resources/ds1.sql:/docker-entrypoint-initdb.d/ds1.sql \
    mariadb:10.4

docker stop postgres1

docker run \
    --rm \
    -d \
    --name postgres1 \
    -p 5432:5432 \
    -e POSTGRES_DB=ds2 \
    -e POSTGRES_PASSWORD=beyondops1234 \
    -v $PWD/src/test/resources/ds2.sql:/docker-entrypoint-initdb.d/ds2.sql \
    postgres:11-alpine


echo "Done"
