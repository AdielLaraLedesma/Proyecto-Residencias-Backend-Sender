CREATE TABLE reunion(
    id INT NOT NULL PRIMARY KEY AUTOINCREMENT,
    numeroParticipantes INT NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    horaInicio DATE NOT NULL,
    horaFin DATE NOT NULL,
    idDepuracion VARCHAR(20) NOT NULL,
)

CREATE TABLE participantes(){
    id INT NOT NULL PRIMARY KEY AUTOINCREMENT,
    nombreCompleto VARCHAR(30) NOT NULL,
    horaUnion DATE NOT NULL,
    horaSalida DATE NOT NULL,
    duracion VARCHAR(10) NOT NULL,
    email VARCHAR(15) NOT NULL,
    rol VARCHAR(10) NOT NULL
    reunion_id INT REFERENCES reunion(id)
}