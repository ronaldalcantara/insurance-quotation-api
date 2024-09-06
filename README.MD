<h1>Insurance Quotation API</h1>
<h2>Descrição</h2>
Esta aplicação é uma API de Cotação de Seguros construída utilizando Spring Boot e organizada com base na Arquitetura Hexagonal. O sistema processa cotações de seguros, gerencia produtos, ofertas e utiliza o Kafka para mensageria, além de persistir os dados em um banco de dados H2. Ela também segue os princípios SOLID, Clean Code e utiliza padrões de projeto como Builder.

<h2>Funcionalidades</h2>
- Solicitação e processamento de cotações de seguros.
- Persistência de dados utilizando o banco H2.
- Comunicação assíncrona com Kafka para envio de cotações e recebimento de apólices.
- Consultas de cotações existentes.
- Testes unitários utilizando BDD com Cucumber e Mockito.

<h2>Tecnologias Utilizadas</h2>
- Java e Spring Boot
- Spring Data JPA (para persistência de dados no H2)
- Kafka (para mensageria)
- Mockito (para testes BDD)
- Arquitetura Hexagonal
- Princípios SOLID e Clean Code

<h2>Configuração</h2>
<h3>Banco de Dados H2</h3>
O banco H2 é utilizado como banco de dados em memória para facilitar o desenvolvimento e testes.

<h3>Kafka</h3>
A aplicação utiliza Kafka para enviar e receber mensagens de cotações e apólices de seguros.

<h3>Dependências</h3>
No pom.xml foram configuradas as seguintes dependências principais:

- Spring Boot
- Spring Data JPA
- Spring Kafka
- H2 Database
- Lombok
- Mockito

<h3>Variáveis de Configuração</h3>
No arquivo <b>application.yml</b>, configuramos o banco H2 e Kafka, além de definir o nome e a versão da aplicação.

<h3>Execução</h3>
<h3>Pré-requisitos</h3>
- Docker e Docker Compose (para rodar o Kafka localmente)
- Java 11+
Passos
1. Clone o repositório.
2. Execute o comando mvn clean install para instalar as dependências.
3. Use o Docker para iniciar o Kafka: <code>docker-compose up -d</code>
4. Inicie a aplicação: <code>mvn spring-boot:run</code>

<h3>Testes</h3>
A aplicação inclui testes com foco em BDD, utilizando Cucumber e Mockito. 
Para rodar os testes: <code>mvn test</code>

<h3>Padrões e Princípios SOLID:</h3> 
A aplicação segue os princípios de responsabilidade única, aberto/fechado e baixo acoplamento.

<h3>Clean Code:</h3> 
Código simples, modular e fácil de entender.

<h3>Padrão Builder:</h3>
Utilizado para construir entidades complexas, como cotações.

<h3>Contribuição</h3>
1. Crie uma nova branch: <code>git checkout -b minha-branch</code>
2. Faça as alterações desejadas e commite: <code>git commit -m 'Minha contribuição'</code>
3. Envie sua branch: <code>git push origin minha-branch</code>
4. Crie um Pull Request.
