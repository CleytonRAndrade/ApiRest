create table pessoa (

	id       bigint       not null auto_increment,
	nome     varchar(60)  not null,
	cpf    varchar(20) not null,
	data_nascimento datetime  not null,
	
	primary  key (id)
);