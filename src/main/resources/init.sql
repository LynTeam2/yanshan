# drop table if exists user_info;
create table if not exists user_info (
  id bigint(20) not null auto_increment,
  username varchar(32) not null comment'用户名称',
  name varchar(32) comment'真实姓名',
  password varchar(64) not null comment'密码',
  salt varchar(64) not null comment'盐',
  state tinyint(4) comment'锁定状态 0 未锁定 1被锁定',
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
  parent_id bigint(20) not null comment'父权限id',
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
create table if not exists shop_catagory (
  id bigint(20) not null auto_increment,
  name varchar(64) comment'商品分类名称',
  parent_id bigint(20) comment'父分类编号',
  create_time timestamp comment'创建时间',
  primary key (id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'商城类别表';
create table if not exists shop_product (
  id bigint(20) not null auto_increment,
  name varchar(64) comment'商品名称',
  catagory_id bigint(20) comment'商品分类id',
  business_id bigint(20) comment'商家id',
  create_time timestamp comment'创建时间',
  picture text comment'图片组 json格式',
  sales int(11) comment'销量',
  comment int(11) comment'评论数量',
  primary key (id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'商城商品表';
create table if not exists shop_sku (
  id bigint(20) not null auto_increment,
  product_id bigint(20) comment'商品id',
  attr_value text comment'sku属性 例[1:1,2:2]',
  price double comment'价格',
  stock int(11) comment'库存',
  sales int(11) comment'销量',
  create_time timestamp comment'创建时间',
  primary key (id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'商品库存表';
create table if not exists shop_attr (
  id bigint(20) not null auto_increment,
  name varchar(64) comment'属性名',
  catagory_id bigint(20) comment'商品分类id',
  parent_id bigint(20) comment'父属性id',
  create_time timestamp comment'创建时间',
  primary key (id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'商品属性名表';
create table if not exists shop_attr_value (
  id bigint(20) not null auto_increment,
  value varchar(64) comment'属性值',
  attr_id bigint(20) comment'属性名id',
  create_time timestamp comment'创建时间',
  primary key (id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'商品属性值表';
create table if not exists shop_product_attr (
  id bigint(20) not null auto_increment,
  product_id bigint(20) comment'产品id',
  attr_id bigint(20) comment'属性名id',
  value_id bigint(20) comment'属性值id',
  primary key (id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'商品属性值表';
create table if not exists shop_product_attr_search (
  id bigint(20) not null auto_increment,
  product_id bigint(20) comment'产品id',
  attr_ids text comment'商品具有的属性值编号 [1,2,3,4]',
  create_time timestamp comment'创建时间',
  primary key (id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'商品属性值表';