create table t_role (
  id bigint(20) not null auto_increment,
  name varchar(32) not null comment'角色名称',
  createTime TIMESTAMP comment'创建时间',
  primary key (id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'角色表';
create table t_permission (
  id bigint(20) not null auto_increment,
  name varchar(32) not null comment'权限名称',
  type tinyint(4) not null comment'权限类型 0最高权限 1主菜单 2子菜单',
  sort tinyint(4) not null comment'顺序',
  url text not null comment'权限绑定路径',
  icon text comment'图片路径',
  createTime TIMESTAMP comment'创建时间',
  PRIMARY KEY (id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'权限表';
create table t_role_permission (
  id bigint(20) not null auto_increment,
  roleId bigint(20) not null comment'角色id',
  permissionId bigint(20) not null comment'权限id',
  createTime TIMESTAMP comment'创建时间',
  PRIMARY KEY (id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'角色权限对应表';
create table t_user (
  id bigint(20) not null auto_increment,
  name varchar(32) not null comment'用户名称',
  realName varchar(32) comment'真实姓名',
  password varchar(32) not null comment'密码',
  createTime date comment'创建时间',
  modifyTime date comment'修改时间',
  lastLoginTime TIMESTAMP comment'最近登录时间',
  lastLoginIp varchar(32)  comment'最近登录ip',
  roleId bigint(20) not null comment'角色id',
  phone varchar(32) comment'联系方式',
  primary key(id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'角色权限对应表';
create table t_system_config (
  id bigint(20) not null auto_increment,
  name varchar(32) not null comment'字段名',
  value varchar(64) not null comment'值',
  remark varchar(64) not null comment'描述',
  createTime TIMESTAMP comment'创建时间',
  primary key(id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'系统配置表';
create table t_member (
  id bigint(20) not null auto_increment,
  name varchar(32) not null comment'会员名称',
  password varchar(32) not null comment'密码',
  createTime date not null  comment'创建时间',
  modifyTime date comment'修改时间',
  lastLoginTime TIMESTAMP comment'最近登录时间',
  lastLoginIp varchar(32) comment'最近登录ip',
  phone varchar(32) comment'联系方式',
  email varchar(64) comment'邮箱地址',
  mac varchar(64) comment'mac地址',
  balance double comment'账户余额',
  memberFlag tinyint(4) comment'是否开启会员 0为开启 1已开启',
  primary key(id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'会员表';
create table t_member_address (
  id bigint(20) not null auto_increment,
  provinceCode varchar(32) comment'省行政编码',
  cityCode varchar(32) comment'市行政编码',
  countyCode varchar(32) comment'区行政编码',
  address text comment'详细地址',
  contactName varchar(32) comment'联系人',
  contactPhone varchar(32) comment'联系地址',
  memberId bigint(20) not null comment'会员id',
  isMain tinyint(4) comment'是否默认地址 0否 1是',
  createTime TIMESTAMP comment'创建时间',
  primary key(id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'地址表';
create table t_order (
  id bigint(20) not null auto_increment,
  seq varchar(32) not null comment'订单编号',
  state tinyint comment'订单状态',
  startTime datetime comment'订单开始时间',
  endTime datetime comment'订单结束时间',
  price double comment'总价',
  payment double comment'支付款',
  payType tinyint comment'支付类型',
  remark text comment'备注',
  primary key(id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'订单表';
create table t_order_detail (
  id bigint(20) not null auto_increment,
  orderId bigint(20) not null comment'订单id',
  address varchar(64) comment'收货地址',
  receiverName varchar(32) comment'收货人',
  receiverPhone varchar(32) comment'联系方式',
  primary key(id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'订单明细表';
create table t_order_history (
  id bigint(20) not null auto_increment,
  orderId bigint(20) not null comment'订单id',
  state tinyint comment'订单状态',
  createTime timestamp comment'创建时间',
  operatorId bigint(20) not null comment'操作人',
  operatorName varchar(32) comment'操作人名字',
  primary key (id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'订单历史记录表';
create table t_order_product(
  id bigint(20) not null AUTO_INCREMENT,
  orderId bigint(20) not null comment'订单id',
  productId bigint(20) not null comment'商品id',
  productName varchar(32) comment'商品名称',
  productQuantity int(32) comment'商品预定数量',
  productPrice double comment'商品单价',
  primary key (id),
  unique key (orderId,productId)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'订单商品表';
create table t_product (
  id bigint(20) not null auto_increment,
  name varchar(32) comment'商品名称',
  price double comment'商品价格',
  quantity int(32) comment'库存数量',
  createTime timestamp comment'创建时间',
  primary key (id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'商品表';
create table t_attr (
  id bigint(20) not null auto_increment,
  name varchar(32) not null comment'属性名',
  primary key(id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'属性表';
create table t_product_attr (
  id bigint(20) not null auto_increment,
  productId bigint(20) not null comment'商品id',
  attrId bigint(20) not null comment'属性id',
  PRIMARY KEY (id),
  unique key (productId, attrId)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'商品属性对应表';
create table t_withdra_history (
  id bigint(20) not null auto_increment,
  memberId bigint(20) not null comment'用户id',
  title varchar(32) comment'标题',
  money double comment'提现金额',
  createTime TIMESTAMP comment'提现时间',
  type tinyint(4) comment'提现方式',
  withdraAccount varchar(32) comment'提现账号',
  state tinyint(4) comment'提现状态',
  primary key (id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'提现记录表';
create table t_funds_detail (
  id bigint(20) not null auto_increment,
  memberId bigint(20) not null comment'用户id',
  title varchar(32) comment'标题',
  type tinyint(4) comment'类型',
  money double comment'金额',
  source varchar(32) comment'来源',
  createTime TIMESTAMP comment'创建时间',
  primary key (id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'资金明细表';
create table t_keywords (
  id bigint(20) not null auto_increment,
  keyword varchar(32) not null comment'关键词',
  createTime TIMESTAMP comment'创建时间',
  primary key (id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'关键词表';
create table t_banner (
  id bigint(20) not null auto_increment,
  title varchar(32) comment'标题',
  url text comment'点击访问链接',
  banner long comment'图片唯一名',
  state tinyint comment'状态',
  createTime timestamp comment'创建时间',
  primary key (id)
)engine=InnoDB DEFAULT CHARSET = utf8 comment'焦点图表';
# create table t_data_dict (
#
# );
