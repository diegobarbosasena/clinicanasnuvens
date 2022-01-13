create table patient
(
    id           bigint primary key auto_increment,
    name         varchar(100) not null,
    sex          char(1),
    birth_date   date,
    phone_number varchar(11),
    cpf          varchar(11)
);
