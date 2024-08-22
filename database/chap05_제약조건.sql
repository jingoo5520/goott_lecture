
drop table emp01;

create table emp0 (
    empno number(4),
    ename varchar2(10)
);

insert into emp01 values(1000, '둘리');
insert into emp01 values(null, '도우너');
insert into emp01 values(1000, '둘리');

-- -----------------------------------------------------------------------------
-- (1) not null
-- -----------------------------------------------------------------------------
create table emp01 (
    empno number(4) not null,
    ename varchar2(10) not null
);

insert into emp01 values(1000, '둘리');
insert into emp01 values(null, '도우너'); -- error: cannot insert NULL into

create table emp02 (
    empno number(4) constraint emp02_ename_nn not null,
    ename varchar2(10) not null
);

-- -----------------------------------------------------------------------------
-- (2) unique
-- -----------------------------------------------------------------------------
-- 컬럼에 중복된 데이터가 없어야 함
drop table emp01;

create table emp01 (
    empno number(4) unique,
    ename varchar2(10) not null
);

insert into emp01 values(1000, '둘리');
insert into emp01 values(1000, '또치'); -- unique constraint (HR.SYS_C007018) violated
insert into emp01 values(1002, '마이콜');
insert into emp01 values(null, '흰둥이');

-- -----------------------------------------------------------------------------
-- (3) primary key
-- -----------------------------------------------------------------------------
-- not null + unique
drop table emp01;

create table emp01 (
    empno number(4) primary key,
    ename varchar2(10) not null
);

insert into emp01 values(null, '둘리'); -- cannot insert NULL into ("HR"."EMP01"."EMPNO")
insert into emp01(ename) values('둘리'); -- cannot insert NULL into ("HR"."EMP01"."EMPNO")

insert into emp01 values(1000, '둘리');
insert into emp01 values(1000, '둘셋'); -- unique constraint

-- ename은 중복 가능
insert into emp01 values(1002, '둘넷');
insert into emp01 values(1003, '둘넷');

alter table emp01
add sal number(6);

update emp01 set sal = 5000;
rollback;

update emp01 set sal = 5000 where empno = 1000;
commit; -- 영구히 저장
rollback; -- 커밋된 시점으로 돌아감

delete from emp01;
rollback;

delete from emp01 where empno = 1003;

-- -----------------------------------------------------------------------------
-- (4) foreign key: 참조되는 테이블의 컬럼에 해당하는 값이 존재해야 함
-- -----------------------------------------------------------------------------
-- 부서번호가 부서테이블(부모테이블)에 존재하지 않는 값을 넣으려고 할 때


insert into employees values(207, '길동', '홍', 'kildong', null, sysdate, 'IT_PROG', 5000, null, 115, 70);
select * from employees where employee_id = 207;

insert into employees values(208, '길동', '홍', 'kildong2', null, sysdate, 'IT_PROG', 5000, null, 115, 370);
-- error: integrity constraint (HR.EMP_DEPT_FK) violated - parent key not found

delete from departments where department_id = 60;
-- error: integrity constraint (HR.EMP_DEPT_FK) violated - child record found

select * from employees where department_id = 60;

delete from employees where department_id = 60;
-- error: integrity constraint (HR.EMP_DEPT_FK) violated - child record found

select * from employees where department_id = 210;
-- 60번 부서 인원들 210번 부서로 옮김
update employees set department_id = 210 where department_id = 60; 

delete from departments where department_id = 60; -- child record found

delete from job_history where department_id = 60;

delete from departments where department_id = 60;

rollback;

select * from employees where department_id = 60;

drop table emp01;
drop table emp02;

-- 사원, 부서 테이블을 만들어보자
create table dept01 (
    deptno number(2) primary key,
    dname varchar2(10) unique
);


create table emp01 (
    empno number(4) primary key,
    ename varchar2(10) not null,
    deptno number(2) references dept01(deptno)
);

desc emp01;
desc dept01;

create table jhistory01 (
    empno number(4),
    start_date date not null,
    end_date date not null,
    deptno number(2),
    constraint jhistory01_pk_combo primary key(empno, start_date),
    constraint jhistory01_deptno_fk foreign key(deptno) references dept01(deptno)
);

insert into dept01 values(10, '총무부');
insert into dept01 values(20, 'IT');

insert into emp01 values(1000, '둘리', 10);
insert into emp01 values(1001, '고길동', 20);

insert into jhistory01 values(1000, '21-10-01', '2024-10-31', 10);

insert into jhistory01 values(1001, '20-10-01', '2024-10-31', 20);

-- 부서 테이블을 참조하고 있는 자식 테이블의 데이터가 있음, emp01
delete from dept01 where deptno = 10;

insert into dept01 values(30, '기획부');

update emp01 set deptno = 30 where empno = 1000;

-- 부서 테이블을 참조하고 있는 자식 테이블의 데이터가 있음, jhistory01
delete from dept01 where deptno = 10;

delete from jhistory01 where deptno = 10;

delete from dept01 where deptno = 10;

-- foreign key
drop table dept01;

-- 자식 테이블부터 위로 삭제
drop table emp01;
drop table jhistory01;
drop table dept01;

-- -----------------------------------------------------------------------------
-- (5) check
-- -----------------------------------------------------------------------------

create table emp01 (
    empno number(4) primary key,
    ename varchar2(10) not null,
    gender char(1) check (gender in ('M', 'F'))
);

insert into emp01 values(1000, '홍길동', m); -- 에러발생
insert into emp01 values(1000, '홍길동', 'M');
insert into emp01 values(1001, '홍길순', 'F');

drop table emp01;

create table member (
    userid varchar2(10) primary key,
    passwd varchar2(20) not null,
    age number(3) check (age between 14 and 150)
);

insert into member values('abcd', '1234', 10);
insert into member values('abcc', '1234', 14);
insert into member values('abdd', '1234', 240);


-- -----------------------------------------------------------------------------
-- (6) default
-- -----------------------------------------------------------------------------

create table dept01 (
    deptno number(3) primary key,
    dname varchar2(10),
    loc varchar2(16) default '서울'
);

insert into dept01(deptno, dname) values(10, '개발부');
insert into dept01 values(20, '총무부', null);

drop table member;



