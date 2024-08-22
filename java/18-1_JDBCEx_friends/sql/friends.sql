create table friends (
    friendNo number(4) primary key,
    friendName varchar(15) not null,
    mobile varchar(13) unique,
    addr varchar(50)
);

insert into friends values(1, '홍길동', '010-1234-1234', '서울시 1번지');

select * from friends;


select * from departments;

delete from departments where department_id = 310;