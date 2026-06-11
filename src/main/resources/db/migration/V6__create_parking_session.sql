CREATE TABLE parking_sessions (

    id BIGSERIAL PRIMARY KEY,

    vehicle_id BIGINT NOT NULL,

    entry_time TIMESTAMP NOT NULL,

    exit_time TIMESTAMP,

    total_amount NUMERIC(10,2),

    status VARCHAR(30) NOT NULL,

    CONSTRAINT fk_session_vehicle
        FOREIGN KEY (vehicle_id)
        REFERENCES vehicles(id)
);