# Gestão-Produtos
Projeto para aprofundar conhecimentos em OOP e Persistência de dados em Java.

## Requisitos:
### Pessoa
- Admin.
- - CRUD Produtos.
  - CRUD Pessoas.

- Pessoa.
- - Vendas em Produtos.

### Produtos
- CRUD.
- - Filtrar preço ou categoria.
- Relatórios.
- - Mostrar produtos que mais saem ou produtos que estão acabando.
- Logs.
- - Mostrar qual usuário vendou o que.

## Organização:
### Views
- Pacote para separar as views do projeto (métodos que seriam equivalente as telas diferentes) do main.
### Model
- Pacote para separar as classes de "entidade" (Pessoa, Produto) do pacote principal.
### Classe Main
- Classe que irá agrupar as views e tals para modularizar o projeto.
