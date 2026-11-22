CREATE SCHEMA bd_Tarea3TransaccionesFicheroTXT;
USE bd_Tarea3TransaccionesFicheroTXT;

-- Creacion de las Tablas

CREATE TABLE Alumno(
NumMatricula INT AUTO_INCREMENT PRIMARY KEY,
Nombre VARCHAR(100),
FechaNacimiento DATE,
Tlfn CHAR(9)
);

CREATE TABLE Profesor(
IdProfesor INT AUTO_INCREMENT PRIMARY KEY,
NIF_P CHAR(9) UNIQUE,
Nombre VARCHAR(100),
Especialidad VARCHAR(100),
Tlfn CHAR(9)
);

CREATE TABLE Asignatura(
CodAsignatura CHAR(10) PRIMARY KEY,
Nombre VARCHAR(100),
IdProfesor INT,

-- Foreign Key
CONSTRAINT fk_IdProfesorAsignatura FOREIGN KEY(IdProfesor) REFERENCES Profesor(IdProfesor)
);

CREATE TABLE Recibe(
NumMatricula INT,
CodAsignatura CHAR(10),
CursoEscolar CHAR(9),
PRIMARY KEY(NumMatricula,CodAsignatura),

CONSTRAINT fk_NumMatriculaRecibe FOREIGN KEY(NumMatricula) REFERENCES Alumno(NumMatricula),
CONSTRAINT fk_CodAsignaturaRecibe FOREIGN KEY(CodAsignatura) REFERENCES Asignatura(CodAsignatura)
);