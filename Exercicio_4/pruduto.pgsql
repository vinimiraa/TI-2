CREATE TABLE produto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50),
    preco FLOAT(1),
    quantidade INT
);

INSERT INTO produto (nome, preco, quantidade) VALUES ( 'Cadeira', 200, 10 );
INSERT INTO produto (nome, preco, quantidade) VALUES ( 'Mesa', 450, 5 );

SELECT * FROM produto;