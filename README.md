# Projeto Spring Boot com MongoDB

Este projeto Spring Boot demonstra a integração com o MongoDB para gerenciar usuários e posts.

## Funcionalidades

- **Gerenciamento de Usuários**:
    - Cadastro de novos usuários.
    - Busca de usuários por ID.
    - Listagem de todos os usuários.
    - Atualização de informações de usuários.
    - Exclusão de usuários.

- **Gerenciamento de Posts**:
    - Criação de novos posts.
    - Busca de posts por ID.
    - Exclusão de posts.
    - Atualização de posts.
    - Busca de posts por data (antes ou depois de uma data específica).
    - Busca de posts por título.

## Tecnologias Utilizadas
- **Spring Boot:** Framework para desenvolvimento de aplicações Java.
- **MongoDB:** Banco de dados NoSQL orientado a documentos.
- **Spring Data MongoDB:** Simplifica o acesso ao MongoDB a partir de aplicações Spring.

## Como Executar

1. **Requisitos**:
    - Java Development Kit (JDK) 8 ou superior.
    - MongoDB instalado e em execução na sua máquina.

2. **Configuração**:
    - Configure a URL de conexão com o MongoDB no arquivo `application.properties` ou `application.yml`.

3. **Inicialização**:
    - Execute a classe principal da aplicação [SpringmongoApplication.java](src/main/java/com/eletrinho/springmongo/SpringmongoApplication.java) para iniciar o servidor Spring Boot.

## Próximos Passos
- Implementar autenticação e autorização para usuários.
- Criar uma interface de usuário (ex: Angular, React) para interagir com a API.
- Implementar testes unitários e de integração para garantir a qualidade do código.
- Adicionar tratamento de erros mais robusto.

## Contribuindo
Contribuições são bem-vindas! Sinta-se à vontade para abrir um pull request com suas sugestões de melhorias.

## Reconhecimentos

Este projeto foi desenvolvido como parte de um [curso de programação em Java](https://www.udemy.com/course/java-curso-completo/).