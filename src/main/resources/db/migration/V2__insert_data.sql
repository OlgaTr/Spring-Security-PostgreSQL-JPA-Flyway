INSERT INTO contributors(first_name, last_name)
VALUES
       ('Hannah','Gadsby'),
       ('Iliza','Shlesinger'),
       ('Jen','Kirkman'),
       ('Melissa', 'McCarthy'),
       ('Mindy','Kaling'),
       ('Rebel','Wilson'),
       ('Taylor','Tomlinson');

INSERT INTO groups(name)
VALUES
        ('Comedians'),
        ('Writers'),
        ('Actresses');

INSERT INTO users(username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled)
VALUES
       ('admin', '$2a$10$LixnQ9CmcLLZ2hkK0fKrDeMWhhx3Zy7w6jK/3Z9anKCqjP0s3EQ7O', true, true, true, true),
       ('user', '$2a$10$zmZ4HxbafwdERGjyH2K0oeYD.w0VT6PDJ4nuSFLGsyxPBq01T3l/S', true, true, true, true);

INSERT INTO authorities(name)
VALUES
       ('admin'),
       ('user');

INSERT INTO users_authorities(user_id, authority_id)
VALUES
       (1, 1),
       (2, 2);