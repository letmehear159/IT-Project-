CREATE TABLE `shipping_methods`
(
    `shipping_name` varchar(40) NOT NULL,
    `description`   varchar(255) DEFAULT 'not set',
    PRIMARY KEY (`shipping_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
