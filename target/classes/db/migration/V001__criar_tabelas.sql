CREATE TABLE auth (
  id SERIAL PRIMARY KEY,
  email VARCHAR NOT NULL,
  senha VARCHAR NOT NULL,
  role VARCHAR NOT NULL
);

CREATE TABLE admin (
  id SERIAL PRIMARY KEY,
  nome VARCHAR NOT NULL,
  cpf VARCHAR NOT NULL,
  id_auth INTEGER NOT NULL,
  FOREIGN KEY (id_auth) REFERENCES auth(id)
);

CREATE TABLE servico (
  id SERIAL PRIMARY KEY,
  nome VARCHAR NOT NULL,
  descricao VARCHAR,
  preco DECIMAL NOT NULL
);

CREATE TABLE tipo_produto (
  id SERIAL PRIMARY KEY,
  nome VARCHAR NOT NULL,
  descricao VARCHAR
);

CREATE TABLE produto (
  id SERIAL PRIMARY KEY,
  nome VARCHAR NOT NULL,
  preco DECIMAL NOT NULL,
  qtde_estoque INTEGER NOT NULL,
  qtde_minima INTEGER NOT NULL,
  id_tipo INTEGER NOT NULL,
  FOREIGN KEY (id_tipo) REFERENCES tipo_produto(id)
);

CREATE TABLE carrinho (
  id SERIAL PRIMARY KEY
);

CREATE TABLE endereco (
  id SERIAL PRIMARY KEY,
  rua VARCHAR NOT NULL,
  numero VARCHAR NOT NULL,
  bairro VARCHAR NOT NULL,
  cep VARCHAR NOT NULL,
  cidade VARCHAR NOT NULL,
  estado VARCHAR NOT NULL,
  complemento VARCHAR
);

CREATE TABLE cliente (
  id SERIAL PRIMARY KEY,
  nome VARCHAR NOT NULL,
  cpf VARCHAR NOT NULL,
  telefone VARCHAR NOT NULL,
  email VARCHAR NOT NULL,
  id_endereco INTEGER NOT NULL,
  id_carrinho INTEGER NOT NULL,
  id_auth INTEGER NOT NULL,
  FOREIGN KEY (id_endereco) REFERENCES endereco(id),
  FOREIGN KEY (id_carrinho) REFERENCES carrinho(id),
  FOREIGN KEY (id_auth) REFERENCES auth(id)
);

CREATE TABLE animal (
  id SERIAL PRIMARY KEY,
  nome VARCHAR NOT NULL,
  raca VARCHAR NOT NULL,
  tipo VARCHAR NOT NULL,
  id_cliente INTEGER NOT NULL,
  FOREIGN KEY (id_cliente) REFERENCES cliente(id)
);

CREATE TABLE agendamento (
  id SERIAL PRIMARY KEY,
  data_hora TIMESTAMP NOT NULL,
  id_cliente INTEGER NOT NULL,
  FOREIGN KEY (id_cliente) REFERENCES cliente(id)
);

CREATE TABLE agendamento_servico (
  id SERIAL PRIMARY KEY,
  id_servico INTEGER NOT NULL,
  id_agendamento INTEGER NOT NULL,
  FOREIGN KEY (id_servico) REFERENCES servico(id),
  FOREIGN KEY (id_agendamento) REFERENCES agendamento(id)
);

CREATE TABLE pedido (
  id SERIAL PRIMARY KEY,
  id_cliente INTEGER NOT NULL,
  data_hora TIMESTAMP NOT NULL,
  FOREIGN KEY (id_cliente) REFERENCES cliente(id)
);

CREATE TABLE produto_pedido (
  id SERIAL PRIMARY KEY,
  qtde INTEGER NOT NULL,
  id_produto INTEGER NOT NULL,
  id_pedido INTEGER NOT NULL,
  FOREIGN KEY (id_produto) REFERENCES produto(id),
  FOREIGN KEY (id_pedido) REFERENCES pedido(id)
);

CREATE TABLE produto_carrinho (
  id SERIAL PRIMARY KEY,
  qtde INTEGER NOT NULL,
  id_produto INTEGER NOT NULL,
  id_carrinho INTEGER NOT NULL,
  FOREIGN KEY (id_produto) REFERENCES produto(id),
  FOREIGN KEY (id_carrinho) REFERENCES carrinho(id)
);

CREATE TABLE horario (
  id SERIAL PRIMARY KEY,
  horario TIME NOT NULL
);