# project-module-user

## description
#### Project Crud User

## PostgreSQL

## Install 
#### open folder docker in terminal, cd docker
#### execute docker-compose up -d

## View PosgreSQL in PgAdmin4
#### open folder docker in terminal, cd docker
#### docker ps
#### copy CONTAINER ID  the image: postgres:latest
#### docker inspect ${CONTAINER_ID}

## SWAGGER
#### View in http://localhost:8080/swagger-ui/index.html

## Curl
```bash
curl --location --request POST 'localhost:8080/api/v1/user' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "John Doe",
    "email": "johndoe@example.com",
    "password": "password123", 
    "phones": "555-1234,555-5678",
    "lastLogin": "2023-10-26T10:30:00",
    "isActive": true
}
'
```

```bash
curl --location --request GET 'localhost:8080/api/v1/user?email=johndoe%40example.com' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "John Doe",
    "email": "johndoe@example.com",
    "password": "password123", 
    "phones": "555-1234,555-5678",
    "lastLogin": "2023-10-26T10:30:00",
    "isActive": true
}
'
```

```bash
curl --location --request PATCH 'localhost:8080/api/v1/user' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "John Doe xd",
    "email": "johndoe@example.com",
    "password": "password123", 
    "phones": "555-1234,555-5678",
    "lastLogin": "2023-10-26T10:30:00",
    "isActive": true
}
'
```

```bash
curl --location --request DELETE 'localhost:8080/api/v1/user?id=69a2b94a-0d42-4980-b3ae-52a87321254f' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "John Doe",
    "email": "johndoe@example.com",
    "password": "password123", 
    "phones": "555-1234,555-5678",
    "lastLogin": "2023-10-26T10:30:00",
    "isActive": true
}
'
```