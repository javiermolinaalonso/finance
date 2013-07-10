-- Table: price

-- DROP TABLE price;

CREATE TABLE price
(
  id bigserial NOT NULL,
  date date,
  previous double precision,
  "current" double precision NOT NULL,
  adj_close double precision,
  day_max double precision,
  day_min double precision,
  volume double precision,
  open double precision,
  ticker character(8),
  CONSTRAINT price_fkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE price OWNER TO postgres;

