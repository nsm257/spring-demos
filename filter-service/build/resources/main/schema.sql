CREATE TABLE IF NOT EXISTS person (
    id                  BIGINT              NOT NULL    AUTO_INCREMENT,
    created_on          DATETIME            DEFAULT     CURRENT_TIMESTAMP,
    updated_on          DATETIME            DEFAULT     CURRENT_TIMESTAMP   ON UPDATE CURRENT_TIMESTAMP,
    created_by          VARCHAR(100)        NOT NULL,
    updated_by          VARCHAR(100)        NOT NULL,
    email_id            VARCHAR(100)        NOT NULL    UNIQUE,
    name                VARCHAR(100)        NOT NULL,
    address_id          VARCHAR(100)        NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS address (
    id                  BIGINT              NOT NULL    AUTO_INCREMENT,
    created_on          DATETIME            DEFAULT     CURRENT_TIMESTAMP,
    updated_on          DATETIME            DEFAULT     CURRENT_TIMESTAMP   ON UPDATE CURRENT_TIMESTAMP,
    created_by          VARCHAR(100)        NOT NULL,
    updated_by          VARCHAR(100)        NOT NULL,
    pincode             VARCHAR(7)          NOT NULL,
    city                VARCHAR(50)         NOT NULL,
    state               VARCHAR(50)         NOT NULL,
    ph_number_id        VARCHAR(50)         NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS phone_number (
    id                  BIGINT              NOT NULL    AUTO_INCREMENT,
    created_on          DATETIME            DEFAULT     CURRENT_TIMESTAMP,
    updated_on          DATETIME            DEFAULT     CURRENT_TIMESTAMP   ON UPDATE CURRENT_TIMESTAMP,
    created_by          VARCHAR(100)        NOT NULL,
    updated_by          VARCHAR(100)        NOT NULL,
    region              VARCHAR(5)          NOT NULL,
    contact             VARCHAR(10)         NOT NULL,

    PRIMARY KEY (id)
);