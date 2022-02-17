/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/ optic /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE optic;

DROP TABLE IF EXISTS user;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `enabled` bit(1) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` text NOT NULL,
  `sur_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS authorities;
CREATE TABLE `authorities` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `authority` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKrimuuii4fm3j9qt8uupyiy8nd` (`user_id`,`authority`),
  CONSTRAINT `FKov2uw9bvmedktrvmk2qchv198` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS hibernate_sequence;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS product;
CREATE TABLE `product` (
  `id` bigint NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `folio` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO hibernate_sequence(next_val) VALUES(8);

INSERT INTO product(id,date,description,folio,is_active,name,price) VALUES
  (1,'2022-02-10 01:54:32.472000','Lentes de sol para cualquier comento','09609658-da15-4101-9984-b3fa86fb7471',1,'Lentes de sol',220),
  (2,'2022-02-10 02:04:19.250000','Lentes de contacto para cualquier ocasión','f1564c88-bd55-4c4a-a6a2-657ce916f728',1,'Lentes de contacto rojos',345),
  (3,'2022-02-10 12:29:53.901000','Lentes para protección','047cc0bd-7122-4945-95cc-b255ad46181e',1,'Lentes sin aumento',150),
  (4,'2022-02-10 12:30:50.003000','Armazón de aluminio para cualquier lente ','f9730a46-41c9-4ca9-bead-58f50b07792c',1,'Armazón de aluminio',120),
  (5,'2022-02-13 19:37:44.510000','Lentes para evitar la afectación por la luz de los equipos de computo','6d4b2cab-7ea8-49f5-9519-f2feae24634d',1,'Lentes de protección para equipos de computo',250),
  (6,'2022-02-14 16:33:25.842000','ninguna','292c4d5f-ced2-42f5-9312-3f50f547165f',1,'Lentes azules y rosas',7554),
  (7,'2022-02-14 16:34:31.018000','  ninguna  ','8c4b4d7f-ae1e-4105-956a-8b0b042f4284',1,'lentes modernos',7888);
INSERT INTO user(id,enabled,last_name,name,password,sur_name,email) VALUES
(1,1,'Robles','José Alejandro','$2a$10$nmzCpl8QtDeJ3MV1I2k4KOYh9MFL1A7IcrHuxTDFBN2L5LNAl/jvS','Benitéz','admin'),
(2,1,'Martínez','Valerie','$2a$10$RE45Nr8bBqL7YNTCVi44SOMjp981FM5N8TR.TNtxjV/8noFKwEtPy','Romero','valerie@gmail.com');

INSERT INTO authorities(id,authority,user_id) VALUES(1,'ROLE_ADMIN',1),(2,'ROLE_CLIENT',2);