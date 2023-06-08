# SpringBootProjects

**stud:**
This is a simple Bank implemented through springboot.
Dependencies are mainly `spring-boot-starter-web` , `spring-boot-starter-thymeleaf` , `lombok` and `spring-boot-starter-data-jpa`

**cloudTest:**
This is a simple implementation of Spring Cloud Gateway API for microservices.
Here gateway service handles the requests and routes to login service or order service.
In gateway service, Gateway `spring-cloud-starter-gateway` is added and in other two services, `spring-boot-starter-web` is used
