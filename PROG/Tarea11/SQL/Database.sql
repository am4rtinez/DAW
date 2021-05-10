DROP DATABASE IF EXISTS BDAngelMartinez;

CREATE DATABASE BDAngelMartinez;

USE BDAngelMartinez;
CREATE TABLE tutors (
    codiTutor INT PRIMARY KEY,
    nomTutor VARCHAR(40) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE alumnes (
    codiAlumne VARCHAR(5) PRIMARY KEY,
    nomAlumne VARCHAR(40) NOT NULL,
    codiTutorAlumne INT NOT NULL,
    CONSTRAINT `fk_codi_tutor`
        FOREIGN KEY (codiTutorAlumne) REFERENCES tutors (codiTutor)
        ON DELETE CASCADE
        ON UPDATE RESTRICT
) ENGINE=InnoDB;

INSERT INTO tutors VALUES (111, 'Fernando Fernandez Guerrero');
INSERT INTO tutors VALUES (112, 'Sofia Lombardo Guerrero');
INSERT INTO tutors VALUES (144, 'Antonia Pajares Romuo');
INSERT INTO tutors VALUES (456, 'Noelia Jimenez Perez');
INSERT INTO tutors VALUES (457, 'Maria Rodriguez Pecos');
INSERT INTO tutors VALUES (123, 'Ildefonso Felipe Ruiz');
INSERT INTO tutors VALUES (124, 'Eustaquio Colmenero Romero');
INSERT INTO tutors VALUES (125, 'Andres Calamaro Pez');
INSERT INTO tutors VALUES (126, 'Julian Muñoz Gil');
INSERT INTO tutors VALUES (127, 'Ambrosio Macaroni Pero');

INSERT INTO alumnes VALUES ('AAA33','Alumno 1',111);
INSERT INTO alumnes VALUES ('AAA22','Alumno 2',111);
INSERT INTO alumnes VALUES ('AAA65','Alumno 3',112);
INSERT INTO alumnes VALUES ('AAA87','Alumno 4',112);
INSERT INTO alumnes VALUES ('BBB24','Alumno 5',144);
INSERT INTO alumnes VALUES ('BBB53','Alumno 6',144);
INSERT INTO alumnes VALUES ('BBB85','Alumno 7',456);
INSERT INTO alumnes VALUES ('CCC76','Alumno 8',456);
INSERT INTO alumnes VALUES ('CCC65','Alumno 9',457);
INSERT INTO alumnes VALUES ('CCC43','Alumno 10',457);
INSERT INTO alumnes VALUES ('CCC23','Alumno 11',123);
INSERT INTO alumnes VALUES ('DDD02','Alumno 12',123);
INSERT INTO alumnes VALUES ('DDD89','Alumno 13',124);
INSERT INTO alumnes VALUES ('DDD67','Alumno 14',124);
INSERT INTO alumnes VALUES ('EEE65','Alumno 15',125);
INSERT INTO alumnes VALUES ('EEE45','Alumno 16',125);
INSERT INTO alumnes VALUES ('EEE34','Alumno 17',126);
INSERT INTO alumnes VALUES ('FFF45','Alumno 18',126);
INSERT INTO alumnes VALUES ('FFF32','Alumno 19',127);
INSERT INTO alumnes VALUES ('FFF12','Alumno 20',127);