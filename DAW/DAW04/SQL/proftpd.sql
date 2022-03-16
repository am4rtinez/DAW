DROP DATABASE IF EXISTS proftpd;

CREATE DATABASE proftpd CHARACTER SET utf8 COLLATE utf8_general_ci;

USE proftpd;

DROP TABLE IF EXISTS `ftpgroups`;
CREATE TABLE `ftpgroups` (
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `groupname` varchar(30) NOT NULL,
    `gid` int NOT NULL DEFAULT '5500',
    `members` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS ` ftpusers `;
CREATE TABLE `ftpusers` (
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `userid` varchar(30) NOT NULL,
    `passwd` varchar(80) NOT NULL,
    `uid` int DEFAULT '5500',
    `gid` int DEFAULT '5500',
    `homedir` varchar(255) DEFAULT NULL,
    `shell` varchar(255) DEFAULT NULL,
    `LoginAllowed` tinyint(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `ftpgroups` (`id`, `groupname`, `gid`, `members`) 
VALUES (1, 'ftpusers', '6600', 'miquel,eric,laia,albert,joaquim,joan');

INSERT INTO ftpusers (`id`, `userid`, `passwd`, `uid`, `gid`, `homedir`, `shell`, `LoginAllowed`) 
VALUES (1, 'miquel', 'miquel', 5500, 5500, '/home/ftpusers', '/bin/false', 1),
(2, 'eric', 'eric', 5501, 5500, '/home/ftpusers', '/bin/false', 1),
(3, 'laia', 'laia', 5502, 5500, '/home/ftpusers', '/bin/false', 1),
(4, 'albert', 'albert', 5503, 5500, '/home/ftpusers', '/bin/false', 1),
(5, 'joaquim', 'joaquim', 5504, 5500, '/home/ftpusers', '/bin/false', 1),
(6, 'jon', 'joan', 5505, 5505, '/home/ftpusers', '/bin/false', 1);
