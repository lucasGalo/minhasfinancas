## Evolução do código com comentarios detalhado das tarefas

### Adicioanndo lineChart
> Adicioandno lineCHart para companhar evolução de aesso por mês
> 
### Adicionando outro método de exibir maps
> Trabalahndo com a libary mapLibre, método usado no monitoração.

### Libary externa para infoCLeint por ip
> Add um libary externa para informações do usuario so pelo ip
> Referencia: https://ipinfo.io/189.5.234.45/json

### Colocando Telemetria
> Add telemetria no sistema com geoLocalização do usuario.
> Exibindo a localização dos usuarios pelo maps

### Configurado o DNS no Registro Br
> Servidor no Ar, configurado os DNS no Registro Br para apontar para 189.5.345.45.

### Mudando nome de Pro SAt para Atimosphera
> Comprado o dominio atimosphera no registro-Br, foi obrigado a alterar o nome na web. (12-04-2023)
> A empresa será Atimosphera o servico continua Pro SAT.

### Layout ADMINLTE3
> Mudando para o novo ADMINLTE 3 (29-03-2023 a 30-03-2023)

### Entidade Valor
> Add Entitade Valor para ficar a colocar e ajustando preço na cobrança automática.

### Validação de senha e criptografia de banco (27-03-2023)
> Alterado o spring para validar a senha de ususario e tbm gravar a senha criptografada.

### Add Pagamento (24-03-2023 a 26-03-2023)
> Método automatico de invalidar compra com pagamentos em aberto.
> Método automático de gerar cobrança;
> Criada a classe conbrança e pagaento para relizar a ação de compra do usuario em questão.

### Add Rol (22-03-2023 a 23-03-2023)
> Colocado a classe compra para vincular o item e usuario para cobrar pelo serviço
> após o compra será implementando a classe compra e pagamento para fazer tal ação mês a mês.

### Add MapStruct (18-03-2023 a 19-03-2023)
> Adicionado libary do mapstruct com sucesso, tive q contornar os dtos, inserindo no dto a classe q faz a chamada
> para não ter reciclicidade.

### Dockerizando a aplicação (17-03-2023 a 22-03-2023)
> 20/03/2023 -> consegui gerar um build e rodar pelo docker, porém falta ele olhar para o arquivo local application-prod.properties local
> assim finalizamos a parde de dockerização.
> Será add um arquivo Dockerfile para criar uma imagem desse projeto podendo assim dockerizaro,
> de maneira simples, no arquivo DOCKER.md componhes ordenado os comandos para criar um container desse docker no servidor