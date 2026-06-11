CREATE TABLE vehicles (

    id BIGSERIAL PRIMARY KEY,

    plate VARCHAR(10) NOT NULL UNIQUE,

    mark VARCHAR(100),

    model VARCHAR(100),

    color VARCHAR(50),

    client_id BIGINT NOT NULL,

    CONSTRAINT fk_vehicle_client
        FOREIGN KEY (client_id)
        REFERENCES clients(id)
);