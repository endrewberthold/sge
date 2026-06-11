CREATE TABLE parking_rate (

    id BIGSERIAL PRIMARY KEY,

    type VARCHAR(20) NOT NULL,

    amount NUMERIC(10,2) NOT NULL,

    active BOOLEAN NOT NULL
);