CREATE TABLE pessoa(
id_pessoa serial not null,
nome_pessoa varchar(100) NOT NULL,
telefone_pessoa varchar(20),
celular_pessoa varchar(20) NOT NULL,
crm_pessoa varchar(50),
login_pessoa varchar(50) NOT NULL,
senha_pessoa varchar(50) NOT NULL,
tipo_pessoa char(1) NOT NULL,
CONSTRAINT pk_pessoa PRIMARY KEY (id_pessoa));		
		
insert into pessoa(nome_pessoa, celular_pessoa, login_pessoa, senha_pessoa, tipo_pessoa)
values('teste', '000', 'teste@gmail.com', '123', 'A');
		
CREATE TABLE consulta(
id serial NOT NULL,
horario varchar(10) NOT NULL,
data date NOT NULL NOT NULL,
id_pessoa integer not null,
CONSTRAINT pk_consulta PRIMARY KEY (id),
CONSTRAINT fk_consulta_pessoa FOREIGN KEY(id_pessoa) REFERENCES pessoa(id_pessoa));
					
CREATE TABLE cidade(
id serial not null,
nome varchar(50) not null,
CONSTRAINT pk_cidade PRIMARY KEY (id));