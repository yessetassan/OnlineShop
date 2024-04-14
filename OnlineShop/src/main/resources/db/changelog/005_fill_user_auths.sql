INSERT INTO t_user_auth (password, user_id, role_id, token, expired_date, is_expired)
VALUES
    ('$2a$10$/oPeyKVyKYISPJ4YVvdBx.yydKLEHC9BNaUMaTDOQnsNGsu3wzRQK', 1, 3, 'token1', NOW(), TRUE),
    ('$2a$10$/oPeyKVyKYISPJ4YVvdBx.yydKLEHC9BNaUMaTDOQnsNGsu3wzRQK', 2, 2, 'token2', NOW(), TRUE),
    ('$2a$10$/oPeyKVyKYISPJ4YVvdBx.yydKLEHC9BNaUMaTDOQnsNGsu3wzRQK', 3, 1, 'token3', NOW(), TRUE),
    ('$2a$10$/oPeyKVyKYISPJ4YVvdBx.yydKLEHC9BNaUMaTDOQnsNGsu3wzRQK', 4, 1, 'token4', NOW(), TRUE),
    ('$2a$10$/oPeyKVyKYISPJ4YVvdBx.yydKLEHC9BNaUMaTDOQnsNGsu3wzRQK', 5, 1, 'token5', NOW(), TRUE);