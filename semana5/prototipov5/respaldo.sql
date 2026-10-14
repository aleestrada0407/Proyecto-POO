PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE usuarios (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  nombre TEXT NOT NULL,
  seccion TEXT NOT NULL,
  nombre_usuario TEXT NOT NULL UNIQUE,
  contrasena_hash TEXT NOT NULL,
  tipo TEXT NOT NULL CHECK (tipo IN ('Estudiante', 'Profesor'))
);
CREATE TABLE ejercicios_balanceo (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  enunciado TEXT NOT NULL
);
CREATE TABLE compuestos (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  ejercicio_id INTEGER NOT NULL,
  lado TEXT NOT NULL CHECK (lado IN ('R', 'P')),
  posicion INTEGER NOT NULL,
  formula TEXT NOT NULL,
  coef_sugerido INTEGER NOT NULL CHECK (coef_sugerido >= 1),
  FOREIGN KEY (ejercicio_id) REFERENCES ejercicios_balanceo(id),
  UNIQUE (ejercicio_id, lado, posicion)
);
CREATE TABLE resultados (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  usuario_id INTEGER NOT NULL,
  ejercicio_id INTEGER NOT NULL,
  correcto INTEGER NOT NULL CHECK (correcto IN (0, 1)),
  fecha TEXT DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
  FOREIGN KEY (ejercicio_id) REFERENCES ejercicios_balanceo(id)
);
CREATE TABLE respuestas_coef (
  resultado_id INTEGER NOT NULL,
  lado TEXT NOT NULL CHECK (lado IN ('R', 'P')),
  posicion INTEGER NOT NULL,
  coeficiente INTEGER NOT NULL,
  PRIMARY KEY (resultado_id, lado, posicion),
  FOREIGN KEY (resultado_id) REFERENCES resultados(id)
);
COMMIT;
