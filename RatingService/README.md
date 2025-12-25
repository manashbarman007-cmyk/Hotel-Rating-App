# ⭐ Rating Service

The **Rating Service** is a Spring Boot microservice responsible for managing hotel ratings in a distributed system.  
It uses **MongoDB** for persistence, integrates with **Eureka Service Discovery**, and secures endpoints with **Okta OAuth2**.

---

## 🚀 Features
- RESTful APIs for rating management
- MongoDB integration for storing ratings
- Service discovery with Eureka
- Secure resource access via Okta OAuth2 JWT
- Kubernetes ConfigMap support for cloud-native deployment

---

## ⚙️ Configuration

### Server
- **Port:** `8083`

### Spring Application
- **Name:** `RATING-SERVICE`

### MongoDB
- **Database:** `rating_microservicesDB`  
- **URI:** `${MONGO_DB_URI:mongodb://localhost:27017}`

### Cloud Config
- Config Server: Disabled (`enabled: false`)  
- Kubernetes ConfigMap: Enabled (`name: config-map`)

### Security
- OAuth2 Resource Server with JWT  
- **Issuer URI:** `https://integrator-9230174.okta.com/oauth2/default`

### Eureka Client
- **Instance ID:** `${spring.application.name}:${server.port}`  
- **Prefer IP Address:** `true`  
- **Service URL:** `${EUREKA_SERVER_URL:http://localhost:8761/eureka}`  
- **Register with Eureka:** `true`  
- **Fetch Registry:** `true`

### Okta OAuth2
- **Audience:** `api://default`  
- **Issuer:** `https://integrator-9230174.okta.com/oauth2/default`

---

## 🔐 Security Notes
- This service accepts only tokens with the `internal` scope.
- External clients must call the User Service, which then securely communicates with Rating Service.

---

## 🛠️ Prerequisites
- Java 17+
- Maven/Gradle
- MongoDB (local or cloud instance)
- Eureka Server running on `http://localhost:8761/eureka`
- Okta Developer Account (for OAuth2 setup)

---

Maintained by **Manash Barman**  
See [root README](../README.md) for author details.
