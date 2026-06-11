CREATE TABLE client (

    id BIGSERIAL PRIMARY KEY,

    name VARCHAR(255) NOT NULL,

    document VARCHAR(30) NOT NULL UNIQUE,

    phone VARCHAR(30),

    user_id BIGINT UNIQUE NOT NULL,

    CONSTRAINT fk_client_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
);