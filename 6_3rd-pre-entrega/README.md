# Simple POS System
> Project for Coderhouse

![GitHub top language](https://img.shields.io/github/languages/top/happy-valley-rock/cd_java/tree/master/6_3rd-pre-entrega?style=flat-square)

1. [Challenge](#challenge)
2. [Description](#description)
3. [Dependencies](#dependencies)
5. [Setup](#setup)
6. [Environment](#environment)
7. [DER](#der)


## Challenge
- [Desafio 6 - Ultima Pre-entrega](/challenges_descriptions/6_3rd-pre-entrega.md)


## Description
This project is just a simple billing system of product for a simple POS (Point of Sale) building with Spring boot and maven


## Setup
For build and setup this project is only necessary have
- [Java SE Runtime Environment (JRE) 1.8.0_202](https://www.oracle.com/ar/java/technologies/javase/javase8-archive-downloads.html)
- [Java SE Development Kit (JDK) 1.8.0_202](https://www.oracle.com/ar/java/technologies/javase/javase8-archive-downloads.html)
- [Apache Maven 3.9.5](https://maven.apache.org/download.cgi)

Then install dependencies and build the project (.jar) with maven in directory `/pom.xml`
```
mvn clean install
```
After that you can run the project with
```
mvn spring-boot:run
```

Also you can test all the services with the [Postman](./collection_postman/SimplePOS.postman_collection.json) collection in `/collection_postman`

## Environment

``` .properties
server.port=8000
debug=false

spring.datasource.url=jdbc:postgresql://<host>:5432/simple_pos
spring.datasource.username=<postgresql_username>
spring.datasource.password=<postgresql_password>
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
```

## Dependencies

| groupId                  | artifactId                   | version |
|--------------------------|------------------------------|---------|
| org.springframework.boot | spring-boot-starter-data-jpa | current |
| org.springframework.boot | spring-boot-starter-web      | current |
| org.projectlombok        | lombok                       | current |
| org.postgresql           | postgresql                   | current |
| org.modelmapper          | modelmapper                  | 3.2.0   |

## DER
