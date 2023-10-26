CREATE TABLE institucion(
    id_institucion BIGINT AUTO_INCREMENT,
    nombre VARCHAR(70) NOT NULL UNIQUE,
    num_calle INT NOT NULL,
    num_avenida INT NOT NULL,
    PRIMARY KEY(id_institucion)
);