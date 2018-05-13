# 测试用的初始数据

INSERT INTO `test`.`user_info` (`id`, `user_nickname`, `user_avatar`, `user_sex`) VALUES ('1', '测试用户昵称', '头像', '0');

INSERT INTO `test`.`user_auth` (`id`, `user_info_id`, `auth_available`, `login_type`, `login_account`, `login_password`, `login_salt`, `last_login_ip`, `last_login_time`, `last_login_device`, `last_login_try_ip`, `last_login_try_count`, `last_login_token`) VALUES ('1', '1', '', 'username', 'admin', 'admin', 'admin', '0.0.0.0', '2018-05-13 18:10:32', 'pc', '0.0.0.0', '0', NULL);