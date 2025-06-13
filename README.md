# ⚡ Energy Monitoring System

The **Energy Monitoring System** API is a RESTful application built with **Java 21**, **Spring Boot 3.5**, **PostgreSQL**, **Kafka**, and **Docker**. It is designed to **monitor real-time energy consumption** from devices such as smart meters or sensors. This project supports **green tech** initiatives by enabling energy usage analysis, promoting efficiency, and reducing waste.

## 🚀 Features

- ✅ Record energy consumption data from devices
- 🔍 Retrieve single or multiple records
- 📦 Store data securely in PostgreSQL
- ⚙️ Asynchronous processing with Kafka (up to 60% faster response times)
- 🐳 Containerized with Docker for easy deployment

## 📡 API Endpoints

Base URL: `/api/energy`

### ➕ POST `/api/energy`

Registers a new energy consumption record.

**Request body:**

```json
{
  "deviceId": "device123",
  "consumptionKwh": 5.2,
  "recordedAt": "2025-06-13T15:00:00"
}

#Response:
-> Json

{
  "id": 1,
  "deviceId": "device123",
  "consumptionKwh": 5.2,
  "recordedAt": "2025-06-13T15:00:00"
}



🔍 GET /api/energy/{id}

Fetches a specific energy consumption record by its ID.

Response:
-> Json

{
  "id": 1,
  "deviceId": "device123",
  "consumptionKwh": 5.2,
  "recordedAt": "2025-06-13T15:00:00"
}




📄 GET /api/energy

Lists all stored energy consumption records.

Response:
-> Json

[
  {
    "id": 1,
    "deviceId": "device123",
    "consumptionKwh": 5.2,
    "recordedAt": "2025-06-13T15:00:00"
  },
  {
    "id": 2,
    "deviceId": "device456",
    "consumptionKwh": 3.8,
    "recordedAt": "2025-06-13T15:01:00"
  }
]




## 🔄 Data Flow

   1 - Devices send energy data via POST /api/energy.

   2 - The backend:

        -> Saves the record to PostgreSQL.

        -> Publishes the data to the Kafka topic energy-data.

    3 - External systems (e.g., analytics, alerts) can consume the Kafka topic asynchronously.





## 🧱 Data Schema
Table: energy_consumption

Field	                 Type	                Description
id	                   Long	                Unique identifier (primary key)
device_id	             String	              Device identifier (e.g. "device123")
consumption_kwh	       Double	              Energy consumption in kWh
recorded_at	           Timestamp	          Timestamp of the recorded data


🛠 Tech Stack

    Java 21

    Spring Boot 3.5

    PostgreSQL

    Apache Kafka

    Docker & Docker Compose

🌱 Green Tech Purpose

This API is a green tech tool that helps individuals and organizations:

    Track real-time energy consumption

    Identify consumption patterns and spikes

    Generate reports for cost and environmental impact reduction

    Integrate with renewable energy systems

🐳 Running the Project

Requirements:

    Docker and Docker Compose installed


# Clone the repository
git clone https://github.com/gabrielnfonseca-bsb-de/Energy-Monitoring-System.git

# Navigate into the project folder
cd Energy-Monitoring-System

# Start the services (API + PostgreSQL + Kafka)
docker-compose up --build

The API will be available at http://localhost:8085


✨ Contributions

Contributions are welcome! Feel free to open issues or submit pull requests.
📬 Contact

Gabriel do Nascimento Fonseca
email gabriel.fonseca.on@gmail.com
