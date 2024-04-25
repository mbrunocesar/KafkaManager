# Desafio Prático de Engenharia Plataforma

## Instruções
- Leia esse documento antes de iniciar as atividades.
- Você tem até 7 dias corridos para concluir as atividades aqui solicitadas e apresentar o resultado em um bate-papo técnico com especialistas;
- Fique à vontade para utilizar tecnologias, frameworks e técnicas não citadas nas atividades.
- caso não consiga concluir todas as atividades, por favor, entregue o que foi feito até a data solicitada.

## Descrição do Caso de Uso

Objetivo: desenvolver uma REST API que permita que permita a interação com um cluster Kafka rodando em container Docker, possibilitando executar operações de administração nele.
Importante: o foco do case não é o cluster Kafka portanto, pode-se utilizar uma implementação simples com um setup básico com apenas um broker, fique à vontade para usar o Docker composse para executar um Kafka da maneira mais simples possível.

## Requisitos Funcionais Mandatórios
### API de Gerenciamento de Tópicos:
- Criar tópicos no cluster Kafka.
- Listar todos os tópicos disponíveis no cluster.
- Excluir tópicos específicos.

## Requisitos Funcionais Desejáveis
### API de Monitoramento de Cluster:
- Obter o status geral do cluster Kafka.
- Listar nodos no cluster com detalhes (ID, status, quantidade de tópicos atribuídos).

## Requisitos Funcionais considerados diferenciais
### API de Gerenciamento de Consumidores:
- Listar grupos de consumidores.
- Resetar offsets de consumidores para um ponto específico no tempo ou offset.

## Requisitos Não Funcionais mandatório
### Documentação:
Swagger ou outra ferramenta de documentação de API para documentar os endpoints.

## Requisitos Não Funcionais Diferenciais
Segurança: Autenticação e autorização para acessar as APIs.

## 
### Critérios de Aceitação
- O código deve ser escrito em uma linguagem de programação moderna de sua escolha.
- O projeto deve ser conteinerizado com Docker para fácil implantação e isolamento de ambiente.
- Utilizar práticas de código limpo e padrões de design.
- Implementar logs detalhados para facilitar o diagnóstico de problemas.
- O projeto deve ser versionado em um repositório Git, com commits bem estruturados e descritivos.

### Pontos que daremos maior atenção
- Testes de unidade / integração
- Abstração, acoplamento, extensibilidade
- Design de API
- SOLID
- Commits realizados durante a construção
- Observability (Logging/Tracing/Monitoring)

### Demais Itens
- Conteinerização da aplicação
- Deploy Automatizado para Infraestrutura AWS via terraform
- Realizar o deploy dos containers em cluster de Kubernetes/ECS ou outro que achar mais confortável.
- Repositório no GitHub.
- expor a API  no provedor de cloud AWS


### Sobre a documentação

Nesta etapa do processo seletivo queremos entender as decisões por trás do código, portanto é fundamental que o README tenha algumas informações referentes a sua solução.

### Algumas dicas do que esperamos ver são:
- Instruções básicas de como executar o projeto;
- Detalhes da descrição dos métodos
- Caso algo não esteja claro e você precisou assumir alguma premissa, quais foram e o que te motivou a tomar essas decisões.
