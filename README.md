# 🌐 Microservices Ecosystem (Backend Only)

This project is a **cloud-native microservices architecture** built with **Spring Boot**, **Spring Cloud**, and **Resilience4j**.  
It consists of five core components:

- **User Service** (MySQL, orchestrator, entry point for external clients)
- **Hotel Service** (MongoDB, manages hotel data)
- **Rating Service** (MongoDB, manages hotel ratings)
- **Eureka Server** (Service registry and discovery)
- **API Gateway** (Centralized routing and entry point)
- **Config Server** (Externalized configuration management)

Authentication and authorization are handled via **Okta OAuth2**, with an **internal scope** to enforce secure service-to-service communication.

⚠️ **Note:** This ecosystem currently has **no frontend**. All interactions are via REST APIs, routed through the API Gateway.

---

## 🧩 Services Overview

| Service           | Port  | Database | Purpose                                                                 | Security Scope |
|-------------------|-------|----------|-------------------------------------------------------------------------|----------------|
| **User Service**  | 8081  | MySQL    | Manages user data and orchestrates calls to Hotel & Rating services     | `internal` + external scopes |
| **Hotel Service** | 8082  | MongoDB  | Manages hotel information                                               | `internal` only |
| **Rating Service**| 8083  | MongoDB  | Manages hotel ratings                                                   | `internal` only |
| **Eureka Server** | 8761  | N/A      | Service registry and discovery                                          | N/A            |
| **API Gateway**   | 8084  | N/A      | Routes external requests to User Service, enforces security policies    | External + internal |
| **Config Server** | 8085  | N/A      | Centralized configuration for all services                              | N/A            |

---

## 🔐 Security Model

- **Okta OAuth2** is used for authentication and authorization.
- External clients interact **only through the API Gateway → User Service**.
- The **User Service** uses a **custom `internal` scope** to securely call Hotel and Rating services.
- Hotel and Rating services reject requests without the `internal` scope, ensuring they are **not directly accessible** from outside.

---

## ⚙️ Tech Stack

- **Spring Boot** (REST APIs, JPA, MongoDB integration)
- **Spring Cloud** (Eureka, Config Server, API Gateway, Kubernetes ConfigMap support)
- **Resilience4j** (Circuit Breaker, Retry, Rate Limiter)
- **Spring Security + Okta OAuth2** (JWT, Client Credentials flow)
- **Databases:**  
  - MySQL (User Service)  
  - MongoDB (Hotel & Rating Services)

---

## 🛠️ Prerequisites

- Java 17+
- Maven/Gradle
- MySQL & MongoDB instances running
- Eureka Server running on `http://localhost:8761/eureka`
- Config Server running on `http://localhost:8085`
- Okta Developer Account (for OAuth2 setup)

---

# 🚀 Deployment on Kubernetes

This project includes **Kubernetes manifests** for all services (User, Hotel, Rating, Eureka, Config Server, API Gateway).  
Anyone can deploy the ecosystem by applying the manifests directly to their cluster.

## ▶️ Steps

1. Ensure you have a running Kubernetes cluster (e.g., Minikube, Kind, or a cloud provider).
2. Apply the manifests:
   ```bash
   kubectl apply -f ./k8s/

---

## 🔗 Accessing the API Gateway

After applying the Kubernetes manifests, check the NodePort for the API Gateway:

```bash
kubectl get svc api-gateway
```
---

## 👨‍💻 Author
**Manash Barman**  
Backend Developer | Java, Spring Boot, Microservices  
[LinkedIn](https://www.linkedin.com/in/manash-barman-15b1833a1/) | [GitHub](https://github.com/manashbarman007-cmyk)

---
