DROP DATABASE IF EXISTS gimnas;
CREATE DATABASE gimnas CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE gimnas;
DROP TABLE IF EXISTS clients;
CREATE TABLE clients (
  idclient int AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(15) NOT NULL,
  nom VARCHAR(30) NOT NULL,
  llinatges VARCHAR(50) NOT NULL,
  email varchar(100) NOT NULL,
  telefon varchar(12) NOT NULL,
  password char(102) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
INSERT INTO
  clients
VALUES
  (
    1,
    'amartinez',
    'Angel',
    'Martinez',
    'amartinez@example.com',
    '666777888',
    'pbkdf2:sha256:260000$FmeGDLSwcd69n5QI$6df8f7701a2daa6a0bad1119a3832b585f0ec9d658bb429552e3694a63d3dc25'
  ),
  (
    2,
    'sikari',
    'Shinji',
    'Ikari',
    'sikari@example.com',
    '666222888',
    'pbkdf2:sha256:260000$10eZ4touJOL9A0Tl$001ca267fd695e4edaaf4412db0dfe70f4a05b323694045d2c30335614d992c7'
  ),
  (
    3,
    'alangley',
    'Asuka',
    'Langley',
    'alangley@example.com',
    '666333888',
    'pbkdf2:sha256:260000$A9FpUwmSPxeZ552p$7037ca1fbbacb6bbb562984a542581c8ce8b641fc21de2fe120fa5b91ea4e2e1'
  );
DROP TABLE IF EXISTS pistes;
CREATE TABLE pistes (
    idpista int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    tipo enum('Coberta', 'Exterior') NOT NULL,
    preu int(3) NOT NULL
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8;
INSERT INTO
  pistes (idpista, tipo, preu)
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
    CONSTRAINT reserves_ibfk_1 FOREIGN KEY (idclient) REFERENCES clients (idclient),
    CONSTRAINT reserves_ibfk_2 FOREIGN KEY (idpista) REFERENCES pistes (idpista)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8;
INSERT INTO
  reserves (data, idpista, idclient)
VALUES
  ('2022-02-03 16:00:00', 2, 1),
  ('2022-02-02 15:00:00', 1, 1),
  ('2022-01-20 18:00:00', 2, 2),
  ('2022-01-10 19:00:00', 1, 2),
  ('2022-02-02 18:00:00', 1, 3);