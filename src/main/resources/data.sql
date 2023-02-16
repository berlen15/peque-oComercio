create table articulo (
    idarticulo int PRIMARY KEY,
    descripcion varchar(200),
    stock int,
    precio double NOT NULL
);

create table usuario (
    id int PRIMARY KEY,
    nombreusuario varchar,
    email varchar,
    nombre varchar
);

create table cesta (
    idcesta int PRIMARY KEY,
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
    idventa int PRIMARY KEY,
    fecha date,
    articulo_id int,
    cantidad int,
    FOREIGN KEY (articulo_id) REFERENCES articulo(idarticulo)
);

insert into articulo (idarticulo, descripcion, stock, precio)values(1000,'Ratón ergonomico', 10, 15.3);
insert into articulo (idarticulo, descripcion, stock, precio)values(1001,'Plancha para el pelo portátil, ideal para viajes', 3, 45.3);
insert into articulo (idarticulo, descripcion, stock, precio)values(1002, 'Herramientas de teclado para PC sobremesa con luces led', 0, 25.4);
insert into articulo (idarticulo, descripcion, stock, precio)values(1003,'Mochila para portátil con cargador USB incluído', 9, 45.2);
insert into articulo (idarticulo, descripcion, stock, precio)values(1004,'Ratón especial para el descanso de las muñecas', 15,60.3);


insert into usuario (id, nombreusuario, email, nombre)values(1,'belen_1', 'belen@prueba.com', 'Belén');
insert into usuario (id, nombreusuario, email, nombre)values(2,'salvador_2', 'salvador@prueba2.com', 'Salvador');

insert into cesta (idcesta, usuario_id)values(1,1);
insert into cesta (idcesta, usuario_id)values(2,2);

insert into cesta_articulo (cesta_id, articulo_id)values(1,1000);
insert into cesta_articulo (cesta_id, articulo_id)values(1,1001);
insert into cesta_articulo (cesta_id, articulo_id)values(2,1002);
insert into cesta_articulo (cesta_id, articulo_id)values(2,1003);

insert into articulo_usuario (articulo_id, usuario_id)values(1000,1);
insert into articulo_usuario (articulo_id, usuario_id)values(1001,2);

insert into venta(idventa, articulo_id, fecha, cantidad)values(1, 1001,'2022-01-09', 2);
insert into venta(idventa, articulo_id, fecha, cantidad)values(2, 1001,'2022-01-02', 5);
insert into venta(idventa, articulo_id, fecha, cantidad)values(3, 1000,'2022-01-03', 1);
insert into venta(idventa, articulo_id, fecha, cantidad)values(4, 1000,'2022-01-07', 1);
insert into venta(idventa, articulo_id, fecha, cantidad)values(5, 1000,'2022-02-16', 1);
insert into venta(idventa, articulo_id, fecha, cantidad)values(6, 1002,'2022-02-15', 1);
insert into venta(idventa, articulo_id, fecha, cantidad)values(7, 1002,'2022-02-15', 1);
insert into venta(idventa, articulo_id, fecha, cantidad)values(8, 1002,'2022-01-31', 1);