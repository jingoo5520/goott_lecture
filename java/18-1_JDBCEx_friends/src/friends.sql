create table friends (
    friendNo number(4) primary key,
    friendName varchar(15) not null,
    mobile varchar(13) unique,
    addr varchar(50)
);

insert into friends values(1, '홍길동', '010-1234-1234', '서울시 1번지');
insert into friends values(2, '홍길서', '010-1234-1235', '서울시 2번지');
insert into friends values(3, '홍길순', '010-1111-5520', '서울시 3번지');
insert into friends values(4, '홍길순', '010-4444-7777', '서울시 4번지');

select * from friends;

select * from departments;

update friends set friendname = '홍길순2' where friendNo = 4;

commit;

rollback;

select count(*) as cnt from friends where mobile = '010-1234-1234';