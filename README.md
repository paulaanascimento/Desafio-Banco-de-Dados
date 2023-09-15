# Desafio - Banco de Dados Sistema de Vendas
<div align="justify">
Este projeto foi desenvolvido como parte de um desafio para criar e manipular um banco de dados para um sistema de vendas. Ele inclui a criação de tabelas, inserção de valores e execução de consultas SQL específicas. O sistema é destinado a registrar vendas em uma organização.
<div>

## Descrição

O projeto consiste nas seguintes tabelas no banco de dados:

- `clients` para armazenar informações sobre clientes, incluindo nome, email, CPF e endereço.
- `sellers` para armazenar informações sobre vendedores, incluindo nome, email, CPF e salário.
- `products` para armazenar informações sobre produtos, incluindo nome e preço.
- `sales` para registrar vendas, incluindo informações sobre o vendedor, o cliente e o valor total da venda.
- `sales_products` para registrar os produtos vendidos em cada venda, incluindo a quantidade.

## Funcionalidades

O sistema oferece as seguintes funcionalidades:

- Cadastro de clientes, vendedores, produtos, vendas e produtos de venda.
- Listagem de todos os clientes, vendedores, produtos, vendas e produtos de venda.
- Pesquisa específica de clientes, vendedores, produtos, vendas e produtos de venda.
- Atualização de informações de clientes, vendedores, produtos, vendas e produtos de venda.
- Exclusão de clientes, vendedores, produtos, vendas e produtos de venda.
- Pesquisa de vendas com valor acima de R$10.00.
- Atualização de valores totais nulos em vendas para zero.
- Listagem de salários dos vendedores, ordernados do maior para o menor.
- Listagem de pessoas que possuem email `zup.com.br`

## Script SQL

Script utilizado para a criação das tabelas:
```
create table clients (
    cpf varchar(14) primary key,
    name varchar(255),
    email varchar(255),
    address varchar(255)
);

create table sellers (
    cpf varchar(14) primary key,
    name varchar(255),
    email varchar(255),
    salary decimal(10, 2)
);

create table products (
    product_id serial primary key,
    product_name varchar(255),
    product_price decimal(10, 2) not null check (product_price > 0)
);

create table sales (
    sale_id serial primary key,
    seller_cpf varchar(14) not null,
    client_cpf varchar(14) not null,
    total_value decimal(10, 2),
    constraint fk_seller foreign key(seller_cpf) references sellers(cpf),
    constraint fk_client foreign key (client_cpf) references clients(cpf)
    on delete cascade
);

create table sales_products (
    sale_id int not null,
    product_id int not null,
    quantity int,
    primary key (sale_id, product_id),
    constraint fk_sale foreign key (sale_id) references sales(sale_id),
    constraint fk_product foreign key (product_id) references products(product_id)
    on delete cascade
);
```
