# Krastev's Gym

## Description

This project is developed using Spring Boot and Thymeleaf for building a gym application. The application is secured with Spring Security and uses MySQL for data storage. The application is deployed using Docker.

## Technologies Used

* [Spring Framework](https://docs.spring.io/spring-framework/reference/index.html)
* [Spring Boot](https://docs.spring.io/spring-boot/documentation.html)
* [Spring Data](https://docs.spring.io/spring-data/jpa/docs/current-SNAPSHOT/reference/html/#reference)
* [Spring Security](https://docs.spring.io/spring-security/reference/index.html)
* [MySQL](https://dev.mysql.com/doc/refman/8.4/en/)
* [Thymeleaf](https://www.thymeleaf.org/documentation.html)
* [HTML & CSS](https://github.com/todorkrastev/krastevs-gym-html-css)
* [Cloudinary](https://cloudinary.com/documentation)
* [Open Exchange Rates](https://docs.openexchangerates.org/reference/api-introduction)
* [Docker](https://docs.docker.com/)

## Getting Started

### Prerequisites

* [Docker Desktop](https://www.docker.com/products/docker-desktop) for Mac or Windows (Docker Compose will be
  automatically installed)

### Installation

1. Pull the docker images from Docker Hub:

 ```shell
docker pull todorkrastev/krastevs-gym:v1.0.7
docker pull todorkrastev/krastevs-gym-rest:v1.0.5
 ```

2. Navigate to `docker` directory to build and run the application:

```shell
cd docker
docker-compose -f docker-compose.yml up
```

3. The `krastevs-gym` app will be running at [http://localhost:8080](http://localhost:8080), and the `krastevs-gym-rest`
   will be available
   at [http://localhost:8081](http://localhost:8081).

## License

This project is licensed under the terms of the Creative Commons Legal Code CC0 1.0 Universal. See the `LICENSE` file for details.