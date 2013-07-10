-- Table: split

-- DROP TABLE split;

CREATE TABLE split
(
  date date NOT NULL,
  ticker character(8) NOT NULL,
  original_shares integer,
  new_shares integer,
  id_price bigint,
  CONSTRAINT split_pkey PRIMARY KEY (date, ticker),
  CONSTRAINT split_price_fkey FOREIGN KEY (id_price)
      REFERENCES price (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE split OWNER TO postgres;

