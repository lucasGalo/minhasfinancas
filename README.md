# ProSat

[Executando aplicações Spring Boot no Docker](https://wp.me/p5RSbg-fO)

## Pré requisito
- Maven 3
- Java 19
- Docker 1.13.0+

## Preparando ambiente

```
mvn clean package dockerfile:build 
```

## Executando

Executando container da aplicação
```
docker run -it     
   --link docker-postgres     
   -p 8080:8080    
   lucasgalo/ProSat-Manager
```

```
CONTAINER ID        IMAGE                                 COMMAND                  CREATED             STATUS              PORTS                    NAMES
3b7f0cfeceaf        lucasgalo/manager-prosat   "java -Djava.securit…"   8 minutes ago       Up 7 seconds        0.0.0.0:8080->8080/tcp   manager-prosat:0.1.0
```

## Executando com Docker Compose

```
docker-compose up
```

## Acessando 

- http://localhost:8080/prosat/
- http://localhost:8080/prosat/cidades/list


### Gerando nova versão para o dockerhub

    ```bash
        docker build -t prosat-manager:0.1.0 . 
        docker tag <imageId> lucasgalo/prosat-manager:0.1.0
        docker push lucasgalo/prosat-manager:0.1.0
        docker run --name manager-prosat --detach --publish 7070:7070 lucasgalo/prosat-manager:0.1.0
    ```
