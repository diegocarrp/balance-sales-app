CREATE TABLE item(
    id serial not null,
    name varchar not null,
    sku varchar not null,
    category_id numeric not null,
    price numeric not null,
    item_type_id numeric not null,
    primary key (id)
);

create table category(
    id serial not null,
    name varchar not null,
    primary key (id)
)

create table item_type(
    id serial not null,
    name varchar not null,
    primary key (id)
)

insert into category (name) values ('Food');
insert into category (name) values ('Furniture');
insert into category (name) values ('Delivery');

insert into item_type (name) values ('Product');
insert into item_type (name) values ('Service');