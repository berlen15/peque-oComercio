create table articulo (
    idarticulo int PRIMARY KEY,
    descripcion varchar(200),
    stock int,
    referencia varchar,
    precio double NOT NULL
);

create table usuario (
    id int  PRIMARY KEY,
    username varchar,
    email varchar,
    nombre varchar
);

create table cesta (
    idcesta int AUTO_INCREMENT PRIMARY KEY,
    usuario_id int,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

create table cesta_articulo(
    cesta_id int,
    articulo_id int,
    FOREIGN KEY (cesta_id) REFERENCES cesta(idcesta),
    FOREIGN KEY (articulo_id) REFERENCES articulo(idarticulo)
);

create table articulo_usuario(
    articulo_id int,
    usuario_id int,
    FOREIGN KEY (articulo_id) REFERENCES articulo(idarticulo),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

create table venta(
    idventa int AUTO_INCREMENT  PRIMARY KEY,
    fecha date,
    articulo_id int,
    importe double,
    tarjeta_bancaria varchar,
    usuario_id int,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (articulo_id) REFERENCES articulo(idarticulo)
);



insert into articulo (idarticulo, descripcion, stock, precio, referencia)values(1000,'Ratón ergonomico', 5, 15.3, 'REF1');
insert into articulo (idarticulo, descripcion, stock, precio, referencia)values(1001,'Plancha para el pelo portátil, ideal para viajes', 1, 45.3, 'REF2');
insert into articulo (idarticulo, descripcion, stock, precio, referencia)values(1002, 'Herramientas de teclado para PC sobremesa con luces led', 0, 25.4, 'REF3');
insert into articulo (idarticulo, descripcion, stock, precio, referencia)values(1003,'Mochila para portátil con cargador USB incluído', 0, 45.2, 'REF4');
insert into articulo (idarticulo, descripcion, stock, precio, referencia)values(1004,'Ratón especial para el descanso de las muñecas', 15,60.3, 'REF5');


insert into usuario (id, username, email, nombre)values(1,'belen', 'belen@prueba.com', 'Belén');
insert into usuario (id, username, email, nombre)values(2,'salvador', 'salvador@prueba2.com', 'Salvador');

insert into cesta (usuario_id)values(2);

--insert into cesta_articulo (cesta_id, articulo_id)values(2,1002);
--insert into cesta_articulo (cesta_id, articulo_id)values(2,1003);

--insert into articulo_usuario (articulo_id, usuario_id)values(1000,1);
--insert into articulo_usuario (articulo_id, usuario_id)values(1001,2);

--insert into venta(idventa, articulo_id, fecha, importe, usuario_id, tarjeta_bancaria)values(1,1001,'2023-01-09',45.3,1,'TARJETA1');
--insert into venta(idventa, articulo_id, fecha, importe, usuario_id, tarjeta_bancaria)values(2,1002,'2023-01-03',15.3,1,'TARJETA1');
insert into venta(articulo_id, fecha, importe, usuario_id, tarjeta_bancaria)values(1000,'2023-02-11',15.3,1,'TARJETA2');
insert into venta(articulo_id, fecha, importe, usuario_id, tarjeta_bancaria)values(1003,'2023-02-17',45.2,2,'TARJETA1');

