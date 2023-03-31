-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-1');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-2');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-3');
INSERT INTO product(id, name, description) VALUES (1, 'Tarjeta de Credito', 'TDC');
INSERT INTO product(id, name, description) VALUES (2, 'Tarjeta de Debito', 'TDD');
INSERT INTO product(id, name, description) VALUES (3, 'Credito Auto', 'CA');
INSERT INTO product(id, name, description) VALUES (4, 'Credito Hipotecario', 'CH');
INSERT INTO product(id, name, description) VALUES (5, 'Credito Inmediato', 'CI');