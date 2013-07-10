-- Table: dividend

-- DROP TABLE dividend;

CREATE TABLE dividend
(
  id_price bigint,
  date date NOT NULL,
  ticker character(8) NOT NULL,
  amount double precision,
  CONSTRAINT dividend_pkey PRIMARY KEY (date, ticker),
  CONSTRAINT dividend_price_fkey FOREIGN KEY (id_price)
      REFERENCES price (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE dividend OWNER TO postgres;

