CREATE TABLE `notifications`
(
    `notification_id` bigint NOT NULL AUTO_INCREMENT,
    `user_id`         bigint       DEFAULT NULL,
    `product_id`      bigint       DEFAULT NULL,
    `content`         varchar(255) DEFAULT '',
    PRIMARY KEY (`notification_id`),
    KEY               `notifi_user_fk_idx` (`user_id`),
    KEY               `notifi_product_fk_idx` (`product_id`),
    CONSTRAINT `notifi_product_fk` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`),
    CONSTRAINT `notifi_user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
