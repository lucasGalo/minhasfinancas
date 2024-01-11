## Docker imagem pronta no dockerHub

## Criando uma imagen e push para dockerhub a partir do projeto.

1. **Deletar imagens antigas**
    ```bash
        docker rmi minhas-financas:0.0.1
    ``` 
2. **Build do projeto**
    ```bash
        docker build -t minhas-financas:0.0.1 .
    ``` 
3. **Tag do build**
   ```bash
        docker tag 5ee1dd41f102 lucasgalo/minhas-financas:0.0.1
   ```
4. **Login dockerhub**
   ```bash
        docker login
   ```
5. **Push da tag**
    ```bash
        docker push lucasgalo/minhas-financas:0.0.1
    ```

## Container create example:

1. **Create work directories:**
    ```bash
    mkdir -p /home/minhas-financas/logs    
    ```
2. **Permissao de leitura e escrita para a pasta**
   ```bash
     sudo chmod 777 -R /home/minhas-financas/logs
   ```
3. **Create container:**
    ```bash
    docker run \
    --name minhas-financas \
    --hostname minhasfinancas \
    --detach --restart unless-stopped \
    --publish 12458:12458 \
    --volume /home/minhas-financas/logs:/opt/minhasfinancas/logs:rw \
    --volume /home/minhas-financas/application-prod.properties:/opt/minhasfinancas/src/resources/application-prod.properties:ro \
    lucasgalo/minhas-financas:0.0.1
    ```   
4. **Create container, servidor externo**
    ```bash
     docker run --name minhas-financas --network="host" --detach --restart unless-stopped --publish 12458:12458 --volume /home/minhas-financas/logs:/opt/minhasfinancas/logs:rw --volume /home/minhas-financas/application-prod.properties:/opt/minhasfinancas/src/resources/application-prod.properties:ro lucasgalo/minhas-financas:0.0.1
    ```
5. **Other option create container local:**
    ```bash
    docker run --name minhas-financas --network="host" --detach --restart unless-stopped --publish 12458:12458 minhas-financas:0.0.1
    ```
6. **Olhar os logs do container criado:**
    ```bash
    docker container logs 7b2   
    ```

