INSERT INTO produto (ID, NOME, DESCRICAO, QUANTIDADE_UTILIZADA, PRECO, NOME_IMAGEM)
VALUES
    ('8da7f6de-2eb8-49d1-b014-abe38de2a47c', 'Cafe Expresso', 'Cafe expresso forte e aromatico', 10, 4.99, 'testeimagem.png'),
    ('da67814e-4eeb-4f2c-9a30-84e06f419294', 'Cafe Cappuccino', 'Cappuccino com espuma de leite cremosa', 20, 5.99, 'testeimagem.png'),
    ('c4a40ce8-5a7a-47cc-8388-b1d7dc6ac12d', 'Cafe Latte', 'Cafe com leite suave e cremoso', 15, 5.49, 'testeimagem.png'),
    ('26fe7b16-4a1a-48b4-816b-5f51541c2391', 'Cafe Mocha', 'Cafe com chocolate e leite vaporizado', 5, 6.49, 'testeimagem.png'),
    ('11111111-1111-1111-1111-111111111111', 'Cafe Americano', 'Cafe diluido com agua quente', 8, 3.99, 'testeimagem.png'),
    ('22222222-2222-2222-2222-222222222222', 'Cafe Macchiato', 'Expresso com um toque de espuma de leite', 12, 4.79, 'testeimagem.png'),
    ('33333333-3333-3333-3333-333333333333', 'Cafe Gelado', 'Cafe gelado refrescante', 18, 5.29, 'testeimagem.png'),
    ('44444444-4444-4444-4444-444444444444', 'Cafe Vienense', 'Cafe com chantilly', 22, 5.99, 'testeimagem.png'),
    ('55555555-5555-5555-5555-555555555555', 'Cafe Irlandes', 'Cafe com whisky e chantilly', 16, 7.99, 'testeimagem.png'),
    ('66666666-6666-6666-6666-666666666666', 'Cafe Cortado', 'Expresso com uma pequena quantidade de leite', 14, 4.49, 'testeimagem.png'),
    ('77777777-7777-7777-7777-777777777777', 'Cafe Doppio', 'Duplo expresso', 9, 5.99, 'testeimagem.png'),
    ('88888888-8888-8888-8888-888888888888', 'Cafe Affogato', 'Expresso servido sobre uma bola de sorvete', 7, 6.99, 'testeimagem.png'),
    ('99999999-9999-9999-9999-999999999999', 'Cafe Ristretto', 'Expresso mais concentrado e menos amargo', 11, 4.99, 'testeimagem.png'),
    ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Cafe Breve', 'Expresso com metade leite e metade creme', 13, 5.49, 'testeimagem.png'),
    ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'Cafe Lungo', 'Expresso longo, mais suave que o ristretto', 6, 4.99, 'testeimagem.png'),
    ('cccccccc-cccc-cccc-cccc-cccccccccccc', 'Cafe Flat White', 'Cafe com leite vaporizado, similar ao latte', 4, 5.49, 'testeimagem.png'),
    ('dddddddd-dddd-dddd-dddd-dddddddddddd', 'Cafe Caramel Macchiato', 'Macchiato com caramelo', 3, 6.49, 'testeimagem.png'),
    ('eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee', 'Cafe Marocchino', 'Expresso com cacau em po e espuma de leite', 17, 5.99, 'testeimagem.png'),
    ('ffffffff-ffff-ffff-ffff-ffffffffffff', 'Cafe Bombon', 'Cafe expresso com leite condensado', 21, 5.99, 'testeimagem.png'),
    ('gggggggg-gggg-gggg-gggg-gggggggggggg', 'Cafe Turco', 'Cafe turco forte e espesso', 25, 4.99, 'testeimagem.png');


INSERT INTO funcionarios (ID, NOME, EMAIL, SENHA, ENTRADA, SAIDA, TIPO_FUNCIONARIO)
VALUES
    ('11111111-1111-1111-1111-111111111111', 'Joao Silva', 'joao.silva@cafeteria.com', 'senha123', PARSEDATETIME('08:00:00', 'HH:mm:ss'), PARSEDATETIME('16:00:00', 'HH:mm:ss'), 0),
    ('22222222-2222-2222-2222-222222222222', 'Maria Santos', 'maria.santos@cafeteria.com', 'senha123', PARSEDATETIME('09:00:00', 'HH:mm:ss'), PARSEDATETIME('17:00:00', 'HH:mm:ss'), 0),
    ('33333333-3333-3333-3333-333333333333', 'Carlos Oliveira', 'carlos.oliveira@cafeteria.com', 'senha123', PARSEDATETIME('10:00:00', 'HH:mm:ss'), PARSEDATETIME('18:00:00', 'HH:mm:ss'), 0),
    ('44444444-4444-4444-4444-444444444444', 'Ana Costa', 'ana.costa@cafeteria.com', 'senha123', PARSEDATETIME('11:00:00', 'HH:mm:ss'), PARSEDATETIME('19:00:00', 'HH:mm:ss'), 1);


INSERT INTO ESTOQUE (ID, NOME, QUANTIDADE, ID_PRODUTO)
VALUES
    ('11111111-1111-1111-1111-111111111111', 'Cafe em po', 2, NULL),
    ('22222222-2222-2222-2222-222222222222', 'Teste', 5, NULL),
    ('33333333-3333-3333-3333-333333333333', 'Leite', 10, NULL),
    ('44444444-4444-4444-4444-444444444444', 'Acucar', 15, NULL),
    ('55555555-5555-5555-5555-555555555555', 'Chocolate em po', 20, NULL),
    ('66666666-6666-6666-6666-666666666666', 'Canela em po', 25, NULL),
    ('77777777-7777-7777-7777-777777777777', 'Nata', 30, NULL),
    ('88888888-8888-8888-8888-888888888888', 'Baunilha', 35, NULL),
    ('99999999-9999-9999-9999-999999999999', 'Creme de leite', 40, NULL),
    ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Leite condensado', 45, NULL),
    ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'Cafe soluvel', 50, NULL),
    ('cccccccc-cccc-cccc-cccc-cccccccccccc', 'Cafe Arabica', 55, NULL),
    ('dddddddd-dddd-dddd-dddd-dddddddddddd', 'Cafe Robusta', 60, NULL),
    ('eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee', 'Cafe Descafeinado', 65, NULL),
    ('ffffffff-ffff-ffff-ffff-ffffffffffff', 'Xarope de Caramelo', 70, NULL),
    ('11112222-2222-2222-2222-222222222222', 'Xarope de Baunilha', 75, NULL),
    ('22223333-3333-3333-3333-333333333333', 'Xarope de Chocolate', 80, NULL),
    ('33334444-4444-4444-4444-444444444444', 'Xarope de Amendoa', 85, NULL),
    ('44445555-5555-5555-5555-555555555555', 'Cacau em po', 90, NULL),
    ('55556666-6666-6666-6666-666666666666', 'Chantilly', 95, NULL),
    ('66667777-7777-7777-7777-777777777777', 'Creme de avela', 65, NULL);


INSERT INTO cliente (id, cpf, email, endereco, nome, senha, telefone)
VALUES
('6bd22abd-383a-434d-8ab3-f9899c458519', '12345678909', 'clienteteste@gmail.com', 'endereco teste', 'clienteTeste', '12345678', '12345678');


INSERT INTO pedido (PRECO_TOTAL, TIPO_PEDIDO, CREATED_AT, ID, ID_CLIENTE, ID_FUNCIONARIO, SITUACAO)
VALUES
(16.47, 1, '2024-05-19 16:42:40.149422', '5892584e-ec2a-46b0-862f-ba4bdcb94f3a', '6bd22abd-383a-434d-8ab3-f9899c458519', NULL, 'EM_ANDAMENTO');


INSERT INTO item_produto (PRECO_PRODUTO, QUANTIDADE, ID, ID_PEDIDO, ID_PRODUTO)
VALUES
(4.99, 1, 'a31d707a-83fc-400f-b379-1cf54b149eec', '5892584e-ec2a-46b0-862f-ba4bdcb94f3a', '8da7f6de-2eb8-49d1-b014-abe38de2a47c'),
(5.99, 1, '9c487725-e102-4d35-a27b-0fdd9ba1774b', '5892584e-ec2a-46b0-862f-ba4bdcb94f3a', 'da67814e-4eeb-4f2c-9a30-84e06f419294'),
(5.49, 1, '7491ec6a-9baf-446f-a248-970e52ecf132', '5892584e-ec2a-46b0-862f-ba4bdcb94f3a', 'c4a40ce8-5a7a-47cc-8388-b1d7dc6ac12d');
