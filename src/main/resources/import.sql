insert into estado (nome, sigla) values( 'Tocantins', 'TO');
insert into estado (nome, sigla) values( 'Goiás', 'GO');
insert into estado (nome, sigla) values( 'São Paulo', 'SP');
insert into estado (nome, sigla) values( 'Rio de Janeiro', 'RJ');
insert into estado (nome, sigla) values( 'Pará', 'PA');

insert into cidade (nome, id_estado) values( 'Palmas', 1);
insert into cidade (nome, id_estado) values( 'Paraíso', 1);
insert into cidade (nome, id_estado) values( 'Gurupi', 1);
insert into cidade (nome, id_estado) values( 'Goiânia', 2);
insert into cidade (nome, id_estado) values( 'Anápolis', 2);

-- senha 111
insert into usuario (username, senha) values ('emily', '1BTSU7LatsY6BK9FYiXT9du++eWLisRMBwGyxbRSh0ZpH0p3jIIAWh1+Ta/zBagkeJa/NwPo5XngKb/REUH74g==');
insert into usuario (username, senha) values ('luis', '222');
insert into usuario (username, senha) values ('david', '333');

insert into pessoa (cpf, nome, datanascimento, id_usuario) values ('111.111.111-11', 'Emily bessa', '1980-08-26', 1);
insert into pessoa (cpf, nome, datanascimento, id_usuario) values ('222.222.222-22', 'Luis', '1985-08-12', 2);
insert into pessoa (cpf, nome, datanascimento, id_usuario) values ('333.333.333-33', 'David', '1982-06-11', 3);
insert into pessoa (cpf, nome, datanascimento, id_usuario) values ('444.444.444-44', 'Fredson', '1982-06-11', null);

insert into cliente (id_pessoa, id_cidade) values (1, 4);
insert into cliente (id_pessoa, id_cidade) values (2, 1);
insert into cliente (id_pessoa, id_cidade) values (4, 2);

insert into gravadora (nome) values ('Sony Music');
insert into gravadora (nome) values ('Universal Music Group');
insert into gravadora (nome) values ('Republic Records');
insert into gravadora (nome) values ('Independente/Desconhecida');

insert into artista (nome, descricao) values ('Banda Calypso', 'Banda Calypso foi uma banda brasileira de calypso, com influências de ritmos regionais do estado de origem. O conjunto foi formado em Belém no estado do Pará, em 10 de junho de 1999, pela cantora e dançarina Joelma Mendes e pelo guitarrista e produtor musical, Cledivan Almeida Farias, mais conhecido como Ximbinha.');
insert into artista (nome, descricao) values ('Banda Kassikó', 'Banda de Recife (PE), formada em 2000, influenciada por estilos como Calypso, Melody e Forró, além do ritmo caribenho Kasseko, que deu origem ao nome do grupo.');
insert into artista (nome, descricao) values ('Anitta', 'Larissa de Macedo Machado, mais conhecida pelo seu nome artístico Anitta, é uma cantora, compositora, atriz, dançarina e empresária brasileira.');

insert into genero (nome) values ('Pop');
INSERT into genero (nome) values ('Rock');
Insert into genero (nome) values ('Calypso');
insert into genero (nome) values ('MPB');

insert into compositor (nome) values ('Joelma Mendes');
insert into compositor (nome) values ('Ximbinha');
insert into compositor (nome) values ('Anitta');

insert into classificacaoetaria (descricao) values ('Livre');
insert into classificacaoetaria (descricao) values ('10 anos');
insert into classificacaoetaria (descricao) values ('12 anos');

