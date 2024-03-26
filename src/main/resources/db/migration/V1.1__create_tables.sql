create table sale
(
    id           serial    not null,
    datetime     timestamp not null,
    cashier_id   numeric   not null,
    customer_id  numeric   not null,
    total_amount numeric   not null,
    primary key (id)
);

create table sale_item
(
    id       serial not null,
    sale_id  numeric not null,
    sku      varchar not null,
    quantity numeric not null,
    total    numeric not null,
    primary key (id)
);

create table item
(
    id               serial  not null,
    description      varchar not null,
    sku              varchar not null,
    price            numeric not null,
    item_category_id numeric not null,
    item_type_id     numeric not null,
    primary key (id)
);

create table item_category
(
    id          serial  not null,
    description varchar not null,
    primary key (id)
);

create table item_type
(
    id          serial  not null,
    description varchar not null,
    primary key (id)
);

create table payment_method
(
    id          serial  not null,
    description varchar not null,
    primary key (id)
);


create table payment
(
    id                serial  not null,
    payment_method_id numeric not null,
    amount            numeric not null,
    sale_id           numeric not null,
    primary key (id)
);
