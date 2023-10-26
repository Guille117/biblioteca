CREATE TABLE prestamo_libro(
    id_prestamo_libro BIGINT PRIMARY KEY AUTO_INCREMENT,
    id_libro BIGINT,
    id_prestamo BIGINT,
    FOREIGN KEY(id_libro) REFERENCES libro(id_libro),
    FOREIGN KEY(id_prestamo) REFERENCES prestamo(id_prestamo)
);