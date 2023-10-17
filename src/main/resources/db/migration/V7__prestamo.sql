CREATE TABLE prestamo(
    id_prestamo BIGINT AUTO_INCREMENT,
    id_libro BIGINT NOT NULL,
    id_lector BIGINT NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_vencimiento DATE NOT NULL,
    prestamo_activo TINYINT(1),
    PRIMARY KEY(id_prestamo),
    FOREIGN KEY(id_libro) REFERENCES libro(id_libro),
    FOREIGN KEY(id_lector) REFERENCES lector(id_lector)
    );