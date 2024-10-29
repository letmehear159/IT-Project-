CREATE TABLE `product_images`
(
    `id`         bigint       NOT NULL AUTO_INCREMENT,
    `product_id` bigint       NOT NULL,
    `image_url`  varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    KEY          `product_image_fk_idx` (`product_id`),
    CONSTRAINT `product_image_fk` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
