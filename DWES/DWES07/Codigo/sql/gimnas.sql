DROP DATABASE IF EXISTS gimnas;
CREATE DATABASE gimnas CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE gimnas;
DROP TABLE IF EXISTS clients;
CREATE TABLE clients (
  id int(11) AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(15) NOT NULL,
  nom VARCHAR(30) NOT NULL,
  llinatges VARCHAR(50) NOT NULL,
  email varchar(100) NOT NULL,
  telefon varchar(12) NOT NULL,
  password char(120) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
INSERT INTO clients
VALUES
  (1, 'amartinez', 'Angel', 'Martinez', 'amartinez@example.com', '666777888', 'pbkdf2:sha256:260000$FmeGDLSwcd69n5QI$6df8f7701a2daa6a0bad1119a3832b585f0ec9d658bb429552e3694a63d3dc25'),
  (2, 'sikari', 'Shinji', 'Ikari', 'sikari@example.com', '666222888', 'pbkdf2:sha256:260000$10eZ4touJOL9A0Tl$001ca267fd695e4edaaf4412db0dfe70f4a05b323694045d2c30335614d992c7'),
  (3, 'alangley', 'Asuka', 'Langley', 'alangley@example.com', '666333888', 'pbkdf2:sha256:260000$A9FpUwmSPxeZ552p$7037ca1fbbacb6bbb562984a542581c8ce8b641fc21de2fe120fa5b91ea4e2e1'),
  (4, 'hkensjin', 'Himura', 'Kenshin', 'hkensjin@example.com', '666444888', 'pbkdf2 :sha256 :260000$FmeGDLSwcd69n5QI$6df8f7701a2daa6a0bad1119a3832b585f0ec9d658bb429552e3694a63d3dc25'),
  (5, 'SaulGman', 'pepe', 'Goodman', '', '999777555', 'pbkdf2:sha256:260000$92p0bfnAB1JzNLzc$070f91a33e5c30b6f2c2870b6f3ceba2065603577fdb46152994a9a3061ba226');

DROP TABLE IF EXISTS clients;
CREATE TABLE clients (
  id int(11) AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(15) NOT NULL,
  nom VARCHAR(30) NOT NULL,
  llinatges VARCHAR(50) NOT NULL,
  email varchar(100) NOT NULL,
  telefon varchar(12) NOT NULL,
  password char(120) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
INSERT INTO clients
VALUES
  (1, 'amartinez', 'Angel', 'Martinez', 'amartinez@example.com', '666777888', 'pbkdf2:sha256:260000$FmeGDLSwcd69n5QI$6df8f7701a2daa6a0bad1119a3832b585f0ec9d658bb429552e3694a63d3dc25'),
  (2, 'sikari', 'Shinji', 'Ikari', 'sikari@example.com', '666222888', 'pbkdf2:sha256:260000$10eZ4touJOL9A0Tl$001ca267fd695e4edaaf4412db0dfe70f4a05b323694045d2c30335614d992c7'),
  (3, 'alangley', 'Asuka', 'Langley', 'alangley@example.com', '666333888', 'pbkdf2:sha256:260000$A9FpUwmSPxeZ552p$7037ca1fbbacb6bbb562984a542581c8ce8b641fc21de2fe120fa5b91ea4e2e1'),
  (4, 'hkensjin', 'Himura', 'Kenshin', 'hkensjin@example.com', '666444888', 'pbkdf2 :sha256 :260000$FmeGDLSwcd69n5QI$6df8f7701a2daa6a0bad1119a3832b585f0ec9d658bb429552e3694a63d3dc25'),
  (5, 'SaulGman', 'pepe', 'Goodman', '', '999777555', 'pbkdf2:sha256:260000$92p0bfnAB1JzNLzc$070f91a33e5c30b6f2c2870b6f3ceba2065603577fdb46152994a9a3061ba226');


DROP TABLE IF EXISTS pistes;
CREATE TABLE pistes (
    idpista int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    tipo enum('Coberta', 'Exterior') NOT NULL,
    preu int(3) NOT NULL
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8;
INSERT INTO pistes (idpista, tipo, preu)
VALUES
  (1, 'Coberta', 12),
  (2, 'Exterior', 8);
DROP TABLE IF EXISTS reserves;
CREATE TABLE reserves (
    data datetime NOT NULL,
    idpista int(11) NOT NULL,
    idclient int(11) NOT NULL,
    PRIMARY KEY (data, idpista),
    KEY r_idclient (idclient),
    KEY r_idpista (idpista),
    CONSTRAINT reserves_ibfk_1 FOREIGN KEY (idclient) REFERENCES clients (id),
    CONSTRAINT reserves_ibfk_2 FOREIGN KEY (idpista) REFERENCES pistes (idpista)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8;
INSERT INTO reserves (data, idpista, iduser)
VALUES
  ('2022-02-03 16:00:00', 2, 1),
  ('2022-02-02 15:00:00', 1, 1),
  ('2022-01-20 18:00:00', 2, 2),
  ('2022-01-10 19:00:00', 1, 2),
  ('2022-02-02 18:00:00', 1, 3),
  ('2022-02-21 16:00:00', 2, 1),
  ('2022-02-24 15:00:00', 2, 1),
  ('2022-02-25 18:00:00', 2, 2),
  ('2022-02-25 19:00:00', 1, 3),
  ('2022-02-23 18:00:00', 1, 3);