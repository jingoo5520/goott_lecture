

create table member (
    id varchar2(20) constraint member_id_pk primary key,
    password varchar2(40) constraint member_password_nn not null,
    regno varchar2(13) constraint member_regno_nn not null,
    mobile varchar2(13),
    address varchar2(100),
    constraint member_regno_uq unique(regno),
    constraint member_mobile_uq unique(mobile)
);

create table book (
    code number(4) constraint book_code_pk primary key,
    title varchar2(50) constraint book_code_nn not null,
    count number(6),
    price number(10),
    publish varchar2(50)
);

create table bookorder (
    no varchar2(10) constraint bookorder_no_pk primary key,
    id varchar2(20) constraint bookorder_id_nn not null,
    code number(4) constraint bookorder_code_nn not null,
    count number(6),
    orderDate date,
    constraint bookorder_id_fk foreign key(id) references member(id),
    constraint bookorder_code_fk foreign key(code) references book(code)
);

insert into member values('1000', '1234', '0404', '010-1234-1234', 서울시 구로구);

insert into book values(1, '일번책', 1, 10000, '책이좋아');

insert into bookorder values('000001', 'jingoo', 1, 1, sysdate);

insert into bookorder values('000002', 'jingoo2', 1, 1, sysdate);

drop table bookorder;
drop table book;
drop table member;

