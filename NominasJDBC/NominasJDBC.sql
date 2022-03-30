DROP TABLE NOMINAS;
DROP TABLE EMPLEADOS;

CREATE TABLE EMPLEADOS (
    nombre varchar(50),
    dni varchar(9),
    sexo char,
    categoria number,
    anyos number,
    CONSTRAINT pk_empleados PRIMARY KEY (dni)
);

CREATE TABLE NOMINAS (
    dni varchar(9),
    sueldo number,
    CONSTRAINT pk_nominas PRIMARY KEY (dni),
    CONSTRAINT fk_nominas FOREIGN KEY (dni) REFERENCES empleados (dni)
);

INSERT INTO EMPLEADOS VALUES('James Cosling', '32000032G', 'M', 9, 7);
INSERT INTO EMPLEADOS VALUES('Ada Lovelace', '32000031R', 'F', 1, 2);

INSERT INTO NOMINAS VALUES('32000032G', 245000);
INSERT INTO NOMINAS VALUES('32000031R', 60000);

-- update empleados set anyos='18' where dni='11112223Q';