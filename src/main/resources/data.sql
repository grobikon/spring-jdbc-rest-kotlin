create table car_brand
(
    id  serial constraint car_brand_pk primary key,
    title varchar(100) not null
);

alter table car_brand
    owner to postgres;

create table car
(
    id  serial constraint car_pk primary key,
    title varchar(100) not null,
    passenger_count integer,
    brand_id integer constraint car_brand_fk references car_brand (id)
);

alter table car
    owner to postgres;


