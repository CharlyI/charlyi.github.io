-- MySQL Workbench Synchronization
-- Generated: 2018-01-19 02:24
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Constantin

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE TABLE IF NOT EXISTS `mathdb`.`Users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `patronymic` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mathdb`.`Teachers` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `User_id_UNIQUE` (`user_id` ASC),
  CONSTRAINT `TeacherOfUserFK`
    FOREIGN KEY (`user_id`)
    REFERENCES `mathdb`.`Users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mathdb`.`Students` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC),
  CONSTRAINT `StudentOfUserFK`
    FOREIGN KEY (`user_id`)
    REFERENCES `mathdb`.`Users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mathdb`.`TeachersOfStudents` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `teacher_id` INT(11) NOT NULL,
  `student_id` INT(11) NOT NULL,
  `teacherIsReady` TINYINT(1) NOT NULL DEFAULT 0,
  `studentIsReady` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `TeacherOfStuentOf_TeacherFK_idx` (`teacher_id` ASC),
  INDEX `TeacherOfStudentOf_StuentFK_idx` (`student_id` ASC),
  CONSTRAINT `TeacherOfStudentOf_TeacherFK`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `mathdb`.`Teachers` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `TeacherOfStudentOf_StudentFK`
    FOREIGN KEY (`student_id`)
    REFERENCES `mathdb`.`Students` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mathdb`.`TasksForStudents` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `teacherOfStudent_id` INT(11) NOT NULL,
  `test_id` INT(11) NOT NULL,
  `DateOfReceiving` DATETIME NOT NULL,
  `DateOfPerformance` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `TaskForStudentOf_TeachersOfStudentFK_idx` (`teacherOfStudent_id` ASC),
  INDEX `TaskForStudentOf_TestFK_idx` (`test_id` ASC),
  CONSTRAINT `TaskForStudentOf_TeachersOfStudentFK`
    FOREIGN KEY (`teacherOfStudent_id`)
    REFERENCES `mathdb`.`TeachersOfStudents` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `TaskForStudentOf_TestFK`
    FOREIGN KEY (`test_id`)
    REFERENCES `mathdb`.`Tests` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mathdb`.`AnswersOfStudents` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `taskForStudent_id` INT(11) NOT NULL,
  `AnswerNum` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `AnswerOfStudentOf_TasksForStudentsFK_idx` (`taskForStudent_id` ASC),
  CONSTRAINT `AnswerOfStudentOf_TasksForStudentsFK`
    FOREIGN KEY (`taskForStudent_id`)
    REFERENCES `mathdb`.`TasksForStudents` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mathdb`.`Tests` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `teacher_id` INT(11) NULL DEFAULT NULL,
  `subsection_id` INT(11) NULL DEFAULT NULL,
  `public` TINYINT(1) NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `TestOfTeacherFK_idx` (`teacher_id` ASC),
  INDEX `TestOfSubsection_idx` (`subsection_id` ASC),
  CONSTRAINT `TestOfTeacherFK`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `mathdb`.`Teachers` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `TestOfSubsection`
    FOREIGN KEY (`subsection_id`)
    REFERENCES `mathdb`.`Subsections` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mathdb`.`Tasks` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `test_id` INT(11) NOT NULL,
  `question` VARCHAR(1000) NOT NULL,
  `trueAnswer_id` INT(11) NULL DEFAULT NULL,
  `active` TINYINT(1) NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `TaskOfTestFK_idx` (`test_id` ASC),
  CONSTRAINT `TaskOfTestFK`
    FOREIGN KEY (`test_id`)
    REFERENCES `mathdb`.`Tests` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mathdb`.`Answers` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `task_id` INT(11) NOT NULL,
  `answer` VARCHAR(1000) NOT NULL,
  `active` TINYINT(1) NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `AnswerOfTaskFK_idx` (`task_id` ASC),
  CONSTRAINT `AnswerOfTaskFK`
    FOREIGN KEY (`task_id`)
    REFERENCES `mathdb`.`Tasks` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mathdb`.`Sections` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mathdb`.`Subsections` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `section_id` INT(11) NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `SubsectionOfSection_idx` (`section_id` ASC),
  CONSTRAINT `SubsectionOfSection`
    FOREIGN KEY (`section_id`)
    REFERENCES `mathdb`.`Sections` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
