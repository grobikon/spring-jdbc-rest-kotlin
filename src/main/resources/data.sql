create table car
(
    id  serial constraint car_pk primary key,
    title varchar(100) not null,
    passenger_count integer
);

alter table car
    owner to postgres