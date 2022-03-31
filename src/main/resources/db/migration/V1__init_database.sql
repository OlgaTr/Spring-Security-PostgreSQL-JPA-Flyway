CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE contributors (
    contributor_id BIGSERIAL PRIMARY KEY NOT NULL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL
);

CREATE TABLE groups (
    group_id BIGSERIAL PRIMARY KEY NOT NULL,
    name TEXT NOT NULL
);

CREATE TABLE contributors_groups(
    contributor_id BIGINT,
    group_id BIGINT
);

CREATE TABLE users(
    user_id BIGSERIAL PRIMARY KEY NOT NULL,
    username TEXT,
    password TEXT,
    account_non_expired BOOLEAN,
    account_non_locked BOOLEAN,
    credentials_non_expired BOOLEAN,
    enabled BOOLEAN
);

CREATE TABLE authorities(
    authority_id BIGSERIAL PRIMARY KEY NOT NULL,
    name TEXT
);

CREATE TABLE users_authorities(
    user_id BIGINT,
    authority_id BIGINT
);