# Verificador de JWT


### Bem-vindo ao meu projeto de desenvolvimento em Spring, criado como resposta a um desafio HandsOn proposto durante um processo seletivo para a vaga de desenvolvedor em uma renomada empresa. Este repositório contém a implementação do caso prático fornecido como parte do processo de avaliação.


#### Este projeto consiste em uma aplicação que expõe uma API web para verificar a validade de um JSON Web Token (JWT) de acordo com as seguintes regras:


- Deve ser um JWT válido.
- Deve conter apenas 3 claims (Name, Role e Seed).
- A claim Name não pode conter caracteres numéricos.
- A claim Role deve conter apenas um dos três valores (Admin, Member e External).
- A claim Seed deve ser um número primo.
- O tamanho máximo da claim Name é de 256 caracteres.

#### Sobre a infraeestrutura da aplicação foi exigido que:


- Containerização da aplicação.
- Helm Chart em um cluster de Kubernetes/ECS/FARGATE.
- Repositório no GitHub.
- Deploy Automatizado para Infra-Estrutura AWS.
- scripts ci/cd.
- coleções do Insomnia ou ferramentas para execução.
- Provisione uma infraestrutura na AWS com OpenTerraform.
- expor a api em algum provedor de cloud (aws, azure...).
- Uso de Engenharia de Prompt.

  ## Premissas do Projeto


- Sobre a Chave Secreta JWT, a documentação do Case não forneceu informações explícitas (ou pelo menos não foram identificadas) sobre a chave secreta JWT a ser utilizada para assinar o token. Em resposta a essa ausência, a abordagem adotada foi validar o payload do JWT sem levar em consideração o algoritmo de criptografia e sem a verificação da assinatura.


- Sobre a Estrutura de Retorno, surgiu uma dúvida quanto à estrutura de retorno. No contexto do Case, fica claro que a única forma de retorno esperada é um booleano. Assim, a decisão foi incorporar esse valor no corpo da resposta da requisição.


- Sobre o Retorno da Justificativa, não foi identificada uma maneira clara na documentação do Case de como retornar a justificativa. Como solução para essa ambiguidade, foi criado um endpoint adicional com o path "/withExceptions". Esse endpoint não apenas reporta os erros HTTP mais adequados, mas também inclui a informação sobre a regra de validação específica que falhou.


## Como Executar o Projeto

Para executar este projeto em seu ambiente local, siga os passos abaixo:

1. **Pré-requisitos:**
   - [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)
   - [Maven](https://maven.apache.org/)

2. **Clonar o Repositório:**
   ```bash
   git clone https://github.com/seu-usuario/nome-do-projeto.git
   cd nome-do-projeto
   ```

3. **Compilar e Executar:**
   ```bash
   mvn clean install
   java -jar target/nome-do-projeto.jar
   ```

4. **Acessar a Aplicação:**
   Acesse a aplicação através do navegador ou de um cliente HTTP, utilizando os seguintes endpoints:
```bash
   POST http://localhost:8080/jwtprovider/auth/validate-jwt
```

```bash
   POST http://localhost:8080/jwtprovider/auth/validate-jwt/with-exceptions
```

## Contribuições e Feedback

Sinta-se à vontade para abrir issues para reportar bugs, sugerir melhorias ou contribuir com novas funcionalidades. Seu feedback é valioso para o aprimoramento contínuo deste projeto.

Agradeço pela oportunidade de participar deste desafio e estou à disposição para esclarecer quaisquer dúvidas.



#### Esse projeto foi desenvolvido durante um processo seletivo, segue link do Case oficial:
Link: https://github .com/ 99h58f2qe/ backend-challenge
