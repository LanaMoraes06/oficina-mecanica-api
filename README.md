# 🛠️ API - Sistema para Oficina Mecânica

Uma API RESTful robusta desenvolvida em **Java** com **Spring Boot** para gerenciar as operações diárias de uma oficina mecânica. 

Este sistema controla desde o cadastro de clientes e seus veículos até o gerenciamento de estoque de peças e o ciclo de vida completo de uma Ordem de Serviço (OS), calculando custos e automatizando a baixa de estoque.

## 🚀 Tecnologias Utilizadas

* **Java** 
* **Spring Boot** (Web, Data JPA, Validation)
* **Banco de Dados H2** (Em memória, para testes rápidos)
* **Lombok** (Redução de boilerplate)
* **Padrão DTO** (Data Transfer Object) para segurança e isolamento da camada de dados
* **UUID** para geração de identificadores únicos e seguros

## ⚙️ Funcionalidades e Regras de Negócio

* **Gestão de Clientes e Endereços:** Relacionamento 1:1, garantindo que todo cliente possua um endereço atrelado.
* **Gestão de Veículos:** Relacionamento N:N com clientes (um carro pode pertencer a mais de uma pessoa e vice-versa). Prevenção de loop infinito de JSON utilizando `@JsonIgnoreProperties`.
* **Controle de Estoque de Peças:** Cadastro de peças com controle rigoroso de quantidade e inativação lógica.
* **Ordens de Serviço (OS):** 
  * Abertura de OS vinculada obrigatoriamente a um veículo.
  * Inserção de Peças/Serviços na OS com validação de estoque em tempo real (não é possível adicionar peças se não houver saldo).
  * **Cálculo Automático:** Ao chamar o endpoint de finalizar OS, o sistema calcula o valor total baseado nas peças utilizadas e atualiza o status para `CONCLUIDA`.
  * **Cancelamento Seguro:** O cancelamento de uma OS devolve automaticamente as peças utilizadas para o estoque principal.

## 📦 Como rodar o projeto localmente

### Pré-requisitos
* Java 17 ou superior
* Maven
* Postman, Insomnia ou similar para testar as rotas

### Passo a passo

1. Clone o repositório:
   ```bash
   git clone [https://github.com/LanaMoraes06/sistema-oficina-mecanica.git](https://github.com/LanaMoraes06/sistema-oficina-mecanica.git)
   '''
   Abra o projeto na sua IDE e aguarde o Maven baixar as dependências.

Execute a classe principal SistemaOficinaMecanicaApplication.java.

O servidor iniciará na porta 8080.

Acesso ao Banco de Dados (Console H2):
O projeto está configurado com a diretriz create-drop. A cada inicialização, o banco é recriado e populado através do arquivo data.sql para facilitar os testes.

URL no navegador: http://localhost:8080/h2-console

Driver: org.h2.Driver

JDBC URL: jdbc:h2:mem:oficina_db

User: sa

Password: (deixe em branco)

###🔗 Principais Endpoints da API
👤 Clientes
POST /clientes - Cadastra um novo cliente junto com seu endereço.

GET /clientes - Lista todos os clientes ativos.

PUT /clientes/{id} - Atualiza dados do cliente.

DELETE /clientes/{id} - Desativa um cliente (Exclusão Lógica).

### 🚗 Veículos
POST /veiculos - Cadastra um veículo e já vincula aos IDs dos clientes donos.

GET /veiculos - Lista todos os veículos.

### ⚙️ Peças
POST /pecas - Adiciona uma nova peça ao estoque.

GET /pecas - Lista as peças disponíveis em estoque.

### 📋 Ordens de Serviço
POST /ordemservicos - Abre uma nova OS para um veículo (Inicia com valor zerado e status ABERTA).

GET /ordemservicos - Lista todas as Ordens de Serviço.

PUT /ordemservicos/{id}/finalizar - Calcula o valor total das peças usadas, consolida o preço e fecha a OS.

PUT /ordemservicos/{id}/cancelar - Cancela a OS e estorna os itens diretamente para o estoque.

### 🔧 Itens da OS
POST /itens-peca - Adiciona uma quantidade de peças a uma OS aberta (desconta do estoque na mesma hora).
