INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('jcguerrero21', '$2a$10$t7JyL6.NjwWHc8VBQACZtOe3GbGiR5rJxbltKUVx/twe.svGMCul6', 1, 'Juan Carlos', 'Guerrero', 'jguerrerom@correo.com');
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('admin', '$2a$10$tJT9zaWyrsYf./AxX93GTOgf01L/e.TqCdxzXNh5msNhoHj1Y4fJa', 1, 'John', 'Doe', 'jhon.doe@correo.com');

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles(usuario_id, rol_id) VALUES (1,1);
INSERT INTO usuarios_roles(usuario_id, rol_id) VALUES (2,2);
INSERT INTO usuarios_roles(usuario_id, rol_id) VALUES (2,1);