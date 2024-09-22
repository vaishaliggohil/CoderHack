# CoderHack

## Overview

'CoderHack' is a RESTful API service using Spring Boot to manage the Leaderboard for a Coding Platform while using MongoDB to persist the data.

---

### Problem Description
While coding platforms usually host multiple contests while maintaining numerous leaderboards, this is to design a service for managing the leaderboard of a specific contest.

---

## Requirements

- **CRUD Operations**: Services handles Create, Read, Update, and Delete operations on registered users.
  
- **User Fields**:
  - **User ID** (Unique Identifier)
  - **Username**
  - **Score** (0 <= Score <= 100)
  - **Badges** (Code Ninja, Code Champ, Code Master)

- **User Registration**:
  - User needs to enter **User ID** and **Username**.
  - On registration, the score is set to **0** and badges are empty.

- **Score Updates**:
  - Only the **Score** field is updatable via PUT requests.
  - Badges are awarded automatically based on the score:
    - 1 <= Score < 30 → **Code Ninja**
    - 30 <= Score < 60 → **Code Champ**
    - 60 <= Score <= 100 → **Code Master**

- **User Retrieval**:
  - User data is retrievable and sorted by score in descending order.

---

## Endpoints

### 1. Retrieve All Users
- **GET** `/users`
- **Description**: Retrieve a list of all registered users

### 2. Retrieve a Specific User
- **GET** `/users/{userId}`
- **Description**: Retrieve the details of a specific user

### 3. Register a New User
- **POST** `/users`
- **Description**: Register a new user to the contest

### 4. Update User Score
- **PUT** `/users/{userId}`
- **Description**:  Update the score of a specific user

### 5. Delete a User
- **DELETE** `/users/{userId}`
- **Description**: Deregister a specific user from the contest

---

## Installation & Usage

### Prerequisites

- Java 17+
- MongoDB
- Postman (Optional, For API testing)

### Getting Started

1. **Clone the repository:**

    ```bash
    git clone https://github.com/vaishaliggohil/CoderHack.git
    ```
2. **Navigate to the project directory:**

    ```bash
    cd CoderHack
    ```
3. **Build and run the application using Gradle:**

    ```bash
    ./gradlew bootrun
    ```
---

## API Testing

For testing the API endpoints, you can use the following [Postman Collection](https://www.postman.com/gohilvaishali/api-showcase/collection/cnivood/codehackapis)

---

# CoderHack
