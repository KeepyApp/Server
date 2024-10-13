# 🚀 Keepy - Server 🚀

### 📝 Overview
The **Keepy - Server** repository is a core part of the **Keepy** system, responsible for processing audio data, managing communication between the client app, and handling event storage via Firebase. The server listens for unusual noises in kindergartens and triggers alerts when necessary.

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

1. The server processes incoming audio data from kindergartens.
2. Detects unusual sound patterns (crying, shouting, etc.) using AI models.
3. Sends notifications to the **Keepy Client** when an anomaly is detected.
4. Stores events and audio data in Firebase for future reference.

---

## 💡 Features

- **Real-time Audio Processing**: Processes sound data from the kindergarten and identifies unusual patterns.
- **Firebase Integration**: Stores sound events and notifies users through Firebase Cloud Messaging.
- **Scalable Architecture**: Can be deployed to handle multiple kindergartens simultaneously.
- **Event Logging**: All detected events are logged for analysis and future reference.
   
