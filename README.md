## About This Project

This is a simple project CRUD Bookshelf rest api using Spring Boot. 

## API Spec

### Create Book

Request :

- Method : POST
- URL : `/api/books`
- Request Body :

```json
{
  "name": "string",
  "author": "string",
  "summary": "string",
  "publisher": "string",
  "total_page": 0,
  "read_page": 0
}
```

Response :

```json
{
  "status": "success",
  "errors": null,
  "data": {
    "id": "a42cb555-583a-4975-86dd-e9d88e0f17f7",
    "name": "Testing Book",
    "author": "John Doe",
    "summary": "this is summary",
    "publisher": "Informatika",
    "finished": false,
    "total_page": 100,
    "read_page": 10,
    "inserted_at": "2023-08-23T21:03:25.000+07:00",
    "updated_at": "2023-08-23T21:03:25.000+07:00"
  },
  "pagination": null
}
```

### Update Book

Request :

- Method : PUT
- URL : `/api/books/{id}`
- Request Body :

```json
{
  "name": "string",
  "author": "string",
  "summary": "string",
  "publisher": "string",
  "total_page": 0,
  "read_page": 0
}
```
Response :

```json
{
  "status": "success",
  "errors": null,
  "data": {
    "id": "a42cb555-583a-4975-86dd-e9d88e0f17f7",
    "name": "Testing Book",
    "author": "John Doe",
    "summary": "this is summary",
    "publisher": "Informatika",
    "finished": false,
    "total_page": 100,
    "read_page": 10,
    "inserted_at": "2023-08-23T21:03:25.000+07:00",
    "updated_at": "2023-08-23T21:06:26.624+07:00"
  },
  "pagination": null
}
```

### Get Book By Id

Request :

- Method : GET
- URL : `/api/books/{id}`

Response :
```json
{
  "status": "success",
  "errors": null,
  "data": {
    "id": "a42cb555-583a-4975-86dd-e9d88e0f17f7",
    "name": "Testing Book",
    "author": "John Doe",
    "summary": "this is summary",
    "publisher": "Informatika",
    "finished": false,
    "total_page": 100,
    "read_page": 10,
    "inserted_at": "2023-08-23T21:03:25.000+07:00",
    "updated_at": "2023-08-23T21:06:26.624+07:00"
  },
  "pagination": null
}
```

### Get List Book

Request :

- Method : GET
- URL : `/api/books?page=1&page_size=2`

Response : 
```json
{
  "status": "success",
  "errors": null,
  "data": [
    {
      "id": "a42cb555-583a-4975-86dd-e9d88e0f17f7",
      "name": "Testing Book",
      "publisher": "Informatika"
    },
    {
      "id": "a42cb555-583a-4975-86dd-e9d88e0f17f7",
      "name": "Testing Book",
      "publisher": "Informatika"
    }
  ],
  "pagination": {
    "page": 1,
    "total_page": 4,
    "has_next": true,
    "page_size": 5,
    "total_items": 19
  }
}
```

### Delete Book By Id

Request :

- Method : DELETE
- URL : `/api/books/{id}`

Response : 
```json
{
  "status": "success",
  "errors": null,
  "data": "data deleted",
  "pagination": null
}
```

## Install

```
git clone https://github.com/Ikhlashmulya/spring-bookshelf-api.git
```
```
mvn clean install
```