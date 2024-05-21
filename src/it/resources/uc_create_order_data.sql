delete from courier;
insert into public.courier (x,y,name,status,transport, id) values (1, 1,'Courier 1', 2, 1, '851413ff-34a2-4f14-8678-56e7fea85af8');
insert into public.courier (x,y,name,status,transport, id) values (1, 1,'Courier 2', 2, 2, 'b8023780-96e3-409a-b4e0-0b771eb1b30f');

delete from delivery_order;
insert into delivery_order (id, status, x, y, weight_value) values('4931cb3f-80e3-40a2-880a-9d5e13cb6151', 0, 1, 1, 3);
insert into delivery_order (id, status, x, y, weight_value, courier_id) values('2a053bdb-c027-42ab-a0e4-0f2b67e68c7b', 1, 1, 1, 9, '851413ff-34a2-4f14-8678-56e7fea85af8');
insert into delivery_order (id, status, x, y, weight_value) values('f10eb1af-eac5-42a8-80f5-b104b41551c3', 2, 2, 1, 5);
insert into delivery_order (id, status, x, y, weight_value) values('977a4941-2baa-4a36-9a7f-6ce587064c71', 2, 1, 1, 4);
insert into delivery_order (id, status, x, y, weight_value, courier_id) values('d5de0c02-fabb-47fe-bb50-bfc7aa1c75d2', 1, 2, 1, 2, 'b8023780-96e3-409a-b4e0-0b771eb1b30f');