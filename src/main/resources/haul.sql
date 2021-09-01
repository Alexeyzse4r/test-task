DROP TABLE IF EXISTS client;
CREATE TABLE client (
client_id SERIAL,
name_client varchar(100),
phone varchar(30),
email varchar(30),
passport varchar(30),
PRIMARY KEY (client_id)
);

DROP TABLE IF EXISTS offer;
CREATE TABLE offer (
offer_id SERIAL,
offer_client_id INTEGER,
offer_credit_id INTEGER,
summ INTEGER,
mounth_num INTEGER,
summ_every_mounth INTEGER,
summ_itog INTEGER,
PRIMARY KEY (offer_id)
);

DROP TABLE IF EXISTS credit;
CREATE TABLE credit (
credit_id SERIAL,
limit_credit INTEGER,
rate INTEGER,
PRIMARY KEY (credit_id)
);

