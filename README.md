# Java JPA ORM Test

**Java JPA ORM Test** is a test using a JPA, ORM (EclipseLink) and HttpServlets to read and write to a SQL database.

This project is written in [Java](https://www.oracle.com/java/) using a [PostgreSQL](https://www.postgresql.org/) database and [EclipseLink](http://www.eclipse.org/eclipselink/#download) as a Java (ORM) persistence solution.

This strictly educational, proof of concept side project can be found in the Learning Java Web Development course at [O'Reilly](http://shop.oreilly.com/product/0636920048831.do), under "JPA ORM Exercise".

## Table of contents

* [Quick start](#quick-start)
* [What's included](#whats-included)
* [Contributors](#contributors)


## Quick start

Here's what you need to do to view this project:

1. Install [JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html), the [Apache Tomcat](http://tomcat.apache.org/download-70.cgi) web server and the [Apache Ant](http://ant.apache.org/bindownload.cgi) build tool.
2. Install [PostgreSQL](https://www.postgresql.org/download/). Import the enclosed database (.sql file in the `data` folder) into a database called "skistuff". My database has a username of "rupert" and a password of "secret". If you change this, you will have to update the jsp/java files referencing the database.
3. Set up and start your Apache Tomcat server instance.
4. Within Windows Command Prompt, navigate to the root directory and run the command `ant deploy -Dwar.name=jpaormtest`.
5. Open your browser and navigate to `http://localhost:8080/test` to view **write** example.
..* Open your browser and navigate to `http://localhost:8080/test?id=1` to view **read** example.
..* Open your browser and navigate to `http://localhost:8080/test?all` to view **read all** example.


### What's included

Within the downloaded files, this is the relevant structure:

```
jpa-orm-test/
├── build.xml
└── META-INF
    └── persistence.xml (This file has to wind up in WEB-INF/classes/META-INF in the deployable WAR file)
└── src
    └── data/
        └── skiStuff.sql
    └── test/
        ├── Product.java
        ├── PersistenceManage.java
        └── ProductsController.java
    ├── com.springsource.javax.servlet-2.5.0.jar (Not necessary if using Apache Tomcat)
    ├── postgresql-jdbc.jar
    ├── eclipselink.jar
    ├── org.eclipse.persistence.jpa.modelgen.jar
    ├── javax.persistence.jar
    ├── WebListener.java
    ├── web.xml
    └── *.jsp/html/css
```

## Contributors

**Rupert Ong**

* <https://twitter.com/rupertong>
* <https://github.com/rupert-ong>