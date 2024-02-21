insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Indiana');
insert into cozinha (nome) values ('Brasileira');

insert into restaurante (nome, taxa_frete, cozinha_id) values ("Thai gourmet", "10", 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ("Thai delivery", "9.50", 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ("Tuk Tuk comida indiana", "15", 2);

insert into forma_pagamento (descricao) values ('Crédito');
insert into forma_pagamento (descricao) values ('Débito');
insert into forma_pagamento (descricao) values ('Dinheiro');

insert into permissao (nome, descricao) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (nome, descricao) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into estado (nome) values ('Minas Gerais');
insert into estado (nome) values ('São Paulo');
insert into estado (nome) values ('Ceará');

insert into cidade (nome, estado_id) values ('Belo Horizonte', 1);
insert into cidade (nome, estado_id) values ('São Paulo', 2);
insert into cidade (nome, estado_id) values ('Juazeiro do Norte', 3);
insert into cidade (nome, estado_id) values ('Crato', 3);