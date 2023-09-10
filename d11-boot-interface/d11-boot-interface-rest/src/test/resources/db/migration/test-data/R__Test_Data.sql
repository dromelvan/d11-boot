-- User
INSERT INTO application_user (id, name, email, encrypted_password, administrator)
    VALUES(1, 'User', 'user@email.com', '$2a$12$MgLkW.T6F0iqYRbdwHL9LOGxmt2UtI5hgVr.DA7qho7iuMVFdtQha', false);
INSERT INTO application_user (id, name, email, encrypted_password, administrator)
    VALUES(2, 'Administrator', 'administrator@email.com', '$2a$12$MgLkW.T6F0iqYRbdwHL9LOGxmt2UtI5hgVr.DA7qho7iuMVFdtQha', true);

-- Season
INSERT INTO season (id, name, d11_team_budget, d11_team_max_transfers, status, date) VALUES(1, '2020-2021', 600, 13, 3, '2020-01-01');
INSERT INTO season (id, name, d11_team_budget, d11_team_max_transfers, status, date) VALUES(2, '2021-2022', 600, 13, 1, '2021-01-01');
