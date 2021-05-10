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
        return 'Empate';
    END IF;
    END;
END;

CREATE TABLE PARTITSMartinez OF partit;

SELECT g.local, g.visitant, g.golslocal, g.golsVisitant, g.guanyador() FROM partitsmartinez g;

SELECT DISTINCT * FROM partitsmartinez GROUP BY local, visitant; 

SELECT local AS equipo FROM partitsmartinez UNION SELECT visitant AS equipo FROM partitsmartinez;