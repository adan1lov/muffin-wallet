-- --liquibase formatted sql
-- CREATE TABLE IF NOT EXISTS muffin_transaction (
--     id UUID PRIMARY KEY,
--     amount MONEY CHECK(amount >= 0::money),
--     from_account UUID REFERENCES muffin_wallet(id) NOT NULL,
--     to_account UUID REFERENCES muffin_wallet(id) NOT NULL,
--     created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
-- );

SELECT * FROM muffin_wallet;