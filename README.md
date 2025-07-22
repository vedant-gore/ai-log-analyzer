# 🎯 AI-Driven Log Analyzer Suite

Welcome to the **AI-Driven Log Analyzer Suite**—a microservices-based toolkit that simulates, stores, analyzes, and intelligently summarizes application logs using cutting-edge LLMs (Llama3.2 via Ollama). Whether you’re a DevOps engineer hunting down elusive errors or an SRE aiming to predict system failures, this suite has your back.

---

## 🚀 Project Highlights

* **Dynamic Log Generation**: The `log-producer-service` continuously emits structured JSON logs across levels (INFO, WARN, ERROR, FATAL) to a PostgreSQL database.
* **Smart Log Analysis**: The `log-analyzer-service` fetches historic logs, pre-aggregates key metrics, then feeds them to a local Llama3.2 model (via Ollama) to generate concise, technical summaries.
* **Service Discovery & Routing**: An Eureka-based `service-registry` enables all services to find each other, with a Spring Cloud Gateway (`api-gateway`) acting as a unified entry point.
* **AI-Powered Insights**: Leverage Spring AI’s `ChatClient` to transform raw logs into actionable insights—complete with failure patterns, root causes, and remediation steps.

---

## 🏗 Architecture Diagram

```
+-------------------+      +----------------------+      +-------------------+
|                   | Feign|                      |Spring |                   |
| log-producer-svc  +----->+ log-analyzer-svc     +----->+ local-llama3.2     |
| (Port 8081)       |      | (Port 8082)          | AI    | via Ollama (11434)|
+--------+----------+      +----------+-----------+      +-------------------+
         ^                           |
         |                           |
         | JDBC                      | Eureka + Feign
         v                           v
+-------------------+      +-------------------+
|                   |      |                   |
| PostgreSQL (5432) |      | API Gateway       |
|                   |      | (Port 8765)       |
+-------------------+      +-------------------+
         ^
         | Eureka
         v
+-------------------+
|                   |
| Service Registry  |
| (Eureka, 8761)    |
+-------------------+
```

---

## 🛠️ Microservice Breakdown

### 1. log-producer-service

* **Tech**: Java 21, Spring Boot, Spring Data JPA, PostgreSQL, Logback JSON Encoder
* **Role**: Simulates application events with a 64/10/20/6 ratio (INFO/WARN/ERROR/FATAL).
* **Endpoints**:

  * `GET /logs` (all)
  * `GET /logs/recent` (latest 100)
  * `GET /logs?level={level}`
  * `GET /logs?service={name}`

### 2. log-analyzer-service

* **Tech**: Java 21, Spring Boot, Spring Cloud OpenFeign, Spring AI, Ollama-Mistral
* **Role**: Aggregates and analyzes logs via LLM; exposes human-friendly summaries.
* **Endpoints**:

  * `GET /analyze/logs`
  * `GET /analyze/logs/recent`
  * `GET /analyze/logs/{id}`
  * `GET /analyze/logs?level={level}`
  * `GET /analyze/summarize` (AI-powered)

### 3. service-registry

* **Tech**: Spring Cloud Netflix Eureka
* **Role**: Central discovery for all microservices.

### 4. api-gateway

* **Tech**: Spring Cloud Gateway
* **Role**: Single HTTP entry point; routes `/logs/**` and `/analyze/**` to respective services.

---

## ⚙️ Getting Started Locally

1. **Clone** this repo:

   ```bash
   git clone git@github.com:YourUser/ai-log-analyzer.git
   cd ai-log-analyzer
   ```

2. **Start PostgreSQL** (or via Docker Compose):

   ```bash
   docker-compose up -d postgres
   ```

3. **Launch Eureka**:

   ```bash
   cd service-registry
   ./mvnw spring-boot:run
   ```

4. **Run log-producer-service**:

   ```bash
   cd ../log-producer-service
   ./mvnw spring-boot:run
   ```

5. **Run Ollama Server**:

   ```bash
   ollama serve
   ```

   (Ensure you’ve pulled `mistral` via `ollama pull mistral`)

6. **Run log-analyzer-service**:

   ```bash
   cd ../log-analyzer-service
   ./mvnw spring-boot:run
   ```

7. **Run API Gateway**:

   ```bash
   cd ../api-gateway
   ./mvnw spring-boot:run
   ```

8. **Test via cURL or Postman**:

   * All logs:  `GET http://localhost:8765/logs`
   * AI summary: `GET http://localhost:8765/analyze/summarize`

---

## 📜 License

This project is licensed under the **MIT License**. See [LICENSE](LICENSE) for details.

---

Made with ☕ and 🤖 by **Vedant**. Feel free to ⭐ the repo!
