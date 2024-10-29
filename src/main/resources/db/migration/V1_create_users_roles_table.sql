CREATE TABLE `users_roles`
(
    `user_id` bigint      NOT NULL,
    `role_id` varchar(50) NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`),
    KEY       `users_roles_ibfk_2_idx` (`role_id`),
    CONSTRAINT `users_roles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
    CONSTRAINT `users_roles_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
