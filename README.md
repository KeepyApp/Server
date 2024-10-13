# 🚀 Keepy - Server 🚀

### 📝 Overview
The **Keepy - Server** repository is a core part of the **Keepy** system, responsible for managing communication between the client app and handling event storage via Firebase. The server receives data from external systems that process audio data, manages notifications for the client, and stores event details, including metadata and audio files, in Firebase.

---

## 📁 Structure

- **src/main/java**: Contains all the server-side logic in Java.
- **Firebase integration**: Ensures that detected sound events are stored and retrieved properly for client access.

---

## 🔧 Installation

### Requirements:
- **Java 11+**.
- **Gradle** for build management.
- **Firebase Project Setup**.

### Steps:
1. Clone the repository:
   ```bash
   git clone https://github.com/itzhakGal/Keepy-Server.git

---

## 📚 Usage

1. The server receives data from external systems or services (such as AI models) that process audio data from kindergartens.
2. Manages event notifications and sends them to the **Keepy Client** when anomalies are detected by the external system.
3. Stores event details, including metadata and audio files, in Firebase for future reference and analysis.

---

## 💡 Features

- **Event Management**: Receives event data from external services and manages notifications for the client.
- **Firebase Integration**: Stores event details and audio data, and notifies users through Firebase Cloud Messaging.
- **Scalable Architecture**: Can be deployed to handle multiple kindergartens simultaneously.
- **Data Logging**: Logs all detected events for future analysis and reference.

   
