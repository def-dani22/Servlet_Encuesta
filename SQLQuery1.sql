create database bdinf
go

use bdinf
go



CREATE TABLE tablaCliente (
    id INT PRIMARY KEY identity(1,1),
    nombre NVARCHAR(255),
	apellidos NVARCHAR(255),
	opinion NVARCHAR(255),
	comentarios NVARCHAR(255),
);



select * from tablaCliente