-- We need the old data to be in a 'data' schema.
INSERT INTO "user"
select id, name, email, encrypted_password, administrator, created_at, updated_at from data.users;
SELECT setval('user_id_seq', (SELECT last_value FROM data.users_id_seq));
