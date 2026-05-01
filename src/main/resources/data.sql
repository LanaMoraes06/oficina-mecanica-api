-- 1. Inserir Endereços
INSERT INTO tb_endereco (id, endereco_logradouro, endereco_numero, endereco_bairro, endereco_cidade, endereco_cep)
VALUES ('e1111111-1111-1111-1111-111111111111', 'Av. Rui Barbosa', '100', 'Centro', 'Assis', '19814-000'),
       ('e1111111-1111-1111-1111-111111111112', 'Rua Sao Paulo', '200', 'Vila Operaria', 'Assis', '19800-000');

-- 2. Inserir Clientes (Usando o endereco_id e cliente_ativo)
INSERT INTO tb_cliente (cliente_id, cliente_nome, cliente_cpf, cliente_ativo, endereco_id)
VALUES ('11111111-1111-1111-1111-111111111111', 'Lana Alves de Moraes', '12345678901', TRUE,
        'e1111111-1111-1111-1111-111111111111'),
       ('11111111-1111-1111-1111-111111111112', 'Allan David de Moraes', '12345678902', TRUE,
        'e1111111-1111-1111-1111-111111111112');

-- 3. Inserir Veículos
INSERT INTO tb_veiculo (veiculo_id, veiculo_placa, veiculo_modelo, veiculo_ano_fabricacao, veiculo_ativo)
VALUES ('22222222-2222-2222-2222-222222222221', 'ABC1D23', 'Honda Civic', '2024-01-10', TRUE),
       ('22222222-2222-2222-2222-222222222222', 'XYZ9E87', 'Toyota Corolla', '2023-05-20', TRUE);

-- 4. Inserir Peças
INSERT INTO tb_peca (peca_id, peca_nome, peca_fabricante, peca_preco, peca_qtd_estoque, peca_ativo)
VALUES ('33333333-3333-3333-3333-333333333331', 'Pastilha de Freio', 'Brembo', 250.00, 50, true),
       ('33333333-3333-3333-3333-333333333332', 'Oleo 5W30 (Litro)', 'Castrol', 55.00, 100, true);

-- 5. Inserir Ordens de Serviço
INSERT INTO tb_ordem_servico (ordem_servico_id, veiculo_id, ordem_servico_valor_total, status)
VALUES ('44444444-4444-4444-4444-444444444441', '22222222-2222-2222-2222-222222222221', 0.0, 'ABERTA');

-- 6. Inserir Itens de Peça
INSERT INTO tb_item_peca (ordem_servico_id, peca_id, qtd_utilizada)
VALUES ('44444444-4444-4444-4444-444444444441', '33333333-3333-3333-3333-333333333332', 4);