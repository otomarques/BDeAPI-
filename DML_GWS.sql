use db_gws;

insert into tb_clientes values (
	UUID_TO_BIN(UUID()),
    "Senai",
    "Anderson",
    11999999999,
    "email@email.com");
    
insert into tb_usuarios values (
	UUID_TO_BIN(UUID()),
    "lucas",
    "Gestor",
    "leandro",
    "11999999999",
    "leandro@email.email",
    "255255",
    now(),
    now(),
    "github,phyton e php",
    "Rio de Janeiro",
    "RJ",
    now()
);

insert into tb_usuarios values (
	UUID_TO_BIN(UUID()),
    "jeferson",
    "Gestor",
    "hugo",
    "11999999999",
    "leandro@email.email",
    "255255",
    '2022-10-11 04:34:29',
    '2022-10-11 04:34:29',
    "github,phyton e php",
    "São paulo",
    "SP",
    '2022-10-11 04:34:29'
);

insert into tb_demandas values(
	UUID_TO_BIN(UUID()),
    UUID_TO_BIN("c809256d-7062-11ee-aa82-0c9d928e5d1a"),
    "Registro de maquinas",
    "C://Logos//XXXX_XXXX_XXXX",
    "Neste serviço é obrigatorio fazer o registro das maquinas e se possivel fazer a manutenção.",
    "C://Anexos//XXXX_XXXX_XXXX",
	"M",
    "P",
    "Em andamento",
    '2022-10-11 04:34:29',
    '2022-10-11 04:34:50',
    25000
); 

insert into tb_hardskills values 
(UUID_TO_BIN(UUID()),"phyton"),
(UUID_TO_BIN(UUID()),"css"),
(UUID_TO_BIN(UUID()),"html"),
(UUID_TO_BIN(UUID()),"java"),
(UUID_TO_BIN(UUID()),"javascript"),
(UUID_TO_BIN(UUID()),"c++");

insert into tb_softskills values 
(UUID_TO_BIN(UUID()),"extrovertido"),
(UUID_TO_BIN(UUID()),"fala em publico"),
(UUID_TO_BIN(UUID()),"boas ideias"),
(UUID_TO_BIN(UUID()),"empenhado"),
(UUID_TO_BIN(UUID()),"sempre com time"),
(UUID_TO_BIN(UUID()),"sempre presente");

insert into tb_segmentos values(
	UUID_TO_BIN(UUID()),
    "maquinario"
);

insert into tb_segmentos_demandas values(
	UUID_TO_BIN('7e885586-7079-11ee-aa82-0c9d928e5d1a'),
    UUID_TO_BIN('19a24d40-7079-11ee-aa82-0c9d928e5d1a')
);

insert into tb_usuarios_demandas values(
	UUID_TO_BIN('08b0f5d7-706b-11ee-aa82-0c9d928e5d1a'),
    UUID_TO_BIN('19a24d40-7079-11ee-aa82-0c9d928e5d1a')
);



