delete from delivery_order;
delete from courier;

insert into courier(id, name, status, transport, x, y)
values ('bf79a004-56d7-4e5f-a21c-0a9e5e08d10d', 'Courier1', 2, 1, 5, 6);
insert into delivery_order(id, courier_id, status, weight_value, x, y)
values ('49ee1822-6703-4103-a2c6-9b2592a3f7f6', 'bf79a004-56d7-4e5f-a21c-0a9e5e08d10d', 1, 4, 5, 7);

insert into courier(id, name, status, transport, x, y)
values ('070b2db6-e553-4abf-bebc-345a115cd277', 'Courier2', 2, 2, 5, 7);
insert into delivery_order(id, courier_id, status, weight_value, x, y)
values ('29f92517-e38c-4579-b957-a16b576be2bc', '070b2db6-e553-4abf-bebc-345a115cd277', 1, 4, 1, 4);

insert into courier(id, name, status, transport, x, y)
values ('4bbcb112-253f-457f-bb61-4bc52cf0357c', 'Courier3', 2, 3, 3, 1);
insert into delivery_order(id, courier_id, status, weight_value, x, y)
values ('3a5c19de-072a-4ad3-b303-afb2c8b74e93', '4bbcb112-253f-457f-bb61-4bc52cf0357c', 1, 4, 1, 4);