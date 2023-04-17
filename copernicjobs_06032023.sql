DROP DATABASE IF EXISTS copernicjobs;
CREATE DATABASE IF NOT EXISTS copernicjobs;
GRANT ALL PRIVILEGES ON copernicjobs.* TO 'adminCopernicJobs'@'localhost';

USE copernicjobs;
CREATE TABLE IF NOT EXISTS rol (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS modulo (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS rolmodulo (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    rol_id INT NOT NULL,
    modulo_id INT NOT NULL,
    visibilidad BOOLEAN DEFAULT TRUE,
	CONSTRAINT fk_rolmodulo_rol FOREIGN KEY (rol_id) REFERENCES rol(ID) ON DELETE CASCADE,
    CONSTRAINT fk_rolmodulo_modulo FOREIGN KEY (modulo_id) REFERENCES modulo(ID) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS usuario (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(128) NOT NULL UNIQUE,
    password VARCHAR(128) NOT NULL,
    baja BOOLEAN DEFAULT FALSE NOT NULL,
    rol_id INT NOT NULL,
    fecha_registro DATE NOT NULL,
    fecha_validacion DATE,
    fecha_baja DATE,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(200) NOT NULL,
    sexo INT,
    correo_contacto VARCHAR(128),
    sexo_desc VARCHAR (50),
    cod_postal VARCHAR (5),
    municipio VARCHAR(50),
    direccion VARCHAR(200),
    movil VARCHAR(12),
    movil_empresa VARCHAR(12),
    username_contacto VARCHAR (50),
    descripcion_empresa TEXT,
	nombre_empresa VARCHAR (50),
    web_empresa VARCHAR (50),
    tarjeta_sanitaria VARCHAR(100),
    seg_social VARCHAR (200),
    linkedin VARCHAR(50),
    portafoli_personal VARCHAR(50),
    curriculum_online VARCHAR(50),
    pdf_link VARCHAR(50),
    avatar_link VARCHAR(200),
    dni VARCHAR(9),
    CONSTRAINT fk_usuario_rol FOREIGN KEY (rol_id) REFERENCES rol(ID) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS oferta (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    empresa_id INT,
    fecha_validacion DATE,
    enlace_pdf TEXT,
    titulo VARCHAR(50),
    descripcion TEXT,
    requisitos TEXT,
    se_ofrece TEXT,
    fecha_peticion DATE NOT NULL,
    baja BOOLEAN,
    CONSTRAINT fk_oferta_empresa FOREIGN KEY (empresa_id) REFERENCES usuario(ID) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS inscripcion (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    alumno_id INT,
    fecha_inscripcion DATE NOT NULL,
    estado INT NOT NULL,
    oferta_id INT,
    CONSTRAINT fk_inscripcion_alumno FOREIGN KEY (alumno_id) REFERENCES usuario(ID) ON DELETE CASCADE,
    CONSTRAINT fk_inscripcion_oferta FOREIGN KEY (oferta_id) REFERENCES oferta(ID)ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS incidencia (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    descripcion TEXT,
    titulo VARCHAR(50) NOT NULL,
    fecha_incidencia DATE NOT NULL,
    estado INT NOT NULL,
    usuario_id INT,
    CONSTRAINT fk_incidencia_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(ID) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS noticia (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(50) NOT NULL,
    descripcion TEXT NOT NULL,
    fecha_envio DATE NOT NULL,
    rol_id INT,
    CONSTRAINT fk_noticia_rol FOREIGN KEY (rol_id) REFERENCES rol(ID) ON DELETE CASCADE
);

/*
*	1 --> alumne
*	2 --> administrador
*	3 --> empresa
*/
INSERT INTO rol (nombre) VALUES ("alumne"), ("administrador"), ("empresa");
INSERT INTO modulo (nombre) VALUES ("alumne"), ("administrador"), ("empresa");

INSERT INTO rolmodulo (rol_id, modulo_id) VALUES (1,1), (3,3), (2,1), (2,2), (2,3);
INSERT INTO usuario (fecha_validacion, username, password, rol_id, fecha_registro, nombre, apellidos, sexo, sexo_desc, cod_postal, municipio, direccion, movil, username_contacto, tarjeta_sanitaria, seg_social, dni,linkedin,portafoli_personal,curriculum_online,pdf_link,avatar_link)
VALUES ('2023-03-07','usuario1@gmail.com', '$2a$10$PE/9eCx/bndkYcYPKEdD0O48VDeqgBMtuUdtXimIsxyIBCJ4/zofW', 1, '2022-01-01', 'Juan', 'García Pérez', 1, 'Home', '08001', 'Barcelona', 'Carrer Major, 1', '666555444', 'contacto1@gmail.com', '01234567891234', '123456789012', '12345678A','https://www.linkedin.com/home/','https://www.google.com/','https://www.onlinecv.es/','https://www.onlinecv.es/','https://picsum.photos/200/300');

INSERT INTO usuario (fecha_validacion, username, password, rol_id, fecha_registro, nombre, apellidos, sexo, sexo_desc, cod_postal, municipio, direccion, movil, username_contacto, tarjeta_sanitaria, seg_social, dni,linkedin,portafoli_personal,curriculum_online,pdf_link,avatar_link)
VALUES ('2023-03-07','usuario2@gmail.com', '$2a$10$PE/9eCx/bndkYcYPKEdD0O48VDeqgBMtuUdtXimIsxyIBCJ4/zofW', 1, '2023-02-24', 'Alex', 'Cruceat', 1, 'Home', '08001', 'Barcelona', 'Carrer Major, 1', '666555444', 'contacto2@gmail.com', '01234567891234', '123456789012', '12345678A','https://www.linkedin.com/home/','https://www.google.com/','https://www.onlinecv.es/','https://www.onlinecv.es/','https://picsum.photos/200/300');

INSERT INTO usuario (fecha_validacion, movil_empresa,username, password, rol_id, fecha_registro, nombre, apellidos, sexo, sexo_desc, cod_postal, municipio, direccion, movil, username_contacto,nombre_empresa,descripcion_empresa, web_empresa,  tarjeta_sanitaria, seg_social, dni)
VALUES ('2023-03-07','666666666','j.garcia@enttia.com', '$2a$10$2iICz6kUjPMd8hV7L8QtJ.u6FmE0hBgIthCwIvLhlld8Xei/wi202', 3, '2023-01-01', 'Josep', 'Garcia', 1, 'Home', '08205', 'Terrassa', 'Carrer Frederic Mompou, 36', '655722344', 'j.garcia@enttia.com', "ENTTIA","Empresa diriga a la automocion.", "https://enttia.com/", '23778876AAAA', '1928379', '45858349J');

INSERT INTO usuario (fecha_validacion,movil_empresa,username, password, rol_id, fecha_registro, nombre, apellidos, sexo, sexo_desc, cod_postal, municipio, direccion, movil, username_contacto,nombre_empresa,descripcion_empresa, web_empresa,  tarjeta_sanitaria, seg_social, dni)
VALUES ('2023-03-07','666666669','ecobos@albirasolutions.com', 'ecobos@albirasolutions.com', 3, '2023-01-01', 'Eva', 'Cobos', 2, 'Dona', '08226', 'Terrassa', 'Rambla Francesc Macia, 18', '655722344', 'ecobos@albirasolutions.com',"Albira Solutions","We partner with you to make your digital innovation initiative a success.
We offer technology and industry expertise combined with proven processes and methodologies. Avoid the cost overruns and unmet expectations too many companies face when digitalizing their business.", "https://www.albirasolutions.com/en/", '8922833AAAA', '2883324222', '45858123E');

INSERT INTO usuario (fecha_validacion,username, password, rol_id, fecha_registro, nombre, apellidos)
VALUES ('2023-03-07','admin1@gmail.com', '$2a$10$PE/9eCx/bndkYcYPKEdD0O48VDeqgBMtuUdtXimIsxyIBCJ4/zofW', 2, '2023-02-27', 'Joan','Galindo');

INSERT INTO usuario (fecha_validacion,username, password, rol_id, fecha_registro, nombre, apellidos)
VALUES ('2023-03-07,','admin2@gmail.com', '$2a$10$PE/9eCx/bndkYcYPKEdD0O48VDeqgBMtuUdtXimIsxyIBCJ4/zofW', 2, '2023-02-27', 'Joan','Galindo');

INSERT INTO incidencia (titulo,descripcion, fecha_incidencia, estado, usuario_id)
VALUES ('Titulo1','Exemple incidència creada per un alumne', '2023-02-27', 1, 2);

INSERT INTO incidencia (titulo,descripcion, fecha_incidencia, estado, usuario_id)
VALUES ('Titulo2','Exemple incidència creada per un alumne', '2023-02-27', 1, 2);

INSERT INTO incidencia (titulo,descripcion, fecha_incidencia, estado, usuario_id)
VALUES ("Titulo3",'Exemple incidència creada per un alumne', '2023-02-27', 1, 2);

INSERT INTO noticia (titulo, descripcion, fecha_envio, rol_id)
VALUES ('Noticia01', 'Primera noticia creada!', '2023-02-27', 2);

INSERT INTO noticia (titulo, descripcion, fecha_envio, rol_id)
VALUES ('Noticia02', 'Exemple contingut de la noticia creada per un administrador.', '2023-02-27', 2);

INSERT INTO noticia (titulo, descripcion, fecha_envio, rol_id)
VALUES ('Noticia03', 'Us informem que aquesta noticia ha estat creada correctament.', '2023-02-27', 1);

INSERT INTO noticia (titulo, descripcion, fecha_envio, rol_id)
VALUES ('Noticia04', 'Us informem que aquesta noticia ha estat creada correctament.', '2023-02-27', 1);
INSERT INTO noticia (titulo, descripcion, fecha_envio, rol_id)
VALUES ('Noticia04', 'Us informem que aquesta noticia ha estat creada correctament.', '2023-02-27', 1);
INSERT INTO noticia (titulo, descripcion, fecha_envio, rol_id)
VALUES ('Noticia04', 'Us informem que aquesta noticia ha estat creada correctament.', '2023-02-27', 1);
INSERT INTO noticia (titulo, descripcion, fecha_envio, rol_id)
VALUES ('Noticia04', 'Us informem que aquesta noticia ha estat creada correctament.', '2023-02-27', 1);
INSERT INTO noticia (titulo, descripcion, fecha_envio, rol_id)
VALUES ('Noticia04', 'Us informem que aquesta noticia ha estat creada correctament.', '2023-02-27', 1);
INSERT INTO noticia (titulo, descripcion, fecha_envio, rol_id)
VALUES ('Noticia04', 'Us informem que aquesta noticia ha estat creada correctament.', '2023-02-27', 1);
INSERT INTO oferta(empresa_id, fecha_validacion,titulo,descripcion,requisitos,se_ofrece,fecha_peticion,baja)
VALUES(
3,"2022-10-17","Programador Albira Solutions", 
"Funcions: Desenvolupament de software: aplicacions i plataformes per a web i mòbils,
desplegant-les en el núvol, millorant algorismes, automatitzant processos comercials i
analitzar dades comercials clau per al client.", 
"Haver finalitzat o estar a punt de finalitzar DAM
Enviar currículum a l'adreça del contacte.",
"Trabajar en un equipo joven, dinámico, en constante crecimiento y progreso
Formación en TDD, DDD, arquitecturas de backend escalables y entornos de alta disponibilidad
Incorporación a una empresa con fuerte base tecnológica y gran crecimiento
Trabajo remoto
Horario flexible
Oficina moderna en el centro de Barcelona
Contrato indefinido
Formación tecnológica
Ventajas de retribución flexible en comida y transporte
Salario: entre 40 y 50k anuales",
"2022-01-01",  false);

INSERT INTO oferta(empresa_id, fecha_validacion,titulo,descripcion,requisitos,se_ofrece,fecha_peticion,baja)VALUES(4,"2023-01-22",
"Programador Enttia", 
"Enginyeria amb una naturalesa de negoci relacionada amb les tecnologies de la informació i especialitzada en projectes de seguretat i 
identificació de persones cerca programador per a desenvolupar aplicacions amb . Net, WEB i SQL.", 
"Aprenentatge i utilització de coneixements
Flexibilitat i gestió del canvi
Treball en equip i cooperació
.NET (desitjable C#)
Desenvolupament web (HTML, CSS, JavaScript)
Base de dades SQL (principalment SQL Server)
Desitjable coneixements de frameworks web basats en components (React, per exemple) i administració de sistemes i/o núvol.",
"Trabajar en un equipo joven, dinámico, en constante crecimiento y progreso
Formación en TDD, DDD, arquitecturas de backend escalables y entornos de alta disponibilidad
Incorporación a una empresa con fuerte base tecnológica y gran crecimiento
Trabajo remoto
Horario flexible
Oficina moderna en el centro de Barcelona
Contrato indefinido
Formación tecnológica
Ventajas de retribución flexible en comida y transporte
Salario: entre 40 y 50k anuales","2022-02-15",  false);

INSERT INTO oferta(empresa_id, fecha_validacion,titulo,descripcion,requisitos,se_ofrece,fecha_peticion,baja)VALUES(4,"2023-03-07",
"Oferta esta semana", 
"Enginyeria amb una naturalesa de negoci relacionada amb les tecnologies de la informació i especialitzada en projectes de seguretat i 
identificació de persones cerca programador per a desenvolupar aplicacions amb . Net, WEB i SQL.", 
"Aprenentatge i utilització de coneixements
Flexibilitat i gestió del canvi
Treball en equip i cooperació
.NET (desitjable C#)
Desenvolupament web (HTML, CSS, JavaScript)
Base de dades SQL (principalment SQL Server)
Desitjable coneixements de frameworks web basats en components (React, per exemple) i administració de sistemes i/o núvol.",
"Trabajar en un equipo joven, dinámico, en constante crecimiento y progreso
Formación en TDD, DDD, arquitecturas de backend escalables y entornos de alta disponibilidad
Incorporación a una empresa con fuerte base tecnológica y gran crecimiento
Trabajo remoto
Horario flexible
Oficina moderna en el centro de Barcelona
Contrato indefinido
Formación tecnológica
Ventajas de retribución flexible en comida y transporte
Salario: entre 40 y 50k anuales","2023-03-07",  false);

INSERT INTO oferta(empresa_id, fecha_validacion,titulo,descripcion,requisitos,se_ofrece,fecha_peticion,baja)VALUES(4,"2023-03-20",
"Oferta esta semana2", 
"Enginyeria amb una naturalesa de negoci relacionada amb les tecnologies de la informació i especialitzada en projectes de seguretat i 
identificació de persones cerca programador per a desenvolupar aplicacions amb . Net, WEB i SQL.", 
"Aprenentatge i utilització de coneixements
Flexibilitat i gestió del canvi
Treball en equip i cooperació
.NET (desitjable C#)
Desenvolupament web (HTML, CSS, JavaScript)
Base de dades SQL (principalment SQL Server)
Desitjable coneixements de frameworks web basats en components (React, per exemple) i administració de sistemes i/o núvol.",
"Trabajar en un equipo joven, dinámico, en constante crecimiento y progreso
Formación en TDD, DDD, arquitecturas de backend escalables y entornos de alta disponibilidad
Incorporación a una empresa con fuerte base tecnológica y gran crecimiento
Trabajo remoto
Horario flexible
Oficina moderna en el centro de Barcelona
Contrato indefinido
Formación tecnológica
Ventajas de retribución flexible en comida y transporte
Salario: entre 40 y 50k anuales","2023-03-07",  false);

INSERT INTO oferta(empresa_id, fecha_validacion,titulo,descripcion,requisitos,se_ofrece,fecha_peticion,baja)VALUES(4,"2023-03-20",
"Oferta esta semana2", 
"Enginyeria amb una naturalesa de negoci relacionada amb les tecnologies de la informació i especialitzada en projectes de seguretat i 
identificació de persones cerca programador per a desenvolupar aplicacions amb . Net, WEB i SQL.", 
"Aprenentatge i utilització de coneixements
Flexibilitat i gestió del canvi
Treball en equip i cooperació
.NET (desitjable C#)
Desenvolupament web (HTML, CSS, JavaScript)
Base de dades SQL (principalment SQL Server)
Desitjable coneixements de frameworks web basats en components (React, per exemple) i administració de sistemes i/o núvol.",
"Trabajar en un equipo joven, dinámico, en constante crecimiento y progreso
Formación en TDD, DDD, arquitecturas de backend escalables y entornos de alta disponibilidad
Incorporación a una empresa con fuerte base tecnológica y gran crecimiento
Trabajo remoto
Horario flexible
Oficina moderna en el centro de Barcelona
Contrato indefinido
Formación tecnológica
Ventajas de retribución flexible en comida y transporte
Salario: entre 40 y 50k anuales","2023-03-07",  false);
INSERT INTO oferta(empresa_id, fecha_validacion,titulo,descripcion,requisitos,se_ofrece,fecha_peticion,baja)VALUES(4,"2023-03-20",
"Oferta esta semana2", 
"Enginyeria amb una naturalesa de negoci relacionada amb les tecnologies de la informació i especialitzada en projectes de seguretat i 
identificació de persones cerca programador per a desenvolupar aplicacions amb . Net, WEB i SQL.", 
"Aprenentatge i utilització de coneixements
Flexibilitat i gestió del canvi
Treball en equip i cooperació
.NET (desitjable C#)
Desenvolupament web (HTML, CSS, JavaScript)
Base de dades SQL (principalment SQL Server)
Desitjable coneixements de frameworks web basats en components (React, per exemple) i administració de sistemes i/o núvol.",
"Trabajar en un equipo joven, dinámico, en constante crecimiento y progreso
Formación en TDD, DDD, arquitecturas de backend escalables y entornos de alta disponibilidad
Incorporación a una empresa con fuerte base tecnológica y gran crecimiento
Trabajo remoto
Horario flexible
Oficina moderna en el centro de Barcelona
Contrato indefinido
Formación tecnológica
Ventajas de retribución flexible en comida y transporte
Salario: entre 40 y 50k anuales","2023-03-07",  false);
INSERT INTO oferta(empresa_id, fecha_validacion,titulo,descripcion,requisitos,se_ofrece,fecha_peticion,baja)VALUES(4,"2023-03-20",
"Oferta esta semana2", 
"Enginyeria amb una naturalesa de negoci relacionada amb les tecnologies de la informació i especialitzada en projectes de seguretat i 
identificació de persones cerca programador per a desenvolupar aplicacions amb . Net, WEB i SQL.", 
"Aprenentatge i utilització de coneixements
Flexibilitat i gestió del canvi
Treball en equip i cooperació
.NET (desitjable C#)
Desenvolupament web (HTML, CSS, JavaScript)
Base de dades SQL (principalment SQL Server)
Desitjable coneixements de frameworks web basats en components (React, per exemple) i administració de sistemes i/o núvol.",
"Trabajar en un equipo joven, dinámico, en constante crecimiento y progreso
Formación en TDD, DDD, arquitecturas de backend escalables y entornos de alta disponibilidad
Incorporación a una empresa con fuerte base tecnológica y gran crecimiento
Trabajo remoto
Horario flexible
Oficina moderna en el centro de Barcelona
Contrato indefinido
Formación tecnológica
Ventajas de retribución flexible en comida y transporte
Salario: entre 40 y 50k anuales","2023-03-07",  false);
INSERT INTO oferta(empresa_id, fecha_validacion,titulo,descripcion,requisitos,se_ofrece,fecha_peticion,baja)VALUES(4,"2023-03-20",
"Oferta esta semana2", 
"Enginyeria amb una naturalesa de negoci relacionada amb les tecnologies de la informació i especialitzada en projectes de seguretat i 
identificació de persones cerca programador per a desenvolupar aplicacions amb . Net, WEB i SQL.", 
"Aprenentatge i utilització de coneixements
Flexibilitat i gestió del canvi
Treball en equip i cooperació
.NET (desitjable C#)
Desenvolupament web (HTML, CSS, JavaScript)
Base de dades SQL (principalment SQL Server)
Desitjable coneixements de frameworks web basats en components (React, per exemple) i administració de sistemes i/o núvol.",
"Trabajar en un equipo joven, dinámico, en constante crecimiento y progreso
Formación en TDD, DDD, arquitecturas de backend escalables y entornos de alta disponibilidad
Incorporación a una empresa con fuerte base tecnológica y gran crecimiento
Trabajo remoto
Horario flexible
Oficina moderna en el centro de Barcelona
Contrato indefinido
Formación tecnológica
Ventajas de retribución flexible en comida y transporte
Salario: entre 40 y 50k anuales","2023-03-07",  false);
INSERT INTO oferta(empresa_id, fecha_validacion,titulo,descripcion,requisitos,se_ofrece,fecha_peticion,baja)VALUES(4,"2023-03-20",
"Oferta esta semana2", 
"Enginyeria amb una naturalesa de negoci relacionada amb les tecnologies de la informació i especialitzada en projectes de seguretat i 
identificació de persones cerca programador per a desenvolupar aplicacions amb . Net, WEB i SQL.", 
"Aprenentatge i utilització de coneixements
Flexibilitat i gestió del canvi
Treball en equip i cooperació
.NET (desitjable C#)
Desenvolupament web (HTML, CSS, JavaScript)
Base de dades SQL (principalment SQL Server)
Desitjable coneixements de frameworks web basats en components (React, per exemple) i administració de sistemes i/o núvol.",
"Trabajar en un equipo joven, dinámico, en constante crecimiento y progreso
Formación en TDD, DDD, arquitecturas de backend escalables y entornos de alta disponibilidad
Incorporación a una empresa con fuerte base tecnológica y gran crecimiento
Trabajo remoto
Horario flexible
Oficina moderna en el centro de Barcelona
Contrato indefinido
Formación tecnológica
Ventajas de retribución flexible en comida y transporte
Salario: entre 40 y 50k anuales","2023-03-07",  false);
INSERT INTO oferta(empresa_id, fecha_validacion,titulo,descripcion,requisitos,se_ofrece,fecha_peticion,baja)VALUES(4,"2023-03-20",
"Oferta esta semana2", 
"Enginyeria amb una naturalesa de negoci relacionada amb les tecnologies de la informació i especialitzada en projectes de seguretat i 
identificació de persones cerca programador per a desenvolupar aplicacions amb . Net, WEB i SQL.", 
"Aprenentatge i utilització de coneixements
Flexibilitat i gestió del canvi
Treball en equip i cooperació
.NET (desitjable C#)
Desenvolupament web (HTML, CSS, JavaScript)
Base de dades SQL (principalment SQL Server)
Desitjable coneixements de frameworks web basats en components (React, per exemple) i administració de sistemes i/o núvol.",
"Trabajar en un equipo joven, dinámico, en constante crecimiento y progreso
Formación en TDD, DDD, arquitecturas de backend escalables y entornos de alta disponibilidad
Incorporación a una empresa con fuerte base tecnológica y gran crecimiento
Trabajo remoto
Horario flexible
Oficina moderna en el centro de Barcelona
Contrato indefinido
Formación tecnológica
Ventajas de retribución flexible en comida y transporte
Salario: entre 40 y 50k anuales","2023-03-07",  false);
INSERT INTO oferta(empresa_id, fecha_validacion,titulo,descripcion,requisitos,se_ofrece,fecha_peticion,baja)VALUES(4,"2023-03-20",
"Oferta esta semana2", 
"Enginyeria amb una naturalesa de negoci relacionada amb les tecnologies de la informació i especialitzada en projectes de seguretat i 
identificació de persones cerca programador per a desenvolupar aplicacions amb . Net, WEB i SQL.", 
"Aprenentatge i utilització de coneixements
Flexibilitat i gestió del canvi
Treball en equip i cooperació
.NET (desitjable C#)
Desenvolupament rolmoduloweb (HTML, CSS, JavaScript)
Base de dades SQL (principalment SQL Server)
Desitjable coneixements de frameworks web basats en components (React, per exemple) i administració de sistemes i/o núvol.",
"Trabajar en un equipo joven, dinámico, en constante crecimiento y progreso
Formación en TDD, DDD, arquitecturas de backend escalables y entornos de alta disponibilidad
Incorporación a una empresa con fuerte base tecnológica y gran crecimiento
Trabajo remoto
Horario flexible
Oficina moderna en el centro de Barcelona
Contrato indefinido
Formación tecnológica
Ventajas de retribución flexible en comida y transporte
Salario: entre 40 y 50k anuales","2023-03-07",  false);
INSERT INTO oferta(empresa_id, fecha_validacion,titulo,descripcion,requisitos,se_ofrece,fecha_peticion,baja)VALUES(4,"2023-03-20",
"Oferta esta semana2", 
"Enginyeria amb una naturalesa de negoci relacionada amb les tecnologies de la informació i especialitzada en projectes de seguretat i 
identificació de persones cerca programador per a desenvolupar aplicacions amb . Net, WEB i SQL.", 
"Aprenentatge i utilització de coneixements
Flexibilitat i gestió del canvi
Treball en equip i cooperació
.NET (desitjable C#)
Desenvolupament rolmoduloweb (HTML, CSS, JavaScript)
Base de dades SQL (principalment SQL Server)
Desitjable coneixements de frameworks web basats en components (React, per exemple) i administració de sistemes i/o núvol.",
"Trabajar en un equipo joven, dinámico, en constante crecimiento y progreso
Formación en TDD, DDD, arquitecturas de backend escalables y entornos de alta disponibilidad
Incorporación a una empresa con fuerte base tecnológica y gran crecimiento
Trabajo remoto
Horario flexible
Oficina moderna en el centro de Barcelona
Contrato indefinido
Formación tecnológica
Ventajas de retribución flexible en comida y transporte
Salario: entre 40 y 50k anuales","2023-03-07",  false);
INSERT INTO oferta(empresa_id, fecha_validacion,titulo,descripcion,requisitos,se_ofrece,fecha_peticion,baja)VALUES(4,"2023-03-28",
"A", 
"Enginyeria amb una naturalesa de negoci relacionada amb les tecnologies de la informació i especialitzada en projectes de seguretat i 
identificació de persones cerca programador per a desenvolupar aplicacions amb . Net, WEB i SQL.", 
"Aprenentatge i utilització de coneixements
Flexibilitat i gestió del canvi
Treball en equip i cooperació
.NET (desitjable C#)
Desenvolupament rolmoduloweb (HTML, CSS, JavaScript)
Base de dades SQL (principalment SQL Server)
Desitjable coneixements de frameworks web basats en components (React, per exemple) i administració de sistemes i/o núvol.",
"Trabajar en un equipo joven, dinámico, en constante crecimiento y progreso
Formación en TDD, DDD, arquitecturas de backend escalables y entornos de alta disponibilidad
Incorporación a una empresa con fuerte base tecnológica y gran crecimiento
Trabajo remoto
Horario flexible
Oficina moderna en el centro de Barcelona
Contrato indefinido
Formación tecnológica
Ventajas de retribución flexible en comida y transporte
Salario: entre 40 y 50k anuales","2023-03-07",  false);
INSERT INTO oferta(empresa_id, fecha_validacion,titulo,descripcion,requisitos,se_ofrece,fecha_peticion,baja)VALUES(4,null,
"B", 
"Enginyeria amb una naturalesa de negoci relacionada amb les tecnologies de la informació i especialitzada en projectes de seguretat i 
identificació de persones cerca programador per a desenvolupar aplicacions amb . Net, WEB i SQL.", 
"Aprenentatge i utilització de coneixements
Flexibilitat i gestió del canvi
Treball en equip i cooperació
.NET (desitjable C#)
Desenvolupament rolmoduloweb (HTML, CSS, JavaScript)
Base de dades SQL (principalment SQL Server)
Desitjable coneixements de frameworks web basats en components (React, per exemple) i administració de sistemes i/o núvol.",
"Trabajar en un equipo joven, dinámico, en constante crecimiento y progreso
Formación en TDD, DDD, arquitecturas de backend escalables y entornos de alta disponibilidad
Incorporación a una empresa con fuerte base tecnológica y gran crecimiento
Trabajo remoto
Horario flexible
Oficina moderna en el centro de Barcelona
Contrato indefinido
Formación tecnológica
Ventajas de retribución flexible en comida y transporte
Salario: entre 40 y 50k anuales","2023-03-07",  false);
INSERT INTO oferta(empresa_id, fecha_validacion,titulo,descripcion,requisitos,se_ofrece,fecha_peticion,baja)VALUES(4,null,
"X", 
"Enginyeria amb una naturalesa de negoci relacionada amb les tecnologies de la informació i especialitzada en projectes de seguretat i 
identificació de persones cerca programador per a desenvolupar aplicacions amb . Net, WEB i SQL.", 
"Aprenentatge i utilització de coneixements
Flexibilitat i gestió del canvi
Treball en equip i cooperació
.NET (desitjable C#)
Desenvolupament rolmoduloweb (HTML, CSS, JavaScript)
Base de dades SQL (principalment SQL Server)
Desitjable coneixements de frameworks web basats en components (React, per exemple) i administració de sistemes i/o núvol.",
"Trabajar en un equipo joven, dinámico, en constante crecimiento y progreso
Formación en TDD, DDD, arquitecturas de backend escalables y entornos de alta disponibilidad
Incorporación a una empresa con fuerte base tecnológica y gran crecimiento
Trabajo remoto
Horario flexible
Oficina moderna en el centro de Barcelona
Contrato indefinido
Formación tecnológica
Ventajas de retribución flexible en comida y transporte
Salario: entre 40 y 50k anuales","2023-03-07",  false);
INSERT INTO inscripcion(alumno_id, fecha_inscripcion,estado,oferta_id)VALUES(1,'2023-01-16',1,1);
INSERT INTO inscripcion(alumno_id, fecha_inscripcion,estado,oferta_id)VALUES(2,'2022-02-23',2,2);