-- 建库脚本

-- Create DataBase
CREATE DATABASE IF NOT EXISTS `sexyalbum`;

USE `sexyalbum`;

-- clear context
DROP TABLE IF EXISTS `album_user`;
DROP TABLE IF EXISTS `album`;
DROP TABLE IF EXISTS `ele`;
DROP TABLE IF EXISTS `relation`;

-- Create syntax for TABLE 'album_user'
CREATE TABLE `album_user` (
  `userid` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(20) UNIQUE DEFAULT NULL ,
  `password` VARCHAR(20) DEFAULT NULL ,
  PRIMARY KEY (`userid`)
);

-- Create syntax for TABLE 'album'
CREATE TABLE `album` (
  `albumid` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `userid` BIGINT(20) NOT NULL ,
  `albumname` VARCHAR(255) DEFAULT NULL ,
  -- CONSTRAINT user_fk FOREIGN KEY(userid) REFERENCES a_user(id) ,
  PRIMARY KEY (`albumid`)
);

-- Create syntax for TABLE 'ele'
CREATE TABLE `ele` (
  `eleid` BIGINT(20) NOT NULL ,
  `source` VARCHAR(60) DEFAULT NULL ,
  `description` VARCHAR(140) DEFAULT NULL ,
  PRIMARY KEY (`eleid`)
);

-- Create mapping relation from TABLE 'album' to TABLE 'ele'
CREATE TABLE `relation` (
  -- `relationid` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `albumid` BIGINT(20) NOT NULL ,
  `eleid` BIGINT(20) NOT NULL
)