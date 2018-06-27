-- clear context
DROP TABLE IF EXISTS `album_user`;
DROP TABLE IF EXISTS `album`;

-- Create syntax for TABLE 'album_user'
CREATE TABLE `a_user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(20) DEFAULT NULL ,
  `password` VARCHAR(20) DEFAULT NULL ,
  PRIMARY KEY (`id`)
);

-- Create syntax for TABLE 'album'
CREATE TABLE `album` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `userid` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `albumname` VARCHAR(255) DEFAULT NULL ,
  CONSTRAINT user_fk FOREIGN KEY(userid) REFERENCES a_user(id)
  PRIMARY KEY (`id`)
);
