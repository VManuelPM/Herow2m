# Product Backend

Java 11 project with Spring Boot, this project contains: 
   - CRUD of Heroes
   - H2 Database Memory
   - Endpoints secure with Spring security
   - Endpoints Cacheables 
   - Track Execution Time for endpoints with personal annotation
   - Unit Test
   - Integration Test
   - Swagger Documentation
   - Postman Collection
   - and a lot more.. 

## Installation

Use gradle to install the project.

```bash
gradle build
     or
gradlew build
```

## Usage

```bash
# run the project
gradlew bootRun
```

## Test

```bash
# test the project
gradlew test
```
You can find JacocoReport in the next path: build/jacoco/test/html/index.html
There are Unit and Integration test in the project.
## Swagger Documentation
When you run the app you can access to Swagger documentation in the next path: /swagger-ui.html.
If you want to use Swagger to test endpoints you first need to make login.
- Username: admin
- Password: password

After login. put the word "bearer " and the token returned by the fake login. 

## Postman Collection

You can find the collection with the different configurations ready to call the endpoints in resources folder. You only need to import the collection on Postman.

## Build Docker Image
```bash
# Build the image
docker build -t w2m.jar .
```

## Check Docker Image
```bash
# Check the image
docker image ls
```

## Run Docker Image
```bash
# Check the image
docker run -p 9090:8080 w2m.jar
```
In the run command, I have specified that the port 8080 on the container should be mapped to the port 9090 on the Host OS.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
VManuelPM
