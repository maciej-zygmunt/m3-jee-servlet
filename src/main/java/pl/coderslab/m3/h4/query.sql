CREATE TABLE `newsleter`
(
`id` int(11) NOT NULL AUTO_INCREMENT,
`name` varchar(45) ,
`email` varchar(45) ,
PRIMARY KEY (`id`)
);
DROP  TABLE  IF EXISTS `guest-book`;
CREATE TABLE `guest-book` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`name` varchar(45) ,
`description` varchar(250),
PRIMARY KEY (`id`)
); 