#!/bin/bash

cd ..

mvn clean package
java -jar target/kafka-ignite-connector-example-*.jar
