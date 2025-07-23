--liquibase formatted sql

--changeset Koshkin George:ad682896-3de3-48a9-9fb4-b568939abeaf
CREATE TABLE IF NOT EXISTS muffin_transaction (
    id UUID PRIMARY KEY,
    amount DECIMAL,
    from_muffin_wallet_id UUID REFERENCES muffin_wallet(id) NOT NULL,
    to_muffin_wallet_id UUID REFERENCES muffin_wallet(id) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);
--rollback drop table maffin_transaction;