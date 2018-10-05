DROP TABLE IF EXISTS comments;

DROP TABLE IF EXISTS `ticket`;

CREATE TABLE IF NOT EXISTS `ticket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
	`created_by` int(11) DEFAULT NULL,
	`assigned_to` int(11) DEFAULT NULL,
  `created_on` timestamp not null DEFAULT current_timestamp,
  `status` int(11) not null DEFAULT 1,
    `bug` varchar(200) DEFAULT NULL,
  `last_modified` timestamp not null DEFAULT current_timestamp,
  `priority` int(2) DEFAULT 1,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_CREATED` FOREIGN KEY (`created_by`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ASSIGNED` FOREIGN KEY (`assigned_to`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_STATUS` FOREIGN KEY (`status`) REFERENCES `status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PRIORITY` FOREIGN KEY (`priority`) REFERENCES `priority` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)