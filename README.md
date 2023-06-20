# Projeto VollMed-API

Vollmed é um projeto que simula o back-end de uma aplicação mobile de uma clínica médica.

| :placard: Vitrine.Dev | Guilherme Henrique |
|-----------------------|--------------------|
| :sparkles: Nome       | **VollMed-API**    |
| :label: Tecnologias   | Spring Boot, Java  |

> ### Detalhes do projeto
> - **Solicitar um token para fazer o login no sistema.**
> - **Cadastrar, Alterar, deletar, listar e detalhar um médico.**
> - **Cadastrar, Alterar, deletar, listar e detalhar um paciente.**
> - **Agendar uma consulta.**
> - **Cancelar uma consulta.**

### Rotas e Exemplos

- `POST /login`: Essa rota recebe através do corpo da requisição um JSON com os dados de `login` e `senha` cadastrado
  previamente no banco de dados, aqui utilizado o MySQL.

  ```json
  {
    "login": "guilherme@gmail.com",
    "senha": "123456"
  }
  ```

  A resposta será um token utilizando o JWT, o qual deve ser incluído nas futuras requisições, para assim evitar
  o retorno do código [__HTTP 403__](https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status/403).

####

- `POST /medicos`: Essa rota tem a finalidade de cadastrar o médico e recebe através do corpo da requisição um JSON com
  os dados: `nome`,`email`,`telefone`,`crm`,*`especialidade` e `endereço`.

  ```json
  {
    "nome": "Pedro de Lara",
    "email": "pedro-lara@yahoo.com",
    "telefone": "6532658982",
    "crm": "985236",
    "especialidade": "GINECOLOGIA",
    "endereco": {
      "logradouro": "Rua dos bobos",
      "bairro": "bairro dos bobos",
      "cep": "12345678",
      "cidade": "Cidade dos bobos",
      "uf": "MG",
      "numero": "0",
      "complemento": ""
    }
  }
  ```

  Os valores da especialidade são valores enumerados conforme a regra de negócio do projeto, aqui no caso são quatro
  possibilidades sendo elas: `ORTOPEDIA`,`CARDIOLOGIA`,`GINECOLOGIA` e `DERMATOLOGIA`.
  O endereço deve ser passado como um objeto, contendo os campos de `logradouro`,`bairro`,`cep`,`cidade`,`uf`,`numero`
  e `complemento`.

####

- `POST /pacientes`: Essa rota tem a finalidade de cadastrar o paciente e recebe através do corpo da requisição um JSON
  com
  os dados: `nome`,`email`,`telefone`,`cpf`, e `endereço`.

  ```json
  {
  "nome": "Priscila",
  "email": "priscila@yahoo.com",
  "telefone": "195654986",
  "cpf": "11111111111",
  "endereco": {
    "logradouro": "Rua dos bobos",
    "bairro": "bairro dos bobos",
    "cep": "12345678",
    "cidade": "Cidade dos bobos",
    "uf": "MG",
    "numero": "0",
    "complemento": ""
    }
  }
  ```

  O endereço deve ser passado como um objeto, contendo os campos de `logradouro`,`bairro`,`cep`,`cidade`,`uf`,`numero`
  e `complemento`.

####

- `GET /pacientes` & `GET /medicos`: Essas rotas tem a finalidade de listar os pacientes e médicos cadastrados,
  respectivamente.

  ```json
    {
    "content": [
        {
            "id": 3,
            "nome": "Pedro José",
            "email": "pedrinho@yahoo.com",
            "cpf": "99999999999",
            "telefone": "1932649265",
            "endereco": {
                "logradouro": "Rua dos bobos",
                "bairro": "bairro dos bobos",
                "cep": "12345678",
                "cidade": "Cidade dos bobos",
                "uf": "MG",
                "numero": "0",
                "complemento": ""
            }
        },
        {
            "id": 1,
            "nome": "Priscila Aniele",
            "email": "priscila@yahoo.com",
            "cpf": "11111111111",
            "telefone": "22222222222",
            "endereco": {
                "logradouro": "Rua dos bobos",
                "bairro": "bairro dos bobos",
                "cep": "12345678",
                "cidade": "Cidade dos bobos",
                "uf": "MG",
                "numero": "0",
                "complemento": ""
            }
        }
    ],
    */ retorno para paginação omitido/* 
  }
  ```
  A resposta é um array com a chave `content` e com os valores dos médicos ou pacientes cadastrados no banco de dados.

####

- `GET /pacientes/{id}` & `GET /medicos/{id}`: Essas rotas tem a finalidade de detalhar o cadastro dos pacientes ou
  médicos respectivamente, passando o `{id}` do paciente ou médico na URL.

  ```json
  {
    "id": 1,
    "nome": "Priscila Aniele",
    "email": "priscila@yahoo.com",
    "cpf": "11111111111",
    "telefone": "22222222222",
    "endereco": {
        "logradouro": "Rua dos bobos",
        "bairro": "bairro dos bobos",
        "cep": "12345678",
        "cidade": "Cidade dos bobos",
        "uf": "MG",
        "numero": "0",
        "complemento": ""
    }
  }
  ```
  A resposta é um JSON com os dados do paciente ou médico cadastrado com o `{id}` informado.  

####

- `DELETE /pacientes/{id}` & `DELETE /medicos/{id}`: Essas rota tem a finalidade de excluir o cadastro dos pacientes ou
  médicos respectivamente, passando o `{id}` do paciente ou médico na URL.

  ####

  A resposta das requisições são duas possíveis, para **sucesso** a resposta é um código
  [__HTTP 204__](https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status/204), informando que a
  requisição foi bem sucedida, porém não retornou nada. <br/>
  E, em caso de **erro**, a resposta é um código
  [__HTTP 404__](https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status/404), informando que o paciente ou médico com
  o `{id}` não foi encontrado.