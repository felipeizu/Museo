use master
go
drop database museudigital
----------Query Estrutura das Tabelas--------------
create database museudigital
go
use museudigital

create table autor(
id int not null identity,
nome varchar(100) not null,
datanasc date not null,
datafale date,
localmorte varchar(100),
biografia varchar (max),
primary key(id)
)


--primary key(cnpj), --verificar
primary key(id)
)


create table obra(
id int not null identity,
titulo varchar(30),
ano decimal (4),
categoria varchar(50),
descricao varchar(max),
imagem varchar(max),
dimensao varchar(15),
primary key(id)
)


create table obraautor(
idobra int not null,
idautor int not null,
foreign key (idobra) references obra(id),
foreign key (idautor) references autor(id)
)


--------------Inserts de Teste--------------------
INSERT INTO autor VALUES('Leonardo da Vinci', '15/04/1452', '02/05/1519', 'Amboise, França', 'muito gato, pegou varias mina, parecia hetero mas n era n, quase dicaprio');
insert into autor values('Michelangelo', '06/03/1475', '18/02/1564', 'roma, italia' , 'cheirava tudo, parecia o vocalista do charlie brown jr. ');
select * from autor

INSERT INTO obra VALUES ('David', '1504', 
'escultura em marmore de Carrara', 'David ou Davi é uma das esculturas mais famosas do artista renascentista Michelangelo. 
O trabalho retrata o herói bíblico com realismo anatômico impressionante, sendo considerada uma das mais importantes obras do Renascimento.', 
'David.JPG', '5,17m');
INSERT INTO obra VALUES ('Mona lisa', '1517','pintura', 'Mona Lisa também conhecida como 
A Gioconda ou ainda Mona Lisa del Giocondo é a mais notável e conhecida obra de Leonardo da Vinci, um dos mais eminentes homens do Renascimento italiano.', 
'Monalisa.JPG', '77 x 53 cm');

insert into obraautor values (1,1),(2,2)

select * from obra
select * from autor

-------------------------
Pesquisa de obras a partir de autor
--------------------------
create view v_obraautor
as
select ob.id, ob.titulo
from obra ob
inner join autor aut
on ob.idautor = aut.id
where aut.id = 1
group by ob.id, ob.titulo
-------------------------
Pesquisa de obras a partir do titulo
-------------------------
select * from obra where titulo like %''%
-------------------------
select id, nome, CONVERT(CHAR(10),datanasc,103) AS datanasc, CONVERT(CHAR(10),datafale,103) AS datafale, localmorte FROM autor


-- views que deveremos nos basear para montar:

-------------------------
SELECT ob.id as idobra, aut.id as idautor
from obra ob
inner join obraautor oa
on ob.id = oa.idobra
inner join autor aut
on aut.id = oa.idautor
order by ob.id


--VIEW RESPONSAVEL PELA PESQUISA DE OBRA ATRAVEZ DE UM AUTOR
create view v_pesquisaPorAutor
as
	select aut.nome, ob.id, ob.titulo, ob.ano, ob.descricao, ob.imagem
	from obra ob 
	inner join obraautor oa
	on ob.id = oa.idautor
	inner join autor aut
	on aut.id = oa.idautor
	group by ob.id, ob.titulo, ob.ano, ob.dimensao, ob.imagem, aut.nome, ob.descrica

select * from v_pesquisaPorAutor where nome like '%%'
drop view v_pesquisaPorAutor
