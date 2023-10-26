CREATE TABLE usuario(
    id_usuario BIGINT AUTO_INCREMENT,
    alias VARCHAR(40) NOT NULL,
    contraseña VARCHAR(400) NOT NULL,
    id_rol BIGINT NOT NULL,
    PRIMARY KEY(id_usuario),
    FOREIGN KEY(id_rol) REFERENCES rol(id_rol)
);