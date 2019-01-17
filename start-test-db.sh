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

docker stop mysql2

docker run \
    --rm \
    -d \
    --name mysql2 \
    -p 3302:3306 \
    -e MYSQL_DATABASE=ds2 \
    -e MYSQL_ROOT_PASSWORD=beyondops1234 \
    -v $PWD/src/test/resources/ds2.sql:/docker-entrypoint-initdb.d/ds2.sql \
    mariadb:10.4


echo "Done"
