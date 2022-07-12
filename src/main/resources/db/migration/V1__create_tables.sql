CREATE TABLE item
(
    id           serial  not null,
    item_name    varchar not null,
    sku          varchar not null,
    category_id  numeric not null,
    price        numeric not null,
    item_type_id numeric not null,
    primary key (id)
);

create table item_category
(
    id            serial  not null,
    category_name varchar not null,
    primary key (id)
);

create table item_type
(
    id        serial  not null,
    type_name varchar not null,
    primary key (id)
);

create table payment_method
(
    id                  serial  not null,
    description         varchar not null,
    payment_category_id numeric not null,
    primary key (id)
);

create table payment_category
(
    id            serial  not null,
    category_name varchar not null,
    primary key (id)
);

create table payment
(
    id                serial  not null,
    payment_method_id numeric not null,
    amount            numeric not null,
    primary key (id)
);

insert into category (name)
values ('Food');
insert into category (name)
values ('Furniture');
insert into category (name)
values ('Delivery');

insert into item_type (name)
values ('Product');
insert into item_type (name)
values ('Service');