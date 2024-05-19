INSERT INTO produto (ID, NOME, DESCRICAO, QUANTIDADE_UTILIZADA, PRECO, NOME_IMAGEM)
VALUES
    (RANDOM_UUID(), 'Cafe Expresso', 'Cafe expresso forte e aromatico', 10, 4.99, 'testeimagem.png'),
    (RANDOM_UUID(), 'Cafe Cappuccino', 'Cappuccino com espuma de leite cremosa', 20, 5.99, 'testeimagem.png'),
    (RANDOM_UUID(), 'Cafe Latte', 'Cafe com leite suave e cremoso', 15, 5.49, 'testeimagem.png'),
    (RANDOM_UUID(), 'Cafe Mocha', 'Cafe com chocolate e leite vaporizado', 5, 6.49, 'testeimagem.png'),
    (RANDOM_UUID(), 'Cafe Americano', 'Cafe diluido com agua quente', 8, 3.99, 'testeimagem.png'),
    (RANDOM_UUID(), 'Cafe Macchiato', 'Expresso com um toque de espuma de leite', 12, 4.79, 'testeimagem.png'),
    (RANDOM_UUID(), 'Cafe Gelado', 'Cafe gelado refrescante', 18, 5.29, 'testeimagem.png'),
    (RANDOM_UUID(), 'Cafe Vienense', 'Cafe com chantilly', 22, 5.99, 'testeimagem.png'),
    (RANDOM_UUID(), 'Cafe Irlandes', 'Cafe com whisky e chantilly', 16, 7.99, 'testeimagem.png'),
    (RANDOM_UUID(), 'Cafe Cortado', 'Expresso com uma pequena quantidade de leite', 14, 4.49, 'testeimagem.png'),
    (RANDOM_UUID(), 'Cafe Doppio', 'Duplo expresso', 9, 5.99, 'testeimagem.png'),
    (RANDOM_UUID(), 'Cafe Affogato', 'Expresso servido sobre uma bola de sorvete', 7, 6.99, 'testeimagem.png'),
    (RANDOM_UUID(), 'Cafe Ristretto', 'Expresso mais concentrado e menos amargo', 11, 4.99, 'testeimagem.png'),
    (RANDOM_UUID(), 'Cafe Breve', 'Expresso com metade leite e metade creme', 13, 5.49, 'testeimagem.png'),
    (RANDOM_UUID(), 'Cafe Lungo', 'Expresso longo, mais suave que o ristretto', 6, 4.99, 'testeimagem.png'),
    (RANDOM_UUID(), 'Cafe Flat White', 'Cafe com leite vaporizado, similar ao latte', 4, 5.49, 'testeimagem.png'),
    (RANDOM_UUID(), 'Cafe Caramel Macchiato', 'Macchiato com caramelo', 3, 6.49, 'testeimagem.png'),
    (RANDOM_UUID(), 'Cafe Marocchino', 'Expresso com cacau em po e espuma de leite', 17, 5.99, 'testeimagem.png'),
    (RANDOM_UUID(), 'Cafe Bombon', 'Cafe expresso com leite condensado', 21, 5.99, 'testeimagem.png'),
    (RANDOM_UUID(), 'Cafe Turco', 'Cafe turco forte e espesso', 25, 4.99, 'testeimagem.png');

INSERT INTO funcionarios (ID, NOME, EMAIL, SENHA, ENTRADA, SAIDA, TIPO_FUNCIONARIO)
VALUES
    (RANDOM_UUID(), 'Joao Silva', 'joao.silva@cafeteria.com', 'senha123', PARSEDATETIME('08:00:00', 'HH:mm:ss'), PARSEDATETIME('16:00:00', 'HH:mm:ss'), 0),
    (RANDOM_UUID(), 'Maria Santos', 'maria.santos@cafeteria.com', 'senha123', PARSEDATETIME('09:00:00', 'HH:mm:ss'), PARSEDATETIME('17:00:00', 'HH:mm:ss'), 0),
    (RANDOM_UUID(), 'Carlos Oliveira', 'carlos.oliveira@cafeteria.com', 'senha123', PARSEDATETIME('10:00:00', 'HH:mm:ss'), PARSEDATETIME('18:00:00', 'HH:mm:ss'), 0),
    (RANDOM_UUID(), 'Ana Costa', 'ana.costa@cafeteria.com', 'senha123', PARSEDATETIME('11:00:00', 'HH:mm:ss'), PARSEDATETIME('19:00:00', 'HH:mm:ss'), 1);

INSERT INTO ESTOQUE (ID, NOME, QUANTIDADE, ID_PRODUTO)
VALUES
    (RANDOM_UUID(), 'Cafe em po', 2, NULL),
    (RANDOM_UUID(), 'Teste', 5, NULL),
    (RANDOM_UUID(), 'Leite', 10, NULL),
    (RANDOM_UUID(), 'Acucar', 15, NULL),
    (RANDOM_UUID(), 'Chocolate em po', 20, NULL),
    (RANDOM_UUID(), 'Canela em po', 25, NULL),
    (RANDOM_UUID(), 'Nata', 30, NULL),
    (RANDOM_UUID(), 'Baunilha', 35, NULL),
    (RANDOM_UUID(), 'Creme de leite', 40, NULL),
    (RANDOM_UUID(), 'Leite condensado', 45, NULL),
    (RANDOM_UUID(), 'Cafe soluvel', 50, NULL),
    (RANDOM_UUID(), 'Cafe Arabica', 55, NULL),
    (RANDOM_UUID(), 'Cafe Robusta', 60, NULL),
    (RANDOM_UUID(), 'Cafe Descafeinado', 65, NULL),
    (RANDOM_UUID(), 'Xarope de Caramelo', 70, NULL),
    (RANDOM_UUID(), 'Xarope de Baunilha', 75, NULL),
    (RANDOM_UUID(), 'Xarope de Chocolate', 80, NULL),
    (RANDOM_UUID(), 'Xarope de Amendoa', 85, NULL),
    (RANDOM_UUID(), 'Cacau em po', 90, NULL),
    (RANDOM_UUID(), 'Chantilly', 95, NULL),
    (RANDOM_UUID(), 'Creme de avela', 65, NULL);


INSERT INTO cliente (id, cpf, email, endereco, nome, senha, telefone)
VALUES
(UUID(), '12345678909', 'clienteteste@gmail.com', 'endereco teste', 'clienteTeste', '12345678', '12345678');
