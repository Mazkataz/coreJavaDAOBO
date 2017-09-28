DROP TABLE IF EXISTS `invoice`;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `role` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE `invoice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `invoice_number` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `invoice_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

INSERT INTO `user` VALUES (1,'maddy','maddy','Boston','Clerk'),(2,'johnsmith','johnsmith01','Buffalo','Payment Releaser'),(3,'harry','harry02','Los Angeles','Clerk'),(4,'victoria','victoria55','Denver','Auditor'),(5,'xander','xander44','Miami','Payment Releaser'),(6,'nicholas','nicholas41','Austin','Auditor'),(7,'justin','justin2','Phoneix','Clerk'),(8,'brock','brock77','Dallas','Payment Releaser'),(9,'augustine','augustine7','Dayton','Auditor'),(10,'marry','marry','Atlanta','Clerk');

INSERT INTO `invoice` VALUES (1,'INV744','Approved',45000,'2017-05-12',3),(2,'INV112','Paid',12000,'2017-04-01',10),(3,'INV845','Deny',17500,'2017-05-19',7),(4,'INV748','Deny',25000,'2017-03-04',1),(5,'INV744','Paid',19500,'2017-06-19',7),(6,'INV115','Approved',18700,'2017-01-16',3),(7,'INH754','Approved',15000,'2017-04-17',1),(8,'INH749','Approved',15000,'2017-05-27',1);

