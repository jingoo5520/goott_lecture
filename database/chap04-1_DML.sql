-- DML
-- Create(생성), Read(조회), Update(수정), Delete(삭제)
-- (1) insert into: 테이블에 새로운 행을 추가하는 문장
-- 원하는 컬럼만 선택
-- insert into 테이블명([컬럼명1, 컬럼명2, ...])
-- values(컬럼값1, 컬럼값2, 컬럼값3, ...);

create table emp01 (
    empno number(4),
    ename varchar2(16),
    job varchar2(12),
    sal number(6)
);

-- 1) insert
insert into emp01 values(1000, '둘리', '사장님', 10000 );
select * from emp01;

alter table emp01
add hiredate date;

insert into emp01 values('1001', '도우너', '사원', 2000, sysdate );

insert into emp01(empno, ename) values (1002, '또치');

insert into emp01 values (1003, '마이콜', null, 2100, sysdate);

create table copyemp
as select * from employees
where 1 = 0;

-- A테이블에 B테이블 데이터 넣기(서브쿼리 이용)
insert into copyemp (employee_id, first_name, last_name, email, job_id, hire_date, salary, department_id)
select employee_id, first_name, last_name, email, job_id, hire_date, salary, department_id from employees;

-- 2) update
-- update 테이블명 set 컬럼명1 = 값1, 컬럼명2 = 값2, ...
-- [where 조건식]

-- 모든 행의 데이터를 변경하므로 주의!
update emp01 set sal = 200000;

update emp01
set sal = 400000 
where empno = 1003;

update emp01
set sal = (select sal from emp01 where empno = 1003)
where empno = 1002;

update emp01
set sal = 1.5 * (select sal from emp01 where empno = 1003)
where empno = 1001;

-- 3) delete
-- delete from 테이블명 [where 조건식]
-- 모든 데이터 삭제
delete from emp01;

-- 되돌리기, 영구저장 전 까지만 사용 가능
-- DDL문은 실행 마다 자동 영구저장 됨
rollback;

-- 실제 데이터를 삭제하지는 않고, 탈퇴 여부와 관련된 컬럼을 이용
delete from emp01 where empno = 1002;
select * from emp01;



SELECT department_id, count(*) from employees group by department_id;

insert into member values('kildong123', '1234', '홍길동');
insert into member(userid, passwd) values('ddochi123', '2222');
