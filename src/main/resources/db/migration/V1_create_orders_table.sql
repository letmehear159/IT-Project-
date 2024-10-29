CREATE TABLE `orders`
(
    `order_id`         bigint       NOT NULL AUTO_INCREMENT,
    `user_id`          bigint                DEFAULT NULL,
    `customer_name`    varchar(30)  NOT NULL DEFAULT 'undefined_User_name',
    `shipping_address` varchar(255) NOT NULL,
    `order_date`       datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `receive_date`     datetime     NOT NULL,
    `shipping_state`   varchar(50)  NOT NULL,
    `payment_method`   varchar(30)  NOT NULL,
    `shipping_method`  varchar(30)  NOT NULL,
    `phone_number`     varchar(20)  NOT NULL,
    `note`             varchar(255)          DEFAULT NULL,
    `total_price`      float                 DEFAULT NULL,
    `payment_status`   varchar(45)  NOT NULL,
    PRIMARY KEY (`order_id`),
    KEY                `order_user_fk_idx` (`user_id`),
    CONSTRAINT `order_user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE,
    CONSTRAINT `orders_chk_1` CHECK (`total_price` >= 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
