# Simple POS System
> Project for Coderhouse
>
> <a target="_blank" href="https://www.coderhouse.com">![Static Badge](https://img.shields.io/badge/coderhouse-d1e500)</a>

<a target="_blank" href="https://github.com/happy-valley-rock">![Static Badge](https://img.shields.io/badge/author-181717?logo=github)</a>
<a target="_blank" href="https://www.linkedin.com/in/esequieldelgado-developer">![Static Badge](https://img.shields.io/badge/linkedin-0A66C2?logo=github)</a>
![GitHub top language](https://img.shields.io/github/languages/top/happy-valley-rock/cd_java)

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
- [Java SE Development Kit (JDK) 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Apache Maven 3.9.5](https://maven.apache.org/download.cgi)
- [Node JS 18.16.0](https://nodejs.org);

You can make all the install with the script tool in `/tool-project.sh` and running too or follow the next steps.

Install dependencies and build the project with maven in directory `/pom.xml`
```
mvn clean install -Prod
```
After that you can run the project with
```
mvn spring-boot:run -Pprod
```


Also you can test all the services with the [Postman](./collection_postman/POS Simple.postman_collection.json) collection in `/collection_postman` or check the Swagger documentation in the uri `{host}/swagger-ui/index.html`
And for config the database (in PostgreSQL) you can use the SQL scripts in the directory `/db_queries`

```
schema.sql
tables.sql
seed.sql
setup-all.sql
```

## Environment

``` .properties - app(backend)
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

```.env - client(frontend)
API_HOST=http://localhost:8000
PORT=8001
LANGUAGE=en
ESLINT_IGNORE=false
GENERATE_SOURCEMAP=false
```

## Dependencies

| groupId                  | artifactId                          | version  |
|--------------------------|-------------------------------------|----------|
| org.springframework.boot | spring-boot-starter-data-jpa        | 3.2.0    |
| org.springframework.boot | spring-boot-starter-web             | 3.2.0    |
| org.projectlombok        | lombok                              | current  |
| org.postgresql           | postgresql                          | current  |
| org.modelmapper          | modelmapper                         | 3.2.0    |
| org.springdoc            | springdoc-openapi-starter-webmvc-ui | 2.0.2    |
| com.itextpdf             | itextpdf                            | 5.5.13.3 |
| com.github.eirslett      | frontend-maven-plugin               | 1.15.0   |

## DER

![der](./assets/der.png)
<img src="./assets/der.png" alt="DER-image" />
