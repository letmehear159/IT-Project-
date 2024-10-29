CREATE TABLE `vouchers`
(
    `voucher_id`             bigint     NOT NULL AUTO_INCREMENT,
    `percent_discount`       float   DEFAULT NULL,
    `maximum_discount_money` float   DEFAULT NULL,
    `number_left`            int     DEFAULT NULL,
    `state`                  tinyint DEFAULT 1,
    `voucher_name`           varchar(6) NOT NULL,
    PRIMARY KEY (`voucher_id`),
    UNIQUE KEY `voucher_name_UNIQUE` (`voucher_name`),
    CONSTRAINT `vouchers_chk_1` CHECK (`percent_discount` >= 0),
    CONSTRAINT `vouchers_chk_2` CHECK (`maximum_discount_money` >= 0),
    CONSTRAINT `vouchers_chk_3` CHECK (`number_left` >= 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
