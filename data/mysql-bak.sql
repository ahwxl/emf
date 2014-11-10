-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.38 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2014-11-10 13:18:28
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for test
DROP DATABASE IF EXISTS `test`;
CREATE DATABASE IF NOT EXISTS `test` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `test`;


-- Dumping structure for table test.qrtz_blob_triggers
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE IF NOT EXISTS `qrtz_blob_triggers` (
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table test.qrtz_blob_triggers: ~0 rows (approximately)
/*!40000 ALTER TABLE `qrtz_blob_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_blob_triggers` ENABLE KEYS */;


-- Dumping structure for table test.qrtz_calendars
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE IF NOT EXISTS `qrtz_calendars` (
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table test.qrtz_calendars: ~0 rows (approximately)
/*!40000 ALTER TABLE `qrtz_calendars` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_calendars` ENABLE KEYS */;


-- Dumping structure for table test.qrtz_cron_triggers
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE IF NOT EXISTS `qrtz_cron_triggers` (
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(200) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table test.qrtz_cron_triggers: ~0 rows (approximately)
/*!40000 ALTER TABLE `qrtz_cron_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_cron_triggers` ENABLE KEYS */;


-- Dumping structure for table test.qrtz_fired_triggers
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE IF NOT EXISTS `qrtz_fired_triggers` (
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `IS_VOLATILE` varchar(1) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_STATEFUL` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`ENTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table test.qrtz_fired_triggers: ~0 rows (approximately)
/*!40000 ALTER TABLE `qrtz_fired_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_fired_triggers` ENABLE KEYS */;


-- Dumping structure for table test.qrtz_job_details
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE IF NOT EXISTS `qrtz_job_details` (
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_VOLATILE` varchar(1) NOT NULL,
  `IS_STATEFUL` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table test.qrtz_job_details: ~0 rows (approximately)
/*!40000 ALTER TABLE `qrtz_job_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_job_details` ENABLE KEYS */;


-- Dumping structure for table test.qrtz_job_listeners
DROP TABLE IF EXISTS `qrtz_job_listeners`;
CREATE TABLE IF NOT EXISTS `qrtz_job_listeners` (
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `JOB_LISTENER` varchar(200) NOT NULL,
  PRIMARY KEY (`JOB_NAME`,`JOB_GROUP`,`JOB_LISTENER`),
  CONSTRAINT `qrtz_job_listeners_ibfk_1` FOREIGN KEY (`JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table test.qrtz_job_listeners: ~0 rows (approximately)
/*!40000 ALTER TABLE `qrtz_job_listeners` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_job_listeners` ENABLE KEYS */;


-- Dumping structure for table test.qrtz_locks
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE IF NOT EXISTS `qrtz_locks` (
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table test.qrtz_locks: ~5 rows (approximately)
/*!40000 ALTER TABLE `qrtz_locks` DISABLE KEYS */;
INSERT INTO `qrtz_locks` (`LOCK_NAME`) VALUES
	('CALENDAR_ACCESS'),
	('JOB_ACCESS'),
	('MISFIRE_ACCESS'),
	('STATE_ACCESS'),
	('TRIGGER_ACCESS');
/*!40000 ALTER TABLE `qrtz_locks` ENABLE KEYS */;


-- Dumping structure for table test.qrtz_paused_trigger_grps
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE IF NOT EXISTS `qrtz_paused_trigger_grps` (
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table test.qrtz_paused_trigger_grps: ~0 rows (approximately)
/*!40000 ALTER TABLE `qrtz_paused_trigger_grps` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_paused_trigger_grps` ENABLE KEYS */;


-- Dumping structure for table test.qrtz_scheduler_state
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE IF NOT EXISTS `qrtz_scheduler_state` (
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table test.qrtz_scheduler_state: ~1 rows (approximately)
/*!40000 ALTER TABLE `qrtz_scheduler_state` DISABLE KEYS */;
INSERT INTO `qrtz_scheduler_state` (`INSTANCE_NAME`, `LAST_CHECKIN_TIME`, `CHECKIN_INTERVAL`) VALUES
	('MININT-B36CQSL1415596253811', 1415596695426, 15000);
/*!40000 ALTER TABLE `qrtz_scheduler_state` ENABLE KEYS */;


-- Dumping structure for table test.qrtz_simple_triggers
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE IF NOT EXISTS `qrtz_simple_triggers` (
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table test.qrtz_simple_triggers: ~0 rows (approximately)
/*!40000 ALTER TABLE `qrtz_simple_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_simple_triggers` ENABLE KEYS */;


-- Dumping structure for table test.qrtz_triggers
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE IF NOT EXISTS `qrtz_triggers` (
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `IS_VOLATILE` varchar(1) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `JOB_NAME` (`JOB_NAME`,`JOB_GROUP`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table test.qrtz_triggers: ~0 rows (approximately)
/*!40000 ALTER TABLE `qrtz_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_triggers` ENABLE KEYS */;


-- Dumping structure for table test.qrtz_trigger_listeners
DROP TABLE IF EXISTS `qrtz_trigger_listeners`;
CREATE TABLE IF NOT EXISTS `qrtz_trigger_listeners` (
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `TRIGGER_LISTENER` varchar(200) NOT NULL,
  PRIMARY KEY (`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_LISTENER`),
  CONSTRAINT `qrtz_trigger_listeners_ibfk_1` FOREIGN KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table test.qrtz_trigger_listeners: ~0 rows (approximately)
/*!40000 ALTER TABLE `qrtz_trigger_listeners` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_trigger_listeners` ENABLE KEYS */;


-- Dumping structure for table test.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` varchar(50) DEFAULT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `user_password` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table test.user: ~0 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `user_id`, `user_name`, `user_password`) VALUES
	(NULL, '测试', '托尔斯泰', '123456');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
