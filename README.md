# API Documentation

## 🔐 POST /auth/login

Authenticates a user and returns a JWT token for further requests.

### Parameters

| name     | data type | description     |
| -------- | --------- | --------------- |
| username | String    | User's username |
| password | String    | User's password |

---

### Possible responses

| HTTP code | success | error message                |
| --------- | ------- | ---------------------------- |
| 200       | true    | —                            |
| 401       | false   | Invalid username or password |
| 500       | false   | error message                |

---

### Successful response structure

| name  | data type | description |
| ----- | --------- | ----------- |
| token | String    | JWT token   |

---

### Request example

```json
{
  "username": "john",
  "password": "123456"
}
```

### Success response

```json
{
  "success": true,
  "data": {
    "token": "jwt_token_here"
  },
  "error": null
}
```

### Error response

```json
{
  "success": false,
  "data": null,
  "error": {
    "code": 401,
    "message": "Invalid username or password"
  }
}
```

---

## 📝 POST /auth/register

Registers a new user, creates region settings, and returns a JWT token.

### Parameters

| name       | data type | description              |
| ---------- | --------- | ------------------------ |
| username   | String    | Username                 |
| email      | String    | Email address            |
| password   | String    | Password                 |
| country    | String    | Country                  |
| timeZoneId | byte      | Timezone offset from GMT |
| currency   | String    | Currency                 |

---

### Possible responses

| HTTP code | success | error message                             |
| --------- | ------- | ----------------------------------------- |
| 200       | true    | —                                         |
| 400       | false   | User with this name already exists        |
| 500       | false   | Registered user not found / error message |

---

### Successful response structure

| name  | data type | description |
| ----- | --------- | ----------- |
| token | String    | JWT token   |

---

### Request example

```json
{
  "username": "john",
  "email": "john@mail.com",
  "password": "123456",
  "country": "Germany",
  "timeZoneId": 1,
  "currency": "EUR"
}
```

---

## 🌍 GET /region-settings?id={id}

Retrieves region settings for a specific user by ID.

### Parameters

| name | data type | description |
| ---- | --------- | ----------- |
| id   | long      | User ID     |

---

### Possible responses

| HTTP code | success | error message                  |
| --------- | ------- | ------------------------------ |
| 200       | true    | —                              |
| 404       | false   | User region settings not found |

---

### Successful response structure

| name       | data type | description |
| ---------- | --------- | ----------- |
| id         | long      | User ID     |
| country    | String    | Country     |
| timeZoneId | byte      | GMT offset  |
| currency   | String    | Currency    |

---

### Success response

```json
{
  "success": true,
  "data": {
    "id": 1,
    "country": "Germany",
    "timeZoneId": 1,
    "currency": "EUR"
  },
  "error": null
}
```

---

## 🌍 POST /region-settings

Creates or updates region settings for a user.

### Parameters

| name       | data type | description                             |
| ---------- | --------- | --------------------------------------- |
| id         | long      | ID of the user these settings belong to |
| country    | String    | Country name                            |
| timeZoneId | byte      | GMT offset                              |
| currency   | String    | Currency name                           |

---

### Possible responses

| HTTP code | success | error message |
| --------- | ------- | ------------- |
| 200       | true    | —             |
| 500       | false   | error message |

---

### Request example

```json
{
  "id": 1,
  "country": "Germany",
  "timeZoneId": 1,
  "currency": "EUR"
}
```

---

### Success response

```json
{
  "success": true,
  "data": null,
  "error": null
}
```

---

## 🔗 POST /social-link/telegram/link

Creates a session for linking a Telegram account to a user.

### Parameters

| name   | data type | description |
| ------ | --------- | ----------- |
| userId | long      | User ID     |

---

### Possible responses

| HTTP code | success | error message                                    |
| --------- | ------- | ------------------------------------------------ |
| 200       | true    | —                                                |
| 400       | false   | Telegram already linked / session already exists |
| 404       | false   | The user with this id not exists!                |

---

### Successful response structure

| name      | data type | description     |
| --------- | --------- | --------------- |
| sessionId | String    | Link session ID |

---

### Request example

```json
{
  "userId": 1
}
```

---

### Success response

```json
{
  "success": true,
  "data": {
    "sessionId": "abc123"
  },
  "error": null
}
```

---

## 🔗 POST /social-link/telegram/confirm

Confirms a Telegram linking session and binds Telegram ID to the user.

### Parameters

| name       | data type | description      |
| ---------- | --------- | ---------------- |
| sessionId  | String    | Session ID       |
| telegramId | long      | Telegram user ID |

---

### Possible responses

| HTTP code | success | error message           |
| --------- | ------- | ----------------------- |
| 200       | true    | —                       |
| 400       | false   | Telegram already in use |
| 404       | false   | Session not found       |
| 500       | false   | error message           |

---

### Request example

```json
{
  "sessionId": "abc123",
  "telegramId": 99999999
}
```

---

## 👤 GET /user?id={id}

Retrieves a user by their ID.

### Parameters

| name | data type | description |
| ---- | --------- | ----------- |
| id   | long      | User ID     |

---

### Possible responses

| HTTP code | success | error message  |
| --------- | ------- | -------------- |
| 200       | true    | —              |
| 404       | false   | User not found |

---

### Successful response structure

| name         | data type | description   |
|--------------| --------- |---------------|
| id           | long      | User ID       |
| username     | String    | Username      |
| role         | Role      | User role     |
| email        | String    | Email address |
| referralCode | String    | Referral code |

---

### Possible Role values

* DEVELOPER
* USER

---

### Response example

```json
{
  "success": true,
  "data": {
    "id": 1,
    "username": "john",
    "role": "USER"
  },
  "error": null
}
```

---

## 👤 GET /user?name={username}

Retrieves a user by their username.

### Parameters

| name | data type | description |
| ---- | --------- | ----------- |
| name | String    | Username    |

---

### Possible responses

| HTTP code | success | error message  |
| --------- | ------- | -------------- |
| 200       | true    | —              |
| 404       | false   | User not found |

---

### Successful response structure

| name         | data type | description   |
|--------------| --------- |---------------|
| id           | long      | User ID       |
| username     | String    | Username      |
| role         | Role      | User role     |
| email        | String    | Email address |
| referralCode | String    | Referral code |

---

### Possible Role values

* DEVELOPER
* USER

---

### Response example

```json
{
  "success": true,
  "data": {
    "id": 1,
    "username": "john",
    "role": "USER"
  },
  "error": null
}
```

---

## 🔧 POST /user/change-role

Changes a user's role. Requires **DEVELOPER** role.

### Parameters

| name     | data type | description |
| -------- | --------- | ----------- |
| userId   | long      | User ID     |
| roleName | String    | Role name   |

---

### Possible roleName values

* DEVELOPER
* USER

---

### Possible responses

| HTTP code | success | error message                   |
| --------- | ------- | ------------------------------- |
| 200       | true    | —                               |
| 400       | false   | Role already set                |
| 404       | false   | User not found / Role not found |

---

### Request example

```json
{
  "userId": 1,
  "roleName": "DEVELOPER"
}
```

---

### Error example

```json
{
  "success": false,
  "data": null,
  "error": {
    "code": 400,
    "message": "Role already set"
  }
}
```