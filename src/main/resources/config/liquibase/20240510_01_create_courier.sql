create table if not exists public.courier (
 	id uuid PRIMARY KEY,
	status int NOT NULL,
	transport int NOT NULL,
	x int NULL,
	y int NULL,
	name varchar(255) NULL,
	CONSTRAINT courier_status_check CHECK (((status >= 0) AND (status <= 2))),
	CONSTRAINT courier_transport_check CHECK (((transport >= 0) AND (transport <= 3)))
);

