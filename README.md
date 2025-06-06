## Configuração de Banco de Dados

Crie o arquivo `src/main/resources/application.properties` copiando o conteúdo de `application-template.properties` e preencha:

- `NOME_DO_BANCO` com o nome do seu banco
- `SEU_USUARIO` com o seu usuário MySQL
- `SUA_SENHA` com a sua senha do banco

**Exemplo:**
spring.datasource.url=jdbc:mysql://localhost:3306/atendimento?createDatabaseIfNotExist=true&serverTimezone=America/Sao_Paulo
spring.datasource.username=root
spring.datasource.password=suasenha
