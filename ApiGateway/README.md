# API Gateway

This project is a **Spring Cloud Gateway** service that acts as the single entry point for multiple microservices. It provides routing, centralized configuration, and OAuth2 authentication using **Okta**.

---

## 🚀 Features
- Centralized API Gateway for microservices
- Dynamic routing to `USER-SERVICE`, `HOTEL-SERVICE`, and `RATING-SERVICE`
- Integrated with **Spring Cloud Config Server**
- Kubernetes ConfigMap support
- Secure authentication and authorization via **Okta OAuth2**

---

## ⚙️ Configuration

### Server
```yaml
server:
  port: 8084
```

---

Maintained by **Manash Barman**  
See [root README](../README.md) for author details.
