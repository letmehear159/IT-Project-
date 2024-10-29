CREATE TABLE `users`
(
    `user_id`          bigint       NOT NULL AUTO_INCREMENT,
    `user_name`        varchar(50)  NOT NULL,
    `password`         varchar(200) NOT NULL,
    `state`            tinyint      NOT NULL DEFAULT 1, -- Chỉnh sửa phần này
    `date_created`     datetime     NOT NULL,
    `date_modified`    datetime              DEFAULT NULL,
    `full_name`        varchar(30)  NOT NULL,
    `dob`              datetime     NOT NULL,
    `phone_number`     varchar(20)  NOT NULL,
    `email`            varchar(50)           DEFAULT NULL,
    `shopping_cart_id` bigint                DEFAULT NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `user_name` (`user_name`),
    UNIQUE KEY `email` (`email`),
    KEY                `cart_user_fk_idx` (`shopping_cart_id`),
    CONSTRAINT `cart_user_fk` FOREIGN KEY (`shopping_cart_id`) REFERENCES `shoppingcarts` (`cart_id`)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
