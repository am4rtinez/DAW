DROP DATABASE IF EXISTS BDAngelMartinez;
CREATE DATABASE BDAngelMartinez;
USE BDAngelMartinez;
CREATE TABLE alumno (
    codiAlumne VARCHAR(5) PRIMARY KEY,
    nomTutor VARCHAR(40) NOT NULL,
    codiTutorAlumne INT NOT NULL,
    CONSTRAINT `codiTutor`
        FOREIGN KEY (codiTutorAlumne) REFERENCES tutor (codiTutor)
        ON DELETE CASCADE
        ON UPDATE RESTRICT
) ENGINE=InnoDB;
CREATE TABLE tutor (
    codiTutor INT PRIMARY KEY AUTO_INCREMENT,
    nomTutor VARCHAR(40) NOT NULL
) ENGINE=InnoDB;