DROP SCHEMA IF EXISTS service_order;

CREATE SCHEMA service_order;

DROP SEQUENCE IF EXISTS service_order.seq_client;
DROP SEQUENCE IF EXISTS service_order.seq_os;
DROP SEQUENCE IF EXISTS service_order.seq_phone;
DROP SEQUENCE IF EXISTS service_order.seq_state;

CREATE SEQUENCE service_order.seq_client;
CREATE SEQUENCE service_order.seq_os;
CREATE SEQUENCE service_order.seq_phone;
CREATE SEQUENCE service_order.seq_state;

DROP TABLE IF EXISTS service_order.state_phonestate;
DROP TABLE IF EXISTS service_order.phone_phonestate;
DROP TABLE IF EXISTS service_order.phonestate;
DROP TABLE IF EXISTS service_order.state;
DROP TABLE IF EXISTS service_order.phone;
DROP TABLE IF EXISTS service_order.client;
DROP TABLE IF EXISTS service_order.service_order;

CREATE TABLE service_order.client
(
  client_id bigint NOT NULL,
  address varchar(255),
  business_phone varchar(255),
  home_phone varchar(255),
  name varchar(255) NOT NULL,
  PRIMARY KEY (client_id)
);

CREATE TABLE service_order.state
(
  state_id integer NOT NULL,
  situation varchar(255),
  PRIMARY KEY (state_id)
);

CREATE TABLE service_order.phone
(
  phone_id bigint NOT NULL,
  brand varchar(255),
  esn varchar(255),
  model varchar(255),
  phone_state integer,
  client_id bigint,
  PRIMARY KEY (phone_id),
  CONSTRAINT FK_Cliente_Id FOREIGN KEY (client_id) REFERENCES service_order.client (client_id)
);

CREATE TABLE service_order.phone_state
(
  phone_state_id integer NOT NULL,
  phone_id bigint,
  state_id integer,
  PRIMARY KEY (phone_state_id),
  FOREIGN KEY (phone_id) REFERENCES service_order.phone (phone_id),
  FOREIGN KEY (state_id) REFERENCES service_order.state (state_id)
);

CREATE TABLE service_order.phone_phone_state
(
  phone_phone_id bigint NOT NULL,
  state_phone_state_id integer NOT NULL,
  FOREIGN KEY (state_phone_state_id) REFERENCES service_order.phonestate (phone_state_id),
  FOREIGN KEY (phone_phone_id) REFERENCES service_order.phone (phone_id)
);

CREATE TABLE service_order.service_order
(
  id bigint NOT NULL,
  approveddate date,
  datephonewithdrawl date,
  dateso date,
  executedservice varchar(255),
  problemfound varchar(255),
  reportedproblem varchar(255) NOT NULL,
  serviceordertype integer,
  sostate integer,
  value numeric(19,2) NOT NULL,
  client_id bigint NOT NULL,
  phone_id bigint NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (phone_id) REFERENCES service_order.phone (phone_id),
  FOREIGN KEY (client_id) REFERENCES service_order.client (client_id)
);

CREATE TABLE service_order.state_phone_state
(
  state_state_id integer NOT NULL,
  phonestates_phone_state_id integer NOT NULL,
  FOREIGN KEY (state_state_id) REFERENCES service_order.state (state_id),
  FOREIGN KEY (phonestates_phone_state_id) REFERENCES service_order.phonestate (phone_state_id),
  UNIQUE (phonestates_phone_state_id)
);