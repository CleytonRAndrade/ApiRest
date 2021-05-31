create table contato (

	id        bigint       not null auto_increment,
    pessoa_id bigint       not null,
	nome      varchar(60)  not null,
	telefone  varchar(20)  not null,
    email     varchar(255) not null,
	
	
	primary  key (id)
);

alter table contato add constraint fk_contato_pessoa
foreign key (pessoa_id) references pessoa (id);