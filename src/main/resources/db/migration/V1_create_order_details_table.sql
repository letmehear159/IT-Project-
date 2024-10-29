CREATE TABLE `order_details`
(
    `order_detail_id` bigint NOT NULL AUTO_INCREMENT,
    `order_id`        bigint DEFAULT NULL,
    `product_id`      bigint DEFAULT NULL,
    `price`           bigint DEFAULT NULL,
    `quantity`        int    DEFAULT NULL,
    `total_price`     float  DEFAULT NULL,
    `cart_id`         bigint DEFAULT NULL,
    PRIMARY KEY (`order_detail_id`),
    KEY               `order_detail_order_fk_idx` (`order_id`),
    KEY               `order_detail_product_fk_idx` (`product_id`),
    KEY               `order_detail_cart_fk_idx` (`cart_id`),
    CONSTRAINT `order_detail_cart_fk` FOREIGN KEY (`cart_id`) REFERENCES `shoppingcarts` (`cart_id`),
    CONSTRAINT `order_detail_order_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
    CONSTRAINT `order_detail_product_fk` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`),
    CONSTRAINT `order_details_chk_1` CHECK (`price` >= 0),
    CONSTRAINT `order_details_chk_2` CHECK (`quantity` >= 0),
    CONSTRAINT `order_details_chk_3` CHECK (`total_price` >= 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
