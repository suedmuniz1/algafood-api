insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Indiana');
insert into cozinha (nome) values ('Brasileira');

insert into estado (nome) values ('Minas Gerais');
insert into estado (nome) values ('São Paulo');
insert into estado (nome) values ('Ceará');

insert into cidade (nome, estado_id) values ('Belo Horizonte', 1);
insert into cidade (nome, estado_id) values ('São Paulo', 2);
insert into cidade (nome, estado_id) values ('Juazeiro do Norte', 3);
insert into cidade (nome, estado_id) values ('Crato', 3);

insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, endereco_cidade_id) values ("Thai gourmet", 10, 1, utc_timestamp, utc_timestamp, "38400-999", "Rua João Pinheiro", 1000, "Centro", 1);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ("Thai delivery", 9.50, 1, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ("Tuk Tuk comida indiana", 15, 2, utc_timestamp, utc_timestamp);

insert into forma_pagamento (descricao) values ('Crédito');
insert into forma_pagamento (descricao) values ('Débito');
insert into forma_pagamento (descricao) values ('Dinheiro');

insert into permissao (nome, descricao) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (nome, descricao) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');


insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Porco com molho agridoce", "Deliciosa carne suiána ao molho especial", 78.90, true, 1);