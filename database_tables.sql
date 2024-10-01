CREATE TABLE `tb_activities` (
  `id_activity` int unsigned NOT NULL AUTO_INCREMENT,
  `activity_type` text NOT NULL,
  `ds_observation` text NOT NULL,
  `is_activity_done` tinyint(1) NOT NULL,
  `dt_creation` timestamp NOT NULL,
  PRIMARY KEY (`id_activity`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `tb_change_history` (
  `id_change_history` int unsigned NOT NULL AUTO_INCREMENT,
  `id_user` int unsigned NOT NULL,
  `change_type` text NOT NULL,
  `change_object` json NOT NULL,
  `original_object` json NOT NULL,
  `ds_observation` text NOT NULL,
  `dt_of_change` timestamp NOT NULL,
  PRIMARY KEY (`id_change_history`),
  KEY `FK_UserChangeHistory` (`id_user`),
  CONSTRAINT `FK_UserChangeHistory` FOREIGN KEY (`id_user`) REFERENCES `tb_users` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `tb_flowers` (
  `id_flower` int unsigned NOT NULL AUTO_INCREMENT,
  `id_user` int unsigned NOT NULL,
  `ds_temperature` varchar(50) NOT NULL,
  `flower_type` varchar(100) NOT NULL,
  `is_flower_alive` tinyint(1) NOT NULL,
  `dt_creation` timestamp NOT NULL,
  PRIMARY KEY (`id_flower`),
  KEY `FK_UserFlower` (`id_user`),
  CONSTRAINT `FK_UserFlower` FOREIGN KEY (`id_user`) REFERENCES `tb_users` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `tb_hives` (
  `id_hive` int unsigned NOT NULL AUTO_INCREMENT,
  `id_user` int unsigned NOT NULL,
  `cod_hive` text NOT NULL,
  `nr_weight` double NOT NULL,
  `st_hive` text NOT NULL,
  `dt_creation` timestamp NOT NULL,
  PRIMARY KEY (`id_hive`),
  KEY `FK_UserHive` (`id_user`),
  CONSTRAINT `FK_UserHive` FOREIGN KEY (`id_user`) REFERENCES `tb_users` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `tb_hives_activities` (
  `id_hives_activities` int unsigned NOT NULL AUTO_INCREMENT,
  `id_activity` int unsigned NOT NULL,
  `id_hive` int unsigned NOT NULL,
  `dt_hives_activities` timestamp NOT NULL,
  PRIMARY KEY (`id_hives_activities`),
  KEY `FK_ActivityHivesActivity` (`id_activity`),
  KEY `FK_HivesActivity` (`id_hive`),
  CONSTRAINT `FK_ActivityHivesActivity` FOREIGN KEY (`id_activity`) REFERENCES `tb_activities` (`id_activity`),
  CONSTRAINT `FK_HivesActivity` FOREIGN KEY (`id_hive`) REFERENCES `tb_hives` (`id_hive`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `tb_hives_location` (
  `id_location` int unsigned NOT NULL AUTO_INCREMENT,
  `id_hive` int unsigned NOT NULL,
  `ds_lot` text NOT NULL,
  `dt_creation` timestamp NOT NULL,
  PRIMARY KEY (`id_location`),
  KEY `FK_HiveLocation` (`id_hive`),
  CONSTRAINT `FK_HiveLocation` FOREIGN KEY (`id_hive`) REFERENCES `tb_hives` (`id_hive`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `tb_honeys` (
  `id_honey` int unsigned NOT NULL AUTO_INCREMENT,
  `id_hive` int unsigned NOT NULL,
  `qty_honey` double NOT NULL,
  `nr_water_content` double NOT NULL,
  `is_good_water` tinyint(1) NOT NULL,
  `dt_honey_collection` timestamp NOT NULL,
  `ds_observation` text NOT NULL,
  `dt_creation` timestamp NOT NULL,
  PRIMARY KEY (`id_honey`),
  KEY `FK_HiveHoney` (`id_hive`),
  CONSTRAINT `FK_HiveHoney` FOREIGN KEY (`id_hive`) REFERENCES `tb_hives` (`id_hive`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `tb_users` (
  `id_user` int unsigned NOT NULL AUTO_INCREMENT,
  `nm_user` varchar(255) NOT NULL,
  `user_role` varchar(100) NOT NULL,
  `ds_email` varchar(255) NOT NULL,
  `ps_password` varchar(255) NOT NULL,
  `nr_phone` varchar(100) DEFAULT NULL,
  `nr_cep` varchar(70) DEFAULT NULL,
  `ds_address` varchar(255) DEFAULT NULL,
  `dt_creation` timestamp NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `ds_email_UNIQUE` (`ds_email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `tb_water_flowers` (
  `id_water_flowers` int unsigned NOT NULL AUTO_INCREMENT,
  `id_flower` int unsigned NOT NULL,
  `nr_ph` double NOT NULL,
  `nr_temperature` double NOT NULL,
  `contains_contaminants` tinyint(1) NOT NULL,
  `lvl_oxygen` tinyint(1) NOT NULL,
  `dt_creation` timestamp NOT NULL,
  PRIMARY KEY (`id_water_flowers`),
  KEY `FK_FlowerWaterFlower` (`id_flower`),
  CONSTRAINT `FK_FlowerWaterFlower` FOREIGN KEY (`id_flower`) REFERENCES `tb_flowers` (`id_flower`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `tb_water_hives` (
  `id_water_hive` int unsigned NOT NULL AUTO_INCREMENT,
  `id_hive` int unsigned NOT NULL,
  `nr_ph` double NOT NULL,
  `nr_temperature` double NOT NULL,
  `contains_contaminants` tinyint(1) NOT NULL,
  `lvl_oxygen` tinyint(1) NOT NULL,
  `dt_creation` timestamp NOT NULL,
  PRIMARY KEY (`id_water_hive`),
  KEY `FK_FlowerWaterHive` (`id_hive`),
  CONSTRAINT `FK_FlowerWaterHive` FOREIGN KEY (`id_hive`) REFERENCES `tb_hives` (`id_hive`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
