CREATE TABLE products (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         account_number VARCHAR(255) NOT NULL,
                         balance DECIMAL(19,2) NOT NULL,
                         product_type VARCHAR(50) NOT NULL,
                         user_id BIGINT NOT NULL,
                         FOREIGN KEY (user_id) REFERENCES users(id)
);