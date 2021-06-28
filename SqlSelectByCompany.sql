-- create a table
CREATE TABLE company(
                        id INTEGER not null,
                        name character varying,
                        constraint company_pkey primary key (id)
);
-- create a table
CREATE TABLE person (
                        id INTEGER not null,
                        name character varying,
                        company_id INTEGER references company(id),
                        constraint person_pkey primary key (id)
);
-- insert some values
insert into company(id, name) values (1, 'BMW');
insert into company(id, name) values (2, 'Porsche');
insert into company(id, name) values (3, 'Audi');
insert into company(id, name) values (4, 'Lada');
insert into company(id, name) values (5, 'Toyota');
insert into company(id, name) values (6, 'Honda');

insert into person(id, name, company_id) values (1, 'Ivan', 1);
insert into person(id, name, company_id) values (2, 'Max', 1);
insert into person(id, name, company_id) values (3, 'Fedor', 1);
insert into person(id, name, company_id) values (4, 'Petr', 1);
insert into person(id, name, company_id) values (5, 'Nick', 2);
insert into person(id, name, company_id) values (6, 'Alex', 3);
insert into person(id, name, company_id) values (7, 'Ben', 3);
insert into person(id, name, company_id) values (8, 'John', 3);
insert into person(id, name, company_id) values (9, 'Stepan', 4);
insert into person(id, name, company_id) values (10, 'Andrew', 4);
insert into person(id, name, company_id) values (11, 'Dmitry', 4);
insert into person(id, name, company_id) values (12, 'Sasuke', 5);
insert into person(id, name, company_id) values (13, 'Naruto', 5);
insert into person(id, name, company_id) values (14, 'Obito', 6);
insert into person(id, name, company_id) values (15, 'Satoshi', 6);
insert into person(id, name, company_id) values (16, 'Hanzo', 6);
insert into person(id, name, company_id) values (17, 'Hanza', 5);
-- имена всех person, которые не состоят в компании с id = 15
select p.name as name_person, c.name as name_company
from person p
         join company c on c.id = p.company_id
where p.company_id != 5;

-- максимальное количество человек в компании
select c.name, count(p.name)
from person p
         join company c on c.id = p.company_id
group by c.name
order by count(p.name) desc
    limit 1;