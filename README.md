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
#### copy NetworkSettings.IPAddress
#### in http://localhost:85
#### login  user: admin@gmail.com password: pass
#### create connections, replace host for value NetworkSettings.IPAddress and password = admin



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
--data ''
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
curl --location --request DELETE 'localhost:8080/api/v1/user?id=eb15fc93-ca82-4bee-9e0f-1e3e618d45fc' \
--header 'Content-Type: application/json' \
--data ''
```