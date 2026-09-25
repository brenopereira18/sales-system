CREATE
DATABASE "systemProducts";

create table products
(
    id         serial         not null primary key,
    name       varchar(100)   not null,
    unit_value decimal(16, 2) not null
);

CREATE
DATABASE "systemClients";

create table clients
(
    id                serial       not null primary key,
    name              varchar(150) not null,
    cpf               char(11)     not null,
    street_address    varchar(100),
    house_number      varchar(10),
    neighborhood      varchar(100),
    email             varchar(150),
    cell_phone_number varchar(20)
);

CREATE
DATABASE "systemOrders";

create table orders
(
    id            serial         not null primary key,
    client_id     bigint         not null,
    order_date    timestamp      not null default now(),
    payment_key   text,
    observation   text,
    status        varchar(20) check (
        status in ('REALIZED', 'PAID', 'INVOICED', 'PAYMENT_ERROR', 'PREPARING_SHIPMENT')
        ),
    total         decimal(16, 2) not null,
    tracking_code varchar(255),
    url_nf        text
);

create table ordered_Item
(
    id         serial         not null primary key,
    order_id   bigint         not null references orders (id),
    product_id bigint         not null,
    quantity   int            not null,
    unit_value decimal(16, 2) not null
);