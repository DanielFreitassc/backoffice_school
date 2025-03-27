# Como executar a api
Clone o repositório na sua maquina
```yml
https://github.com/DanielFreitassc/backoffice_school.git
```
Dentro da pasta backend abra um terminal e cole o comando abaixo.
```yml
docker build -t api .
```
Apos terminar o processo de build rode o docker image.
```yml
docker run -dti -p 8080:8080 --name api api 
```
Apos o termino abra uma Ferramenta de teste de API de sua prefêrencia. 

Você pode executar o projeto e abrir o Swagger que é gera uma documentação automática 

[Link🚀](http://localhost:8080/swagger-ui/index.html)

--- 
# Cadastrar usuário
Base URL
```yml
http://localhost:8080
```
Método: POST

Endpoint
```yml
/user
```
Payload
```json
{
    "name":"Fulano",
    "username":"fulano-01",
    "password":"Senha12345",
    "role":"ALUNO"
}
```
 Roles: ALUNO, PROFESSOR, ADMIN

Response (201 CREATED):
```yml
{
    "message": "Usuário criado com sucesso;"
}
```
----

# Buscar usuário

Método: GET

Endpoint 
```yml
/user
```
Response (200 OK):
```json
[
    {
        "id": "a3a81597-eeb2-4083-ade9-46e7d8b6ea48",
        "name": "Admin",
        "username": "admin",
        "role": "ADMIN",
        "createdAt": "17/08/2024"
    },
    {
        "id": "aa8b2afa-f38f-4091-9195-bd7d59fffc24",
        "name": "Fulano",
        "username": "fulano-01",
        "role": "ALUNO",
        "createdAt": "17/08/2024"
    }
]
```
----

# Buscar usuário por username

Método: GET

Endpoint 
```yml
/user/{username}
```
Response (200 OK):
```json
{
    "id": "aa8b2afa-f38f-4091-9195-bd7d59fffc24",
    "name": "Fulano",
    "username": "fulano-01",
    "role": "ALUNO",
    "createdAt": "17/08/2024"
}
```
---
# Atualizar usuário por username

Método: PATCH

Endpoint
```yml
/user/{username}
```
Payload
```json
{
    "name":"Fulano de Tal",
    "username":"fulano-01",
    "role":"ALUNO"
}
```
Response (200 OK):
```json
{
    "message": "Usuário atualizado com sucesso;"
}
```
---
# Remover usuário por username

Método: DELETE

Endpoint 
```yml
/user/{username}
```
Response (200 OK):
```json
{
    "message": "Usuário removido com sucesso;"
}
```
---
# Fazer login

Método: POST

Endpoint
```yml
/auth/login
```
Paylod
```json
{
    "username":"admin",
    "password":"Admin12345"
}
```
Response (200 OK)
```yml
{
    "token": "eyJhbGci...."
}
```

 