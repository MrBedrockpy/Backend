# 📋 Task tracker API Documentation

## 🗂️ POST /tasktracker/board

Creates a new board.

### Parameters

| name  | data type | description |
| ----- | --------- | ----------- |
| title | String    | Board title |

---

### Possible responses

| HTTP code | success | error message |
| --------- | ------- | ------------- |
| 200       | true    | —             |
| 500       | false   | error message |

---

### Successful response structure

| name    | data type | description |
| ------- | --------- | ----------- |
| id      | long      | Board ID    |
| title   | String    | Board title |
| columns | List      | Columns     |

---

### Request example

```json
{
  "title": "My Board"
}
```

### Success response

```json
{
  "success": true,
  "data": {
    "id": 1,
    "title": "My Board",
    "columns": []
  },
  "error": null
}
```

---

## 📥 GET /tasktracker/board?id={id}

Retrieves a board by ID.

### Parameters

| name | data type | description |
| ---- | --------- | ----------- |
| id   | long      | Board ID    |

---

### Possible responses

| HTTP code | success | error message   |
| --------- | ------- | --------------- |
| 200       | true    | —               |
| 404       | false   | Board not found |

---

### Successful response structure

| name    | data type | description |
| ------- | --------- | ----------- |
| id      | long      | Board ID    |
| title   | String    | Title       |
| columns | List      | Columns     |

---

### Success response

```json
{
  "success": true,
  "data": {
    "id": 1,
    "title": "My Board",
    "columns": []
  },
  "error": null
}
```

---

## ✏️ POST /tasktracker/board/rename

Renames a board.

### Parameters

| name     | data type | description |
| -------- | --------- | ----------- |
| boardId  | long      | Board ID    |
| newTitle | String    | New title   |

---

### Possible responses

| HTTP code | success | error message   |
| --------- | ------- | --------------- |
| 200       | true    | —               |
| 404       | false   | Board not found |

---

### Request example

```json
{
  "boardId": 1,
  "newTitle": "New Name"
}
```

### Success response

```json
{
  "success": true,
  "data": null,
  "error": null
}
```

---

## 🗑️ DELETE /tasktracker/board?id={id}

Deletes a board by ID.

### Parameters

| name | data type | description |
| ---- | --------- | ----------- |
| id   | long      | Board ID    |

---

### Possible responses

| HTTP code | success | error message   |
| --------- | ------- | --------------- |
| 200       | true    | —               |
| 404       | false   | Board not found |

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

## 📦 POST /tasktracker/column

Creates a column in a board.

### Parameters

| name    | data type | description  |
| ------- | --------- | ------------ |
| boardId | long      | Board ID     |
| title   | String    | Column title |

---

### Possible responses

| HTTP code | success | error message   |
| --------- | ------- | --------------- |
| 200       | true    | —               |
| 404       | false   | Board not found |

---

### Successful response structure

| name     | data type | description |
| -------- | --------- | ----------- |
| id       | long      | Column ID   |
| name     | String    | Title       |
| position | int       | Position    |
| tasks    | List      | Tasks       |

---

### Request example

```json
{
  "boardId": 1,
  "title": "To Do"
}
```

### Success response

```json
{
  "success": true,
  "data": {
    "id": 1,
    "name": "To Do",
    "position": 0,
    "tasks": []
  },
  "error": null
}
```

---

## 📥 GET /tasktracker/column?id={id}

Retrieves column by ID.

### Parameters

| name | data type | description |
| ---- | --------- | ----------- |
| id   | long      | Column ID   |

---

### Possible responses

| HTTP code | success | error message    |
| --------- | ------- | ---------------- |
| 200       | true    | —                |
| 404       | false   | Column not found |

---

### Success response

```json
{
  "success": true,
  "data": {
    "id": 1,
    "name": "To Do",
    "position": 0,
    "tasks": []
  },
  "error": null
}
```

---

## ✏️ POST /tasktracker/column/rename

Renames a column.

### Parameters

| name     | data type | description |
| -------- | --------- | ----------- |
| columnId | long      | Column ID   |
| newTitle | String    | New title   |

---

### Possible responses

| HTTP code | success | error message    |
| --------- | ------- | ---------------- |
| 200       | true    | —                |
| 404       | false   | Column not found |

---

### Request example

```json
{
  "columnId": 1,
  "newTitle": "In Progress"
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

## 🔀 POST /tasktracker/column/move

Moves column to a new position.

### Parameters

| name        | data type | description  |
| ----------- | --------- | ------------ |
| columnId    | long      | Column ID    |
| newPosition | int       | New position |

---

### Possible responses

| HTTP code | success | error message    |
| --------- | ------- | ---------------- |
| 200       | true    | —                |
| 400       | false   | Invalid position |
| 404       | false   | Column not found |

---

### Request example

```json
{
  "columnId": 1,
  "newPosition": 2
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

## 🗑️ DELETE /tasktracker/column?id={id}

Deletes column by ID.

### Parameters

| name | data type | description |
| ---- | --------- | ----------- |
| id   | long      | Column ID   |

---

### Possible responses

| HTTP code | success | error message    |
| --------- | ------- | ---------------- |
| 200       | true    | —                |
| 404       | false   | Column not found |

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

## 🧩 POST /tasktracker/task

Creates a task.

### Parameters

| name        | data type | description |
| ----------- | --------- | ----------- |
| columnId    | long      | Column ID   |
| title       | String    | Title       |
| description | String    | Description |
| position    | int       | Position    |

---

### Possible responses

| HTTP code | success | error message    |
| --------- | ------- | ---------------- |
| 200       | true    | —                |
| 404       | false   | Column not found |

---

### Successful response structure

| name        | data type | description |
| ----------- | --------- | ----------- |
| id          | long      | Task ID     |
| title       | String    | Title       |
| description | String    | Description |
| position    | int       | Position    |

---

### Request example

```json
{
  "columnId": 1,
  "title": "Fix bug",
  "description": "Login issue",
  "position": 0
}
```

---

### Success response

```json
{
  "success": true,
  "data": {
    "id": 1,
    "title": "Fix bug",
    "description": "Login issue",
    "position": 0
  },
  "error": null
}
```

---

## 📥 GET /tasktracker/task?id={id}

Retrieves task by ID.

### Parameters

| name | data type | description |
| ---- | --------- | ----------- |
| id   | long      | Task ID     |

---

### Possible responses

| HTTP code | success | error message  |
| --------- | ------- | -------------- |
| 200       | true    | —              |
| 404       | false   | Task not found |

---

### Success response

```json
{
  "success": true,
  "data": {
    "id": 1,
    "title": "Fix bug",
    "description": "Login issue",
    "position": 0
  },
  "error": null
}
```

---

## 🔀 POST /tasktracker/task/move

Moves task between columns.

### Parameters

| name        | data type | description   |
| ----------- | --------- | ------------- |
| taskId      | long      | Task ID       |
| newColumnId | long      | Target column |
| newPosition | int       | Position      |

---

### Possible responses

| HTTP code | success | error message                            |
| --------- | ------- | ---------------------------------------- |
| 200       | true    | —                                        |
| 400       | false   | Invalid position                         |
| 404       | false   | Task not found / Target column not found |

---

### Request example

```json
{
  "taskId": 1,
  "newColumnId": 2,
  "newPosition": 0
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
