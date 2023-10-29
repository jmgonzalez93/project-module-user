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

### CREATE USER
```bash
curl --location --request POST 'localhost:8080/api/v1/user' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "John Doe",
    "email": "jonatan@gmail.com",
    "password": "123456a",
    "phones": [
        {
            "number": "21212",
            "city_code": "1",
            "country_code": "11"
        },
        {
            "number": "212",
            "city_code": "1",
            "country_code": "57"
        }
    ]
}'
'
```
### GET USER
```bash
curl --location --request GET 'localhost:8080/api/v1/user?email=jonatan%40example.com' \
--header 'Content-Type: application/json' \
--data ''
```

### UPDATE USER
```bash
curl --location --request PATCH 'localhost:8080/api/v1/user' \
--header 'Content-Type: application/json' \
--data-raw '{
        "id": "e0b3ec74-b27e-466c-9b12-83d37ae742d8",
        "name": "John Doe",
        "email": "jonatan@example.com",
        "password": "123456a",
        "phones": [
            {
                "number": "6666",
                "city_code": "1",
                "country_code": "11"
            }
        ],
        "created": "2023-10-29T13:44:55.579159",
        "modified": "2023-10-29T13:44:55.579159",
        "last_login": "2023-10-29T13:44:55.579159",
        "token": "5b9d3121-e51f-4300-8f5e-c0cc54d05953",
        "is_active": true
    }'
'
```
### LOGIN
```bash
curl --location --request POST 'localhost:8080/api/v1/login?email=jonatan%40example.com' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer 5b9d3121-e51f-4300-8f5e-c0cc54d05953' \
--data-raw '{
    "name": "John Doe",
    "email": "froxzx@example.com",
    "password": "password123",
    "phones": [
        {
            "number": "21212",
            "city_code": "1",
            "country_code": "11"
        },
        {
            "number": "212",
            "city_code": "1",
            "country_code": "57"
        }
    ]
}'
```

### DELETE USER
```bash
curl --location --request DELETE 'localhost:8080/api/v1/user?id=e0b3ec74-b27e-466c-9b12-83d37ae742d8' \
--header 'Content-Type: application/json' \
--data ''
```