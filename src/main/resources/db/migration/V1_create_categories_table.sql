CREATE TABLE `categories`
(
    `category_id`   bigint      NOT NULL AUTO_INCREMENT,
    `category_name` varchar(30) NOT NULL,
    `description`   varchar(255) DEFAULT '',
    PRIMARY KEY (`category_id`),
    UNIQUE KEY `category_name` (`category_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
