-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- 主机： localhost
-- 生成日期： 2023-12-31 17:55:13
-- 服务器版本： 5.7.44-log
-- PHP 版本： 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `webchat`
--

-- --------------------------------------------------------

--
-- 表的结构 `friend`
--

CREATE TABLE `friend` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `friend_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `friend`
--

INSERT INTO `friend` (`id`, `user_id`, `friend_id`) VALUES
(3, 1, 2),
(8, 1, 5),
(9, 2, 3),
(10, 1, 3);

-- --------------------------------------------------------

--
-- 表的结构 `group`
--

CREATE TABLE `group` (
  `group_id` int(11) NOT NULL,
  `group_name` varchar(255) DEFAULT NULL,
  `group_master` int(11) DEFAULT NULL,
  `group_avatar` varchar(255) DEFAULT NULL,
  `group_create_time` varchar(255) DEFAULT NULL,
  `group_star_message` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `group`
--

INSERT INTO `group` (`group_id`, `group_name`, `group_master`, `group_avatar`, `group_create_time`, `group_star_message`) VALUES
(1, '三人行', NULL, NULL, NULL, '三人行必有我师！'),
(2, '老铁', NULL, NULL, NULL, '一生情，一杯酒'),
(3, '永远的三班', NULL, NULL, NULL, '三班三班，我是三班，再说一遍，我是三班！');

-- --------------------------------------------------------

--
-- 表的结构 `group_message`
--

CREATE TABLE `group_message` (
  `group_msg_id` int(11) NOT NULL,
  `group_msg_content` varchar(255) NOT NULL,
  `group_msg_from` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  `group_msg_time` timestamp NOT NULL,
  `group_file_type` varchar(10) DEFAULT NULL,
  `group_file_path` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `group_message`
--

INSERT INTO `group_message` (`group_msg_id`, `group_msg_content`, `group_msg_from`, `group_id`, `group_msg_time`, `group_file_type`, `group_file_path`) VALUES
(5, '三人行！', 1, 1, '2023-12-30 16:25:38', NULL, NULL),
(6, '则必有我师！', 1, 1, '2023-12-30 16:25:51', NULL, NULL),
(7, '择其善者而学之！', 2, 1, '2023-12-30 16:55:01', NULL, NULL),
(8, '其不善者而改之！！', 2, 1, '2023-12-30 16:55:18', NULL, NULL),
(10, '一生情，一杯酒', 1, 2, '2023-12-31 09:50:50', NULL, NULL),
(11, '三班三班，我是三班，再说一遍，我是三班！', 1, 3, '2023-12-31 09:50:58', NULL, NULL),
(12, '三班三班，我是三班，再说一遍，我是三班！', 3, 3, '2023-12-31 09:51:06', NULL, NULL),
(13, '三人行必有我师！', 3, 1, '2023-12-31 09:51:17', NULL, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `message`
--

CREATE TABLE `message` (
  `msg_id` int(11) NOT NULL,
  `msg_content` varchar(255) NOT NULL,
  `msg_from` int(11) NOT NULL,
  `msg_to` int(11) NOT NULL,
  `msg_time` timestamp NOT NULL,
  `file_type` varchar(10) DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `message`
--

INSERT INTO `message` (`msg_id`, `msg_content`, `msg_from`, `msg_to`, `msg_time`, `file_type`, `file_path`) VALUES
(3, '是这样吗？', 2, 1, '2023-12-30 15:53:17', NULL, NULL),
(5, '是这样的', 1, 2, '2023-12-30 15:55:16', NULL, NULL),
(7, '我试一试实时聊天', 1, 2, '2023-12-30 16:44:27', NULL, NULL),
(8, '成功了！', 2, 1, '2023-12-30 16:54:39', NULL, NULL),
(10, '你好！我是faks', 3, 1, '2023-12-31 09:37:37', NULL, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `take`
--

CREATE TABLE `take` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `add_time` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `take`
--

INSERT INTO `take` (`id`, `user_id`, `group_id`, `role`, `add_time`) VALUES
(1, 1, 1, NULL, NULL),
(2, 2, 1, NULL, NULL),
(3, 3, 1, NULL, NULL),
(4, 1, 2, NULL, NULL),
(5, 1, 3, NULL, NULL),
(6, 2, 2, NULL, NULL),
(7, 2, 3, NULL, NULL),
(8, 3, 2, NULL, NULL),
(9, 3, 3, NULL, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `gender` tinyint(1) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `signature` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`id`, `name`, `gender`, `avatar`, `password`, `signature`) VALUES
(1, '0', 0, NULL, '0', '0'),
(2, 'web', 1, 'http://dummyimage.com/100x100', 'web', 'nisi elit'),
(3, 'faks', NULL, NULL, 'faks', NULL),
(4, '1', 1, NULL, '1', NULL),
(5, 'test', 0, NULL, 'test', NULL);

--
-- 转储表的索引
--

--
-- 表的索引 `friend`
--
ALTER TABLE `friend`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `group`
--
ALTER TABLE `group`
  ADD PRIMARY KEY (`group_id`);

--
-- 表的索引 `group_message`
--
ALTER TABLE `group_message`
  ADD PRIMARY KEY (`group_msg_id`);

--
-- 表的索引 `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`msg_id`);

--
-- 表的索引 `take`
--
ALTER TABLE `take`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `friend`
--
ALTER TABLE `friend`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- 使用表AUTO_INCREMENT `group`
--
ALTER TABLE `group`
  MODIFY `group_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- 使用表AUTO_INCREMENT `group_message`
--
ALTER TABLE `group_message`
  MODIFY `group_msg_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- 使用表AUTO_INCREMENT `message`
--
ALTER TABLE `message`
  MODIFY `msg_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- 使用表AUTO_INCREMENT `take`
--
ALTER TABLE `take`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- 使用表AUTO_INCREMENT `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
