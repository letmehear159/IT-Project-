CREATE TABLE `permissions` (
                               `permission_id` varchar(50) NOT NULL,
                               `description` varchar(255) DEFAULT '',
                               `role_id` varchar(50) DEFAULT NULL,
                               PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
