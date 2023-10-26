CREATE DATABASE Lotus;
USE Lotus;
CREATE TABLE Usuario(
usuario_id int PRIMARY KEY AUTO_INCREMENT,
usuario_nome VARCHAR(30),
usuario_email VARCHAR(60),
usuario_senha VARCHAR(20)
);

CREATE UNIQUE INDEX idx_usuario_nome ON Usuario(usuario_nome);


CREATE TABLE Arquivos (
  arquivo_id INT PRIMARY KEY AUTO_INCREMENT,
  arquivo_nome VARCHAR(60),
  arquivo_caminho VARCHAR(200),
  arquivo_formato VARCHAR(10),
  usuario_nome VARCHAR(30), 
  FOREIGN KEY (usuario_nome) REFERENCES Usuario(usuario_nome)
);