create table public.outbox (
    id UUID primary key,
    event_type int NOT NULL,
    event_payload VARCHAR(255)
);