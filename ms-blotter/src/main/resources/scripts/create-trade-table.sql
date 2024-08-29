CREATE TABLE trade (
    trade_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    side VARCHAR(10) NOT NULL,
    ticket VARCHAR(20) NOT NULL,
    quantity DOUBLE NOT NULL,
    price DOUBLE NOT NULL,
    pu DOUBLE NOT NULL,
    transact_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- Sets default value to current timestamp
    status VARCHAR(20)
);

select * from trade;