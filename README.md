# BeyondOps SQL Datasource Backend Server For Grafana

It's the backend server of [grafana-sql-datasource](https://github.com/beyondops/grafana-sql-datasource)

This backend server will query the data from SQL database and feedback to Grafana.

# Quick Start

```shell
# Run with maven
mvn spring-boot:run

# Package & Run
mvn package -DskipTests
java -jar target/grafana-sql-datasource-server-0.0.1-RELEASE.jar
```
