create table rabbit(
id serial primary key,
create_date int
);
select * from rabbit;
ALTER TABLE rabbit ALTER COLUMN create_date TYPE bigint;