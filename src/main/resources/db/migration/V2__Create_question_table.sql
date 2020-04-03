CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '发起人id',
  `title` varchar(50) NOT NULL COMMENT '问题名',
  `description` text NOT NULL COMMENT '问题描述',
  `view_count` bigint(20) NOT NULL DEFAULT '0' COMMENT '阅览人数',
  `comment_count` bigint(20) NOT NULL DEFAULT '0' COMMENT '评论人数',
  `like_count` bigint(20) NOT NULL DEFAULT '0' COMMENT '点赞人数',
  `gmt_create` bigint(20) NOT NULL,
  `gmt_modified` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8