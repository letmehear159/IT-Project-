CREATE TABLE `manufacturers`
(
    `manufacturer_id`   bigint      NOT NULL AUTO_INCREMENT,
    `manufacturer_name` varchar(50) NOT NULL DEFAULT 'Not Set',
    `description`       varchar(255)         DEFAULT 'Not Set',
    PRIMARY KEY (`manufacturer_id`),
    UNIQUE KEY `manufacturer_name_UNIQUE` (`manufacturer_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
