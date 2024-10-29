CREATE TABLE `specifications`
(
    `spec_id`    bigint       NOT NULL AUTO_INCREMENT,
    `product_id` bigint DEFAULT NULL,
    `spec_key`   varchar(255) NOT NULL,
    `spec_value` varchar(255) NOT NULL,
    PRIMARY KEY (`spec_id`),
    KEY          `specifications_ibfk_1` (`product_id`),
    CONSTRAINT `specifications_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`) ON DELETE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
