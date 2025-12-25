# 👤 User Service

The **User Service** is a Spring Boot microservice responsible for managing user data and interactions in a distributed system.  
It uses **MySQL** for persistence, integrates with **Eureka Service Discovery**, and secures endpoints with **Okta OAuth2**.  
The service also leverages **Resilience4j** for fault tolerance and **Spring Boot Actuator** for monitoring.

---

## 🚀 Features
- RESTful APIs for user management
- MySQL integration with JPA/Hibernate
- Service discovery with Eureka
- Secure resource access via Okta OAuth2 (JWT + Client Credentials)
- Resilience patterns: Circuit Breaker, Retry, Rate Limiter
- Actuator endpoints for health and monitoring
- Kubernetes ConfigMap support for cloud-native deployment

---

## ⚙️ Configuration

### Server
- **Port:** `8081`

### Spring Application
- **Name:** `USER-SERVICE`

### MySQL Database
- **URL:** `jdbc:mysql://${MYSQL_DB_HOST:localhost}:3306/microservices_db`  
- **Username:** `root`  
- **Password:** `${MYSQL_DB_PASSWORD:M@n@sh12345}`  
- **Driver:** `com.mysql.cj.jdbc.Driver`  
- **Dialect:** `org.hibernate.dialect.MySQL8Dialect`  
- **DDL Auto:** `update`  

### Cloud Config
- Config Server: Optional import from `${CONFIG_SERVER_URL:http://localhost:8085}`  
- Kubernetes ConfigMap: Enabled (`name: config-map`)

### Security
- OAuth2 Resource Server with JWT  
- Client Credentials flow for internal communication  
- **Issuer URI:** `https://integrator-9230174.okta.com/oauth2/default`  
- **Client ID:** `0oavmlvp1jG6VBHKu697`  
- **Client Secret:** `5CYWiNQQAui4QiuF7AN-_BWDG0DDSWwhUAteQkb9onWFxB1BCUpcJj50ItH6NnVu`  
- **Scopes:** `openid, profile, internal, email, offline_access`

> 🔐 **Internal Scope Note:**  
> The `internal` scope ensures that only the User Service can call Hotel and Rating services.  
> External clients must call the User Service, which then securely communicates with other services.


### Actuator
- Endpoints exposed: `health`, `retries`  
- Health details: `ALWAYS`  
- Circuit breaker health checks enabled

### Resilience4j
- **Circuit Breakers:**  
  - `ratingHotelBreaker1` and `ratingHotelBreaker2`  
  - Sliding window size: `10` (COUNT_BASED)  
  - Failure rate threshold: `50%`  
  - Automatic transition from open → half-open  
  - Wait duration in open state: `10s`  
  - Permitted calls in half-open: `3`  
  - Minimum calls before evaluation: `5`

- **Retry:**  
  - Max attempts: `3`  
  - Wait duration: `1000ms`  
  - Exponential backoff enabled (multiplier: `2`)  
  - Ignored exceptions: `UserNotFoundException`

- **Rate Limiter:**  
  - `myRateLimiter1` and `myRateLimiter2`  
  - Limit: `5 calls per 10s`  
  - Timeout duration: `0`

---

## 🛠️ Prerequisites
- Java 17+
- Maven/Gradle
- MySQL (local or cloud instance)
- Eureka Server running on `http://localhost:8761/eureka`
- Okta Developer Account (for OAuth2 setup)

---

Maintained by **Manash Barman**  
See [root README](../README.md) for author details.
