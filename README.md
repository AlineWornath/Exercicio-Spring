Este projeto NÃO versiona usuário e senha do banco de dados. Para definir suas credenciais no ambiente local, utilize uma das opções abaixo:

1. Usando um arquivo local.properties (recomendado)
   
Crie o arquivo local.properties na pasta src/main/resources com o seguinte conteúdo:

    spring.datasource.username=SEU_USUARIO

    spring.datasource.password=SUA_SENHA

O arquivo local.properties já está incluído no .gitignore e NÃO deve ser adicionado ao controle de versão.

2. Preenchendo diretamente no application.properties


   Se preferir, você pode inserir suas credenciais diretamente no arquivo src/main/resources/application.properties:


    spring.datasource.username=SEU_USUARIO

    spring.datasource.password=SUA_SENHA

3. Utilizando variáveis de ambiente
   Você também pode definir as credenciais por meio de variáveis de ambiente ao rodar a aplicação.

