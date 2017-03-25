DROP SEQUENCE IF EXISTS seq_client;
DROP SEQUENCE IF EXISTS seq_os;
DROP SEQUENCE IF EXISTS seq_phone;
DROP SEQUENCE IF EXISTS seq_phone_phone_state;
DROP SEQUENCE IF EXISTS seq_phone_state;

DROP TABLE IF EXISTS so_phone_phone_state;
DROP TABLE IF EXISTS so;
DROP TABLE IF EXISTS phone_state;
DROP TABLE IF EXISTS phone;
DROP TABLE IF EXISTS client;

DROP SCHEMA IF EXISTS service_order;

CREATE SCHEMA service_order;

CREATE SEQUENCE seq_client;
CREATE SEQUENCE seq_os;
CREATE SEQUENCE seq_phone;
CREATE SEQUENCE seq_phone_phone_state;
CREATE SEQUENCE seq_phone_state;

CREATE TABLE client
(
  client_id bigint NOT NULL,
  address varchar(255),
  business_phone varchar(255),
  home_phone varchar(255),
  name varchar(255) NOT NULL,
  PRIMARY KEY (client_id)
);

CREATE TABLE phone
(
  phone_id bigint NOT NULL,
  brand varchar(70),
  esn varchar(50),
  model varchar(50),
  client_id bigint,
  PRIMARY KEY (phone_id),
  CONSTRAINT FK_Cliente_Id FOREIGN KEY (client_id) REFERENCES client (client_id)
);

CREATE TABLE phone_state
(
  phone_state_id bigint NOT NULL,
  state varchar(50),
  PRIMARY KEY (phone_state_id)
);

CREATE TABLE so
(
  so_id bigint NOT NULL,
  approved_date date,
  date_phone_withdrawl date,
  date_so date,
  executed_service varchar(255),
  problem_found varchar(255),
  reported_problem varchar(255) NOT NULL,
  service_order_type integer,
  so_state integer,
  value numeric(19,2) NOT NULL,
  client_id bigint NOT NULL,
  PRIMARY KEY (so_id),
  FOREIGN KEY (client_id) REFERENCES client (client_id)
);

CREATE TABLE so_phone_phone_state
(
  phone_phone_state_id bigint NOT NULL,
  phone_id bigint NOT NULL,
  phone_state_id bigint NOT NULL,
  so_id bigint NOT NULL,
  PRIMARY KEY (phone_phone_state_id),
  FOREIGN KEY (phone_state_id) REFERENCES phone_state (phone_state_id),
  FOREIGN KEY (phone_id) REFERENCES phone (phone_id),
  FOREIGN KEY (so_id) REFERENCES so (so_id)
);

INSERT INTO phone_state (phone_state_id, state) VALUES (1, 'No Chip');
INSERT INTO phone_state (phone_state_id, state) VALUES (2, 'Memory Card');
INSERT INTO phone_state (phone_state_id, state) VALUES (3, 'Cover');
INSERT INTO phone_state (phone_state_id, state) VALUES (4, 'Battery');
INSERT INTO phone_state (phone_state_id, state) VALUES (5, 'Other');