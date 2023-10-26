CREATE TABLE categoria(
    id_categoria BIGINT AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    PRIMARY KEY(id_categoria)
);