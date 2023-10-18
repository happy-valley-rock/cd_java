#!/usr/bin/env bash

echo "Validate are the same version Java Compilar and Java";
javac -version;
java -version;

echo "Compiling...";
javac NumeroFlotanteDelgado.java;

sleep 2;

echo "Running...";
java NumeroFlotanteDelgado;
