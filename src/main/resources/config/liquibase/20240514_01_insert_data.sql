
insert into courier (id, name,status,transport, x, y)
values ('851413ff-34a2-4f14-8678-56e7fea85af8', 'Courier 1', 2, 1, 1, 1);
insert into courier (id, name, status, transport, x, y)
values ('b8023780-96e3-409a-b4e0-0b771eb1b30f', 'Courier 2', 1, 2, 1, 1);
insert into courier(id, name, status, transport, x, y)
values ('bf79a004-56d7-4e5f-a21c-0a9e5e08d10d', 'Courier 3', 0, 1, 5, 6);
insert into courier(id, name, status, transport, x, y)
values ('070b2db6-e553-4abf-bebc-345a115cd277', 'Courier 4', 1, 2, 5, 7);
insert into courier(id, name, status, transport, x, y)
values ('4bbcb112-253f-457f-bb61-4bc52cf0357c', 'Courier 5', 2, 3, 3, 1);

insert into delivery_order (id, status, weight_value, x, y)
values('4931cb3f-80e3-40a2-880a-9d5e13cb6151', 0, 3, 1, 2);
insert into delivery_order (id, status, weight_value, x, y)
values('f10eb1af-eac5-42a8-80f5-b104b41551c3', 2, 5, 2, 1);
insert into delivery_order (id, status, weight_value, x, y)
values('977a4941-2baa-4a36-9a7f-6ce587064c71', 2, 4, 1 ,1);


insert into delivery_order(id, courier_id, status, weight_value, x, y)
values ('49ee1822-6703-4103-a2c6-9b2592a3f7f6', 'bf79a004-56d7-4e5f-a21c-0a9e5e08d10d', 1, 4, 5, 7);

insert into delivery_order(id, courier_id, status, weight_value, x, y)
values ('29f92517-e38c-4579-b957-a16b576be2bc', '070b2db6-e553-4abf-bebc-345a115cd277', 1, 4, 1, 4);

insert into delivery_order(id, courier_id, status, weight_value, x, y)
values ('3a5c19de-072a-4ad3-b303-afb2c8b74e93', '4bbcb112-253f-457f-bb61-4bc52cf0357c', 1, 4, 1, 4);