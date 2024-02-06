
USE simusaldo;
GO
drop database SegurosCSC


-- Creación de la base de datos
CREATE DATABASE SegurosCSC;
GO

USE SegurosCSC;
GO





-- Tabla de Estudiantes
CREATE TABLE Estudiantes (
    cedula INT PRIMARY KEY,
	nomCentroE NVARCHAR(255) NOT NULL,
    nombre NVARCHAR(255) NOT NULL,
    apellido NVARCHAR(255) NOT NULL,
    email NVARCHAR(255) NOT NULL,
    telefono NVARCHAR(20)
);


CREATE TABLE tipoPolizaEstudiante (
    idPoliza INT PRIMARY KEY IDENTITY(1,1),
    tipoPoliza NVARCHAR(50),
    descripcion NVARCHAR(500),
	cobertura DECIMAL(10, 2),
    precio DECIMAL(15, 2),
	beneficiario NVARCHAR(50),
);



CREATE TABLE polizaXestudiantes (
    idPxE INT PRIMARY KEY identity(1,1),
	cedula INT,
	idPoliza INT,
	FOREIGN KEY (cedula) REFERENCES Estudiantes(cedula),
	FOREIGN KEY (idPoliza) REFERENCES tipoPolizaEstudiante(idPoliza)
);


-- Tabla de Clientes
CREATE TABLE Clientes (
    cedula  INT PRIMARY KEY ,
    nombre NVARCHAR(255) NOT NULL,
    apellido NVARCHAR(255) NOT NULL,
    email NVARCHAR(255) NOT NULL,
    telefono NVARCHAR(20),
	fechaInicio DATE, 
    fechaFinal DATE
);


CREATE TABLE tipoPolizaViajes (
    idPoliza INT PRIMARY KEY IDENTITY(1,1),
    tipoPoliza NVARCHAR(50),
    descripcion NVARCHAR(500),
	cobertura DECIMAL(15, 2),
    precio DECIMAL(10, 2),
	beneficiario NVARCHAR(50),
);





CREATE TABLE polizaXclientes (
    idPxE INT PRIMARY KEY identity(1,1),
	cedula INT,
	idPoliza INT,
	FOREIGN KEY (cedula) REFERENCES Clientes(cedula),
	FOREIGN KEY (idPoliza) REFERENCES tipoPolizaViajes(idPoliza)
);



CREATE PROCEDURE InsertarCliente
    @Cedula INT,
    @Nombre NVARCHAR(255),
    @Apellido NVARCHAR(255),
    @Email NVARCHAR(255),
    @Telefono NVARCHAR(20),
    @FechaInicio DATE,
    @FechaFinal DATE
AS
BEGIN
    INSERT INTO Clientes (cedula, nombre, apellido, email, telefono, fechaInicio, fechaFinal)
    VALUES (@Cedula, @Nombre, @Apellido, @Email, @Telefono, @FechaInicio, @FechaFinal);
END;


CREATE PROCEDURE InsertarEstudiante
    @Cedula INT,
    @NomCentroE NVARCHAR(255),
    @Nombre NVARCHAR(255),
    @Apellido NVARCHAR(255),
    @Email NVARCHAR(255),
    @Telefono NVARCHAR(20)
AS
BEGIN
    INSERT INTO Estudiantes (cedula, nomCentroE, nombre, apellido, email, telefono)
    VALUES (@Cedula, @NomCentroE, @Nombre, @Apellido, @Email, @Telefono);
END;



CREATE PROCEDURE MostrarTiposPolizasViajes
AS
BEGIN
   
    SELECT * FROM tipoPolizaViajes;
END;



CREATE or alter PROCEDURE MostrarTiposPolizasEstudiantes
AS
BEGIN
   
    SELECT * FROM tipoPolizaEstudiante;
END;



-- Inserción en tipoPolizaEstudiante
INSERT INTO tipoPolizaEstudiante (tipoPoliza, descripcion, cobertura, precio)
VALUES
    ('Seguro de Vida', 'Gastos Médicos, Referencias nutricionales telefónicas, Orientación Psicológica, Orientación Pedagógica', 300000.00, 10000.00 ),
    ('Seguro de Salud', 'Gastos Médicos, Referencias nutricionales telefónicas, Orientación Psicológica, Orientación Pedagógica', 600000.00, 15000.00 ),
    ('Seguro de Accidentes',  'Gastos Médicos, Referencias nutricionales telefónicas, Orientación Psicológica, Orientación Pedagógica', 750000.00, 20000.00 )


-- Inserción en tipoPolizaEstudiante
INSERT INTO tipoPolizaViajes(tipoPoliza, descripcion, cobertura, precio)
VALUES
    ('Seguro de plus', 'Cobertura de Gastos Médicos Accidente o Enfermedad, Cobertura de Muerte Accidental del Asegurado, Cobertura de Renta Diaria por Hospitalización', 5000000.00, 100000.00 ),
    ('Seguro de standar', 'Cobertura de Retraso en el viaje, Cobertura de Pérdida de Equipaje, Cobertura de Cancelación y Acortamiento de Viaje', 2000000.00, 75000.00 ),
    ('Seguro de básico',  'Cobertura de Retraso en el viaje', 500000.00, 25000.00 )




SELECT * FROM Clientes;

SELECT * FROM Estudiantes;

SELECT * FROM tipoPolizaEstudiante;

SELECT * FROM tipoPolizaViajes;

SELECT * FROM polizaXestudiantes;

SELECT * FROM polizaXclientes;


INSERT INTO tipoPolizaEstudiante (beneficiario)
VALUES
    ('Gastos Médicos')


INSERT INTO polizaXestudiantes (cedula, idPoliza)
VALUES (305350161, 1);

INSERT INTO Estudiantes (cedula, nomCentroE, nombre, apellido, email, telefono)
VALUES (305350161, 'CUC', 'Esteban', 'Gomez Rivera', 'esteban@gmail.com', '60721746');

-- Eliminar registros relacionados en la tabla polizaXestudiantes
DELETE FROM polizaXestudiantes WHERE cedula = 305350161;

-- Ahora puedes eliminar el registro en la tabla Estudiantes
DELETE FROM Estudiantes WHERE cedula = 305350161;



SELECT e.cedula, e.nombre, e.apellido, t.tipoPoliza, t.descripcion, t.cobertura, t.precio, t.beneficiario
                             FROM Estudiantes e
                             JOIN polizaXestudiantes px ON e.cedula = px.cedula 
                             JOIN tipoPolizaEstudiante t ON px.idPoliza = t.idPoliza
                             WHERE e.cedula = 123456789