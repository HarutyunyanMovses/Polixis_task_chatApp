Chat API - User & Message Service
Overview
This REST API, built using Quarkus, provides functionalities for user management and messaging. 
It supports user authentication, messaging, event streaming, and role-based access control (RBAC) using JWT tokens.

Technologies Used
Java 21+
Quarkus
PostgreSQL (for message storage)
Kafka (for event streaming)
Jakarta RESTful Web Services (JAX-RS)
JWT Authentication

📌 User Service
Base URL: /api/v1/users
Endpoints

1️⃣ User Registration (Public)
POST /api/v1/users/register
Description: Creates a new user.
Request Body:
{
  "name": "John",
  "surname": "Doe",
  "email": "john@example.com",
  "password": "securepassword"
}

Response:
{
  "id": "62062702-e651-4aef-8788-584b1e13f338",
  "name": "John",
  "surname": "Doe",
  "email": "john@example.com",
  "role": "USER"
}

2️⃣ Get All Users (Admin Only)
GET /api/v1/users
Description: Fetches all users (Admin access required).
Response: List of users.

3️⃣ Get User by ID
GET /api/v1/users/{id}
Description: Fetches a user by ID (User/Admin access required).

4️⃣ Update User
PUT /api/v1/users/{id}
Description: Updates user details (User/Admin access required).
Request Body: Same as the registration body.

5️⃣ Delete User (Admin Only)
DELETE /api/v1/users/{id}
Description: Deletes a user (Admin access required).

6️⃣ Verify User Email (Public)
POST /api/v1/users/verify?email={email}&code={code}
Description: Verifies a user's email.

📌 Message Service
Base URL: /api/v1/messages
Endpoints

1️⃣ Send Message
POST /api/v1/messages
Description: Sends a message to another user.
Request Body:
{
  "sender": "uuid",
  "recipient": "uuid",
  "content": "Hello!"
}

Response:
{
  "id": "uuid",
  "sender": "uuid",
  "recipient": "uuid",
  "content": "Hello!",
  "timestamp": "2025-02-28T12:34:56Z"
}

2️⃣ Get User Messages
GET /api/v1/messages/{userId}
Description: Fetches all messages for a user.

📌 Authentication & Authorization

Uses JWT-based authentication.
Roles: USER, ADMIN.
Secure endpoints require a valid JWT token in the Authorization header:
Authorization: Bearer <JWT_TOKEN>

📌 How to Run

Clone the repository:
GitHub - Polixis Chat API

Run the Quarkus application:
mvn quarkus:dev

📌 Notes
Thank you so much for the time you dedicated to me.
It was very interesting for me to work on this application.
I learned about the Quarkus framework for the first time.
Please don't judge me too harshly.
With respect and love,
Movses Harutyunyan.

