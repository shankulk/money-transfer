
# Money Transfer
Money Transfer Application

## How to run
1. Create a fat `jar` by running `mvn clean package`
2. Then run `java -jar target/money-transfer-1.0-SNAPSHOT.jar server app-config.yml`
1. Application runs on `8080` `port` and base `URL` is `http://localhost:8080/application` 


## How to test
1. Navigate to `src/test/resources`
2. Download postman collection and run using `Postman` app in the given sequence.

## Assumptions / Limitations
1. This app currently supports only two currencies, viz. `INR` & `GBP`.
2. Really rudimentary currency conversion. Need improvements.

## Tech Stack
1. Java 8
1. Dropwizard Framework
    1. Jersey for REST
    2. Jetty as Servlet Container
1. H2 In-Memory Database    
1. Hibernate
1. JUnit 5

