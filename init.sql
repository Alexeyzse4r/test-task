CREATE TABLE client
(
    Id SERIAL PRIMARY KEY,
    _Name CHARACTER VARYING(100),
	Phone CHARACTER VARYING(12),
    Email CHARACTER VARYING(30),
	Passport CHARACTER VARYING(12),
	Credit_offer CHARACTER VARYING(1000)
);
CREATE TABLE credit
(
    Id SERIAL PRIMARY KEY,
    _Limit INTEGER,
	Stavka INTEGER
);