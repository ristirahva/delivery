CREATE TABLE public.delivery_order (
	id uuid PRIMARY KEY,
	status int NOT NULL,
	weight_value int NOT NULL,
	x int NOT NULL,
	y int NOT NULL,
	courier_id uuid,
	CONSTRAINT delivery_order_status_check CHECK (((status >= 0) AND (status <= 2)))
);
