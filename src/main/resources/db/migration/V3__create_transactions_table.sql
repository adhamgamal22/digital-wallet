CREATE TABLE IF NOT EXISTS transactions (
                                            id VARCHAR(50) PRIMARY KEY,
    amount DECIMAL(19, 2) NOT NULL,
    type VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL,
    description VARCHAR(255),
    sender_wallet_id VARCHAR(36),
    recipient_wallet_id VARCHAR(36),
    sender_wallet_number VARCHAR(50),
    recipient_wallet_number VARCHAR(50),
    reference_id VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_transaction_sender FOREIGN KEY (sender_wallet_id) REFERENCES wallets(id),
    CONSTRAINT fk_transaction_recipient FOREIGN KEY (recipient_wallet_id) REFERENCES wallets(id)
    );

CREATE INDEX idx_transactions_sender ON transactions(sender_wallet_id);
CREATE INDEX idx_transactions_recipient ON transactions(recipient_wallet_id);
CREATE INDEX idx_transactions_created_at ON transactions(created_at);