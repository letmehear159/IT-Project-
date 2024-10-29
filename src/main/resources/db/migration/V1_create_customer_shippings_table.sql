CREATE TABLE `customer_shippings`
(
    `cus_ship_id`    bigint       NOT NULL AUTO_INCREMENT,
    `user_id`        bigint       NOT NULL,
    `receiver_name`  varchar(40)  NOT NULL DEFAULT 'Not Set',
    `phone_number`   varchar(30)  NOT NULL DEFAULT 'Not Set',
    `city`           varchar(20)  NOT NULL DEFAULT 'not set',
    `detail_address` varchar(255) NOT NULL DEFAULT 'not set',
    PRIMARY KEY (`cus_ship_id`),
    KEY              `cus_ship_user_fk_idx` (`user_id`),
    CONSTRAINT `cus_ship_user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
