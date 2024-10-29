CREATE TABLE `products`
(
    `product_id`      bigint      NOT NULL AUTO_INCREMENT,
    `date_created`    datetime    NOT NULL,
    `date_modified`   datetime             DEFAULT NULL,
    `category_id`     bigint               DEFAULT NULL,
    `product_name`    varchar(30) NOT NULL DEFAULT 'UnNamedProduct',
    `price`           float                DEFAULT NULL,
    `manufacturer_id` bigint               DEFAULT NULL,
    `thumbnail`       varchar(300)         DEFAULT '',
    `state`           tinyint     NOT NULL DEFAULT 1,
    `description`     varchar(255)         DEFAULT NULL,
    PRIMARY KEY (`product_id`),
    KEY               `products_ibfk_2` (`manufacturer_id`),
    KEY               `products_ibfk_1` (`category_id`),
    CONSTRAINT `products_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`),
    CONSTRAINT `products_ibfk_2` FOREIGN KEY (`manufacturer_id`) REFERENCES `manufacturers` (`manufacturer_id`),
    CONSTRAINT `products_chk_1` CHECK (`price` >= 0)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
