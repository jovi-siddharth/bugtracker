use bug_tracker;

DROP TABLE IF EXISTS `comments`;

Create Table if not exists `comments` (
	`id` int(11) not null auto_increment,
    `content` varchar(200) default null,
    `created_on` timestamp not null default current_timestamp,
    `ticket_id` int(11) default null,
	primary key (`id`),
    constraint `fk_ticket` foreign key (`ticket_id`) references `ticket` (`id`) on delete cascade on update cascade
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

