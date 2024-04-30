delete from delivery_order;
delete from courier;

insert into delivery_order (id, status, x, y, weight_value) values('f46b347e-198f-403c-a04d-a1d00e5632be', 0, 1, 1, 3);
insert into delivery_order (id, status, x, y, weight_value) values('f2959d3e-aadd-470c-9530-c60f3843300a', 0, 1, 1, 9);
insert into delivery_order (id, status, x, y, weight_value) values('3e7d532f-6a53-406f-829a-b0c9f6a3208a', 0, 2, 9, 3);
insert into delivery_order (id, status, x, y, weight_value) values('f1df1fd4-228e-46f3-ba04-2661583da754', 0, 7, 2, 3);
insert into delivery_order (id, status, x, y, weight_value) values('303c5141-7c46-4836-ab3c-027e1cbe075a', 0, 8, 1, 3);

insert into courier (x,y,name,status,transport, id) values (1, 7,'Courier 1', 1, 0, 'bf79a004-56d7-4e5f-a21c-0a9e5e08d10d');
insert into courier (x,y,name,status,transport, id) values (3, 4,'Courier 2', 1, 1, '407adabd-dc4e-405b-9ee9-c324154a36c4');
insert into courier (x,y,name,status,transport, id) values (6, 3,'Courier 3', 1, 2, '0de247d3-3de7-45d5-98eb-114c721ff6d8');
insert into courier (x,y,name,status,transport, id) values (9, 8,'Courier 4', 1, 3, '407f68be-5adf-4e72-81bc-b1d8e9574cf8');