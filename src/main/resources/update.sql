/*INSERT INTO sys_permission(id,available,name,parent_id,parent_ids,permission,resource_type,url) VALUES (1, 1, '用户管理', 0, '0/', 'userInfo:view', 'menu', 'userInfo/userList');
INSERT INTO sys_permission(id,available,name,parent_id,parent_ids,permission,resource_type,url) VALUES (2, 1, '用户添加', 1, '0/1', 'userInfo:add', 'button', 'userInfo/userAdd');
INSERT INTO sys_permission(id,available,name,parent_id,parent_ids,permission,resource_type,url) VALUES (3, 1, '用户删除', 1, '0/1', 'userInfo:del', 'button', 'userInfo/userDel');
INSERT INTO sys_role(id, available, description, role) VALUES (1, 1, '管理员', 'admin');
INSERT INTO sys_role(id, available, description, role) VALUES (2, 1, 'VIP会员', 'vip');
INSERT INTO sys_role_permission(role_id, permission_id) VALUES (1, 1);
INSERT INTO sys_role_permission(role_id, permission_id) VALUES (1, 2);
INSERT INTO user_info (id,username,name,password,salt,status) VALUES (1, 'admin', '管理员', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', 0);
INSERT INTO sys_user_role(user_id, role_id) VALUES (1, 1);
INSERT INTO sys_user_role(user_id, role_id) VALUES (1, 2);
INSERT INTO sys_permission_init(id, url, permission_init, sort, create_time) VALUES (1,'/logout','logout,kickout',1,now());
INSERT INTO sys_permission_init(id, url, permission_init, sort, create_time) VALUES (2,'/index','user,kickout',2,now());
INSERT INTO sys_permission_init(id, url, permission_init, sort, create_time) VALUES (3,'/','user,kickout',3,now());
INSERT INTO sys_permission_init(id, url, permission_init, sort, create_time) VALUES (4,'/**','anon,kickout',4,now());
INSERT INTO sys_permission_init(id, url, permission_init, sort, create_time) VALUES (5,'/kickout','anon',5,now());
INSERT INTO web_catagory(id, name, parent_id, create_time) VALUE (1, '测试类别', 0, now());
INSERT INTO web_catagory(id, name, parent_id, create_time) VALUE (2, '测试类别2', 1, now());*/