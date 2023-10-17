CREATE TABLE libro(
    id_libro BIGINT AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    id_autor BIGINT NOT NULL,
    id_categoria BIGINT NOT NULL,
    id_editorial BIGINT NOT NULL,
    añoPublicacion VARCHAR(4) NOT NULL,
    edicion INT(2) NOT NULL,
    cantidad INT NOT NULL,
    en_prestamo TINYINT(0),
    PRIMARY KEY(id_libro),
    FOREIGN KEY(id_autor) REFERENCES autor(id_autor),
    FOREIGN KEY(id_categoria) REFERENCES categoria(id_categoria),
    FOREIGN KEY(id_editorial) REFERENCES editorial(id_editorial)
);