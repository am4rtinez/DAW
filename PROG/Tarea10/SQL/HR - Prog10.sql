CREATE OR REPLACE TYPE partit AS OBJECT (
local VARCHAR2(20),
visitant VARCHAR2(20),
golsLocal INTEGER,
golsVisitant INTEGER,
MEMBER FUNCTION guanyador RETURN VARCHAR2);

CREATE OR REPLACE TYPE BODY partit AS
    MEMBER FUNCTION guanyador RETURN VARCHAR2 AS
    return VARCHAR2(20);
    BEGIN
    IF (golsLocal > golsVisitant) THEN
        return local;
    ELSIF (golsVisitant > golsLocal) THEN
        return visitant;
    ELSE
        return 'empate';
    END IF;
    END;
END;

CREATE TABLE PARTITSMartinez OF partit;

SELECT g.local, g.visitant, g.golslocal, g.golsVisitant, g.guanyador() FROM partitsmartinez g;

INSERT INTO PARTITSMartinez VALUES ('R. Madrid','Getafe',1,0);
INSERT INTO PARTITSMartinez VALUES ('Barcelona','Villareal',2,0);
INSERT INTO PARTITSMartinez VALUES ('At. Madrid','Sevilla',1,1);
INSERT INTO PARTITSMartinez VALUES ('Betis','Valladolid',1,0);
