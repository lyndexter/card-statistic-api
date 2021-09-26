-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2021-09-26 14:44:37.638

drop database if exists  monobank_client_db;
Create Database monobank_client_db;
use  monobank_client_db;

-- tables
-- Table: transaction
CREATE TABLE transaction (
    id varchar(50) NOT NULL,
    time int NOT NULL,
    description text NOT NULL,
    mcc int NOT NULL,
    hold bool NOT NULL,
    amount int NOT NULL,
    operation_amount int NOT NULL,
    currency_code int NOT NULL,
    commission_rate int NOT NULL,
    cashback_amount int NOT NULL,
    balance int NOT NULL,
    comment text NULL,
    receipt_id varchar(50) NULL,
    counter_iban int NULL,
    original_mcc int NOT NULL,
    CONSTRAINT transaction_pk PRIMARY KEY (id)
);

-- End of file.

