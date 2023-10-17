CREATE TABLE lector(
    id_lector BIGINT AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    id_institucion BIGINT,
    num_telefono VARCHAR(8) NOT NULL,
    PRIMARY KEY(id_lector),
    FOREIGN KEY(id_institucion) REFERENCES institucion(id_institucion)
);