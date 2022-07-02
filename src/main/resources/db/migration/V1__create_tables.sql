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

insert into category values (1, 'Food');
insert into category values (2, 'Furniture');
insert into category values (3, 'Delivery');

insert into item_type values (1, 'Product');
insert into item_type values (2, 'Service');