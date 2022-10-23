DROP DATABASE IF EXISTS `practice`;
CREATE DATABASE `practice`;
USE `practice`;

DROP TABLE IF EXISTS `order_master`;

DROP TABLE IF EXISTS `order_line`;

DROP TABLE IF EXISTS `product`;

create table practice.order_master
(
    order_no char(36)    not null comment '주문 번호',
    status   varchar(20) not null default 'ORDER_DONE' comment '주문 상태',
    reg_time timestamp   not null default current_timestamp() comment '등록 일시',
    upd_time timestamp   not null default current_timestamp() comment '수정 일시',
    constraint pk_order primary key (order_no)
);

create table practice.order_line
(
    order_line_seq bigint unsigned auto_increment not null comment '주문 내역 SEQ',
    order_no   char(36)        not null comment '주문 번호',
    product_no char(36)        not null comment '상품 번호',
    quantity   bigint unsigned not null comment '주문 수량',
    reg_time   timestamp       not null default current_timestamp() comment '등록 일시',
    upd_time   timestamp       not null default current_timestamp() comment '수정 일시',
    constraint pk_order_line primary key (order_line_seq)
);
