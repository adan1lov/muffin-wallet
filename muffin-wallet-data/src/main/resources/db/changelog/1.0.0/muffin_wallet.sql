--liquibase formatted sql

-- changeset Koshkin George:f4af988c-0326-4d02-8b16-57ca8c1deae7
CREATE TABLE IF NOT EXISTS muffin_wallet (
    id UUID PRIMARY KEY,
    balance DECIMAL NOT NULL,
    owner_name VARCHAR(40) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
)
--rollback drop table maffin_wallet;
