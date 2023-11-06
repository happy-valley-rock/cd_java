#!/usr/bin/env bash

file="$(echo *.java)";
className="${file%.*}"

echo "Validate are the same version Java Compiler and Java Runtime";
javac -version;
java -version;

echo "Compiling...";
javac $file;

sleep 2;

echo "Running...";
java $className;
