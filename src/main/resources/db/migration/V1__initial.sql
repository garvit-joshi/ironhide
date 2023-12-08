CREATE SEQUENCE public.audit_id_seq START WITH 1 INCREMENT BY 1 MINVALUE 1 NO MAXVALUE CACHE 1;

CREATE TABLE public.audit
(
    id            BIGINT PRIMARY KEY DEFAULT nextval('public.audit_id_seq'::regclass) NOT NULL,
    uuid          CHARACTER VARYING(128)                                              NOT NULL UNIQUE,
    application   CHARACTER VARYING(32)                                               NOT NULL,
    caller        CHARACTER VARYING(32)                                               NOT NULL,
    audit_context CHARACTER VARYING(8)                                                NOT NULL,
    audit_time    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    auditor       CHARACTER VARYING(64)                                               NOT NULL,
    blob          JSONB,
    metadata      TEXT
);

