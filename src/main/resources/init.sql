# drop table if exists user_info;
create table if not exists user_info (
  id bigint(20) not null auto_increment,
  username varchar(32) not null comment'用户名称',
  name varchar(32) comment'真实姓名',
  password varchar(64) not null comment'密码',
  salt varchar(64) not null comment'盐',
  status tinyint(4) comment'锁定状态 0 未锁定 1被锁定',
  primary key(id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'用户表';
# drop table if exists sys_role;
create table if not exists sys_role (
  id bigint(20) not null auto_increment,
  available tinyint not null comment'是否可用,如果不可用将不会添加给用户，0不可用 1可用',
  description varchar(128) not null comment'角色描述',
  role varchar(32) not null comment'角色标识，唯一',
  primary key(id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'用户角色表';
# drop table if exists sys_permission;
create table if not exists sys_permission (
  id bigint(20) not null auto_increment,
  available tinyint not null comment'是否可用,如果不可用将不会添加给用户，0不可用 1可用',
  name varchar(32) not null comment'权限名称',
  parent_id bigint(20) comment'父权限id',
  parent_ids varchar(64) comment'父权限编号列表',
  permission varchar(64) not null comment'权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view',
  resource_type varchar(32) not null comment'资源类型',
  url varchar(64) not null comment'资源路径',
  primary key(id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'角色权限对应表';
# drop table if exists sys_user_role;
create table if not exists sys_user_role (
  user_id bigint(20) not null comment'用户id',
  role_id bigint(20) not null comment'角色id'
)engine=InnoDB DEFAULT CHARSET = utf8 comment'用户角色对应表';
# drop table if exists sys_role_permission;
create table if not exists sys_role_permission (
  role_id bigint(20) not null comment'角色id',
  permission_id bigint(20) not null comment'权限id'
)engine=InnoDB DEFAULT CHARSET = utf8 comment'角色权限对应表';
# drop table if exists sys_config;
# create table if not exists sys_config (
#   id bigint(20) not null auto_increment,
#   name varchar(32) not null comment'字段名',
#   value varchar(64) not null comment'值',
#   description varchar(64) not null comment'描述',
#   createTime TIMESTAMP comment'创建时间',
#   primary key(id)
# )engine=InnoDB DEFAULT CHARSET = utf8 comment'系统配置表';
# drop table if exists sys_address;
create table if not exists address (
  id bigint(20) not null auto_increment,
  province_code varchar(32) comment'省行政编码',
  city_code varchar(32) comment'市行政编码',
  county_code varchar(32) comment'区行政编码',
  address text comment'详细地址',
  main tinyint(4) comment'是否默认地址 0否 1是',
  create_time TIMESTAMP comment'创建时间',
  primary key(id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'地址表';
# drop table if exists sys_user_address;
create table if not exists sys_user_address (
  user_id bigint(20) not null comment'用户id',
  address_id bigint(20) not null comment'地址id'
)engine=InnoDB DEFAULT CHARSET = utf8 comment'用户地址对应表';
# drop table if exists market_order;
# create table if not exists market_order (
#   id bigint(20) not null auto_increment,
#   seq varchar(32) not null comment'订单编号',
#   state tinyint comment'订单状态',
#   startTime datetime comment'订单开始时间',
#   endTime datetime comment'订单结束时间',
#   price double comment'总价',
#   payment double comment'支付款',
#   payType tinyint comment'支付类型',
#   remark text comment'备注',
#   primary key(id)
# )engine=InnoDB DEFAULT CHARSET = utf8 comment'订单表';
# drop table if exists market_order_detail;
# create table if not exists market_order_detail (
#   id bigint(20) not null auto_increment,
#   orderId bigint(20) not null comment'订单id',
#   address varchar(64) comment'收货地址',
#   receiverName varchar(32) comment'收货人',
#   receiverPhone varchar(32) comment'联系方式',
#   primary key(id)
# )engine=InnoDB DEFAULT CHARSET = utf8 comment'订单明细表';
# drop table if exists market_order_history;
# create table if not exists market_order_history (
#   id bigint(20) not null auto_increment,
#   orderId bigint(20) not null comment'订单id',
#   state tinyint comment'订单状态',
#   createTime timestamp comment'创建时间',
#   operatorId bigint(20) not null comment'操作人',
#   operatorName varchar(32) comment'操作人名字',
#   primary key (id)
# )engine=InnoDB DEFAULT CHARSET = utf8 comment'订单历史记录表';
# drop table if exists market_product;
# create table if not exists market_product (
#   id bigint(20) not null auto_increment,
#   name varchar(32) comment'商品名称',
#   price double comment'商品价格',
#   quantity int(32) comment'库存数量',
#   createTime timestamp comment'创建时间',
#   primary key (id)
# )engine=InnoDB DEFAULT CHARSET = utf8 comment'商品表';
# drop table if exists market_order_product;
# create table if not exists market_order_product(
#   id bigint(20) not null AUTO_INCREMENT,
#   orderId bigint(20) not null comment'订单id',
#   productId bigint(20) not null comment'商品id',
#   productName varchar(32) comment'商品名称',
#   productQuantity int(32) comment'商品预定数量',
#   productPrice double comment'商品单价',
#   primary key (id),
#   unique key (orderId,productId),
#   constraint order_product_ok foreign key(orderId) references market_order(id),
#   constraint order_product_pk foreign key(productId) references market_product(id)
# )engine=InnoDB DEFAULT CHARSET = utf8 comment'订单商品表';
# drop table if exists market_attr;
# create table if not exists market_attr (
#   id bigint(20) not null auto_increment,
#   name varchar(32) not null comment'属性名',
#   primary key(id)
# )engine=InnoDB DEFAULT CHARSET = utf8 comment'属性表';
# drop table if exists market_product_attr;
# create table if not exists market_product_attr (
#   id bigint(20) not null auto_increment,
#   productId bigint(20) not null comment'商品id',
#   attrId bigint(20) not null comment'属性id',
#   PRIMARY KEY (id),
#   unique key (productId, attrId),
#   constraint product_attr_pk foreign key(productId) references market_product(id),
#   constraint product_attr_ak foreign key(attrId) references market_attr(id)
# )engine=InnoDB DEFAULT CHARSET = utf8 comment'商品属性对应表';
# drop table if exists market_withdra_history;
# create table if not exists market_withdra_history (
#   id bigint(20) not null auto_increment,
#   memberId bigint(20) not null comment'用户id',
#   title varchar(32) comment'标题',
#   money double comment'提现金额',
#   createTime TIMESTAMP comment'提现时间',
#   type tinyint(4) comment'提现方式',
#   withdraAccount varchar(32) comment'提现账号',
#   state tinyint(4) comment'提现状态',
#   primary key (id)
# )engine=InnoDB DEFAULT CHARSET = utf8 comment'提现记录表';
# drop table if exists market_funds_detail;
# create table if not exists market_funds_detail (
#   id bigint(20) not null auto_increment,
#   memberId bigint(20) not null comment'用户id',
#   title varchar(32) comment'标题',
#   type tinyint(4) comment'类型',
#   money double comment'金额',
#   source varchar(32) comment'来源',
#   createTime TIMESTAMP comment'创建时间',
#   primary key (id)
# )engine=InnoDB DEFAULT CHARSET = utf8 comment'资金明细表';
# drop table if exists sys_keywords;
# create table if not exists sys_keywords (
#   id bigint(20) not null auto_increment,
#   keyword varchar(32) not null comment'关键词',
#   createTime TIMESTAMP comment'创建时间',
#   primary key (id)
# )engine=InnoDB DEFAULT CHARSET = utf8 comment'关键词表';
# drop table if exists sys_banner;
# create table if not exists sys_banner (
#   id bigint(20) not null auto_increment,
#   title varchar(32) comment'标题',
#   url text comment'点击访问链接',
#   banner long comment'图片唯一名',
#   state tinyint comment'状态',
#   createTime timestamp comment'创建时间',
#   primary key (id)
# )engine=InnoDB DEFAULT CHARSET = utf8 comment'焦点图表';
# drop table if exists sys_permission_init;
create table if not exists sys_permission_init (
  id bigint(20) not null auto_increment,
  url varchar(64) comment'链接',
  permission_init varchar(32) comment'shiro权限',
  sort int comment'排序',
  create_time timestamp comment'创建时间',
  primary key (id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'shiro权限表';
# # create table t_data_dict (
# #
# # );
create table if not exists web_banner (
  id bigint(20) not null auto_increment,
  img_name varchar(64) comment'图片名称',
  link varchar(64) comment'banner图跳转链接地址',
  position varchar(32) comment'banner图位置',
  url varchar(64) comment'banner图引用地址',
  create_time timestamp comment'创建时间',
  primary key (id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'网站首页banner图表';
create table if not exists web_page (
  id bigint(20) not null auto_increment,
  title varchar(32) comment'页面标题',
  page varchar(32) comment'所属页面',
  create_time timestamp comment'创建时间',
  primary key (id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'网站富本文内容表';
create table if not exists web_news (
  id bigint(20) not null auto_increment,
  title varchar(32) comment'新闻标题',
  news_time datetime comment'新闻日期',
  content text comment'新闻内容',
  create_time timestamp comment'创建时间',
  primary key (id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'网站新闻表';
create table if not exists web_catagory (
  id bigint(20) not null auto_increment,
  name varchar(64) comment'商品分类名称',
  content text comment'分类说明',
  parent_id bigint(20) comment'父分类编号',
  icon text comment'图片地址',
  create_time timestamp comment'创建时间',
  primary key (id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'网站产品类别表';
create table if not exists web_product (
  id bigint(20) not null auto_increment,
  name varchar(64) comment'商品名称',
  title varchar(64) comment'商品标题',
  create_time timestamp comment'创建时间',
  home_flag tinyint(4) comment'首页展示',
  recommend_flag tinyint(4) comment'推荐0不推荐 1推荐',
  primary key (id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'网站产品表';
create table if not exists web_info (
  id bigint(20) not null auto_increment,
  name varchar(64) comment'网站名称',
  title varchar(64) comment'网站标题',
  open_time varchar(64) comment'营业(开放)时间',
  introduction text comment'简介',
  address varchar(128) comment'地址',
  phone varchar(64) comment'联系方式',
  email varchar(64) comment'邮箱',
  fax varchar(64) comment'传真',
  url varchar(64) comment'网址',
  icp varchar(64) comment'icp备案号',
  map varchar(64) comment'地图图片',
  primary key (id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'网站信息表';
create table if not exists web_image (
  id bigint(20) not null auto_increment,
  name varchar(64) comment'图片名称',
  link varchar(64) comment'banner图跳转链接地址',
  position varchar(32) comment'banner图位置',
  address varchar(64) comment'banner图引用地址',
  create_time timestamp comment'创建时间',
  primary key (id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'网站图片表';
create table if not exists web_html (
  id bigint(20) not null auto_increment,
  position varchar(64) comment'富文本位置',
  html text comment'富文本内容',
  create_time timestamp comment'创建时间',
  primary key (id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'网站富文本表';
create table if not exists web_page_html (
  page_id bigint(20) not null comment'页面id',
  html_id bigint(20) not null comment'富文本id'
)engine=InnoDB DEFAULT CHARSET = utf8 comment'网站页面富文本对照表';
create table if not exists web_page_image (
  page_id bigint(20) not null comment'页面id',
  image_id bigint(20) not null comment'图片id'
)engine=InnoDB DEFAULT CHARSET = utf8 comment'网站页面图片对照表';
create table if not exists web_product_html (
  product_id bigint(20) not null comment'页面id',
  html_id bigint(20) not null comment'富文本id'
)engine=InnoDB DEFAULT CHARSET = utf8 comment'网站产品富文本对照表';
create table if not exists web_news_html (
  news_id bigint(20) not null comment'新闻id',
  html_id bigint(20) not null comment'富文本id'
)engine=InnoDB DEFAULT CHARSET = utf8 comment'网站新闻富文本对照表';
create table if not exists web_news_image (
  news_id bigint(20) not null comment'新闻id',
  image_id bigint(20) not null comment'图片id'
)engine=InnoDB DEFAULT CHARSET = utf8 comment'网站新闻图片对照表';
create table if not exists web_catagory_product (
  catagory_id bigint(20) not null comment'类别id',
  product_id bigint(20) not null comment'产品id'
)engine=InnoDB DEFAULT CHARSET = utf8 comment'网站类别产品对照表';