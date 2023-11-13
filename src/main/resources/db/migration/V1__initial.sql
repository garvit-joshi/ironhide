CREATE TABLE audit
(
    id            BIGSERIAL PRIMARY KEY,
    uuid          VARCHAR(128) NOT NULL,
    application   VARCHAR(32) NOT NULL,
    caller        VARCHAR(32) NOT NULL,
    audit_context VARCHAR(8)  NOT NULL,
    time          TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    auditor       VARCHAR(64) NOT NULL,
    blob          JSONB,
    metadata      TEXT
);
