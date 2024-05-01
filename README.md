# Sistema de interação e gerenciamente de cluster Kafka

## Instruções de Configuração
O primeiro passo é baixar o repositório:
https://github.com/mbrunocesar/KafkaManager/

Tendo docker e docker-compose devidamente instalados, o projeto pode ser executado com:

```
# Nota técnica, o app roda na porta 7000 e o kafka na porta 9092)
docker-compose build app
docker-compose build up kafka app
```

## Instruções de Uso
O Swagger do projeto pode ser encontrado no link:
http://localhost:8080/swagger-ui/index.html#/

Com o swagger em mãos, podemos ver os endpoints e executar as funções desejadas.

Como praticamente todas as rotas do sistema requerem autenticação, o primeiro passo será o login.

### Login
O login por enquanto se baseia num Mock, que aceita qualquer usuário.

O único requisito é que a senha seja: **123456** 

Como resultado, sera retornado um objeto que contém um Auth Token

Exemplo:
```
curl --request POST \
  --url http://localhost:7000/auth/login \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/8.4.5' \
  --cookie JSESSIONID=9C63667CC56B479DF435B5965148BCB8 \
  --data '{
	"email": "mbrunocesar@gmail.com",
	"password": "123456"
}'
```

### Executando o Topic Manager
Com o token em mãos, podemos executar as operações de gerenciamento, tais como:
```
# GET all topics
curl --request GET \
--url http://localhost:7000/topics \
--header 'Authorization: Bearer Amberisthecolorofyourenergy' \
--header 'Content-Type: application/json' \
--header 'User-Agent: insomnia/8.4.5' \
--cookie JSESSIONID=9C63667CC56B479DF435B5965148BCB8

# POST a new Topic
curl --request POST \
  --url http://localhost:8080/topics \
  --header 'Authorization: Bearer Amberisthecolorofyourenergy' \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/8.4.5' \
  --cookie JSESSIONID=9C63667CC56B479DF435B5965148BCB8 \
  --data '{
   "name":"FirstTopic",
   "numPartitions":3
}'

# DELETE a Topic by name
curl --request DELETE \
  --url http://localhost:8080/topics/FirstTOpic \
  --header 'Authorization: Bearer Amberisthecolorofyourenergy' \
  --header 'User-Agent: insomnia/8.4.5' \
  --cookie JSESSIONID=9C63667CC56B479DF435B5965148BCB8
```

### Executando o Cluster Monitor
Com o token em mãos, podemos executar as operações de gerenciamento, tais como:
```
# GET Cluster status informations
curl --request GET \
  --url http://localhost:8080/clusters/status \
  --header 'Authorization: Bearer Amberisthecolorofyourenergy' \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/8.4.5' \
  --cookie JSESSIONID=9C63667CC56B479DF435B5965148BCB8

# GET Cluster node informations
curl --request GET \
  --url http://localhost:8080/clusters/nodes \
  --header 'Authorization: Bearer Amberisthecolorofyourenergy' \
  --header 'User-Agent: insomnia/8.4.5' \
  --cookie JSESSIONID=9C63667CC56B479DF435B5965148BCB8Na
```

## Descrições das classes/métodos

### Login
Foi implementado um Mock login, que aceita qualquer usuário com a senha 123456, e qualquer token encodado após o `Bearer`

O motivo principal foi a falta de tempo para implementar uma classe de User propriamente dita e um esquema de save/retrieve. 


### Topic Manager
A parte mais completa do sistema, é uma API RESTFUL que se comunica com um cluster Kafka e realiza operações de CRUD (sem update) no cluster.

Devido a natureza assícrona do Kafka, algumas vezes podemos ter problemas de concorrência ao redor de um dado, por essa razão o método de DELETE não espera pra saber se um dado foi removido.

Caso esperassemos pela resposta, o método poderia se tornar excessivamente lento, por isso nos testes há uma preferencia por chamar o método `getAll()` para saber se o DELETE cumpriu seu papel.


### Cluster Monitor
Como as informações básicas retornadas pelo objeto `KafkaAdmin` são muito básicas, esse método parece um tanto desnecessário.

Mas combinado com as informações do `getNodes()` é possível reunir algumas informações interessantes.

A função `getNodes()` invoka implicitamente o `getAll()` do TopicManager, enriquecendo consideravelmente os dados.


## Premissas
Pra esse projeto assumimos uma série de premisas, tais como:
- Toda operação é sempre baseada num único cluster.
- Os usuários não são má intencionados, usando apenas a senha 123456 e seus tokens gerados.
- Apesar do Kafka ter muitos processos assincronos, tratamos boa parte dos problemas como síncronos. 


## Considerações Finais
- Acabei não tendo muito tempo para implementar o projeto, então não terminei nem mesmo partes que julguei importantes e que planejei implementar.
- Nesse pacote de features adicionais que estavam planejados para implementação caso tivesse mais 1 dia:
  - Terraform
  - Deploy na AWS