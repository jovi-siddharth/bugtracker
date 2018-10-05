use `bug_tracker`;

#SET FOREIGN_KEY_CHECKS = 0;

create table if not exists REVINFO (
        REV int(11) NOT NULL AUTO_INCREMENT,
        REVTSTMP bigint,
        primary key (REV)
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
    
CREATE TABLE IF NOT EXISTS  `priority_AUD` (
  `id` int(11),
  `priority` int(2),
   REV int(7) not null,
   REVTYPE tinyint,
  PRIMARY KEY (`id`,REV)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS  `status_AUD` (
  `id` int(11) ,
  `status` varchar(20),
  REV int(7) not null,
   REVTYPE tinyint,
  PRIMARY KEY (`id`,REV)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `ticket_AUD` (
  `id` int(11),
	`created_by` int(11) ,
	`assigned_to` int(11) ,
  `created_on` timestamp ,
  `status` int(11) ,
    `bug` varchar(200) ,
  `last_modified` timestamp ,
  `priority` int(2),
  REV int(7) not null,
   REVTYPE tinyint,
  PRIMARY KEY (`id`,REV)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `user_AUD` (
  `id` int(11) ,
  `username` varchar(50) ,
  `password` char(80),
  `first_name` varchar(50) ,
  `last_name` varchar(50),
  `email` varchar(50) ,
  REV int(7) not null,
   REVTYPE tinyint,
  PRIMARY KEY (`id`,REV)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;