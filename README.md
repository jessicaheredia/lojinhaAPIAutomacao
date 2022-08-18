### Lojinha API Automação

Esse é um repositório que contem a automação de alguns testes de API Rest de um software denominado Lojinha. Os subtópicos abaixo descrevem algumas decisões tomadas na estruturação do projeto.

#### Tecnologias Utilizadas
- Java
  https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html
- Junit
  https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api/5.8.0
- RestAssured
  https://mvnrepository.com/artifact/io.rest-assured/rest-assured/5.1.1
- Maven
  https://maven.apache.org/

#### Testes Automatizados

- Testes para validar as partições de equivalência relacionadas ao valor do produto na Lojinha, que estão vinculados diretamente a regra de negocio que diz que o valor do produto deve estar entre R$0,01 e R$7.000,00 reais.

#### Notas Gerais

- Foi utilizado a anotação Before Each para capturar o token que será utilizado posteriormente nos métodos de teste.
- Armazeno os dados que são enviados para a API através do uso de classes POJO.
- Foi criado dados iniciais através do uso de classes Data Factory para facilitar a criação e controle dos mesmos.
- Nesse projeto fazemos uso do JUnit 5, o que nos da a possibilidade de usar a anotação DisplayName para dar descrições em português para os testes.
