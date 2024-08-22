---- DML문 연습문제
--
--1. emp01 테이블을 제거한 후 다음과 같은 구조로 emp01테이블을 생성하세요
---- empno number(4)
---- ename varchar2(10)
---- job varchar2(9)
---- mgr number(4)
---- hiredate date
---- sal number(7, 2)
---- comm number(7, 2)
---- deptno number(2)

create table emp01 (
    empno number(4),
    ename varchar2(10),
    job varchar2(9),
    mgr number(4),
    hiredate date,
    sal number(7, 2),
    comm number(7, 2),
    deptno number(2)
);

--2. emp01테이블에 다음과 같은 데이터를 추가하세요
-- 7369 smith clerk 7839 80/12/17 800 null 20
insert into emp01 values(7369, 'smith', 'clerk', 7839, '80/12/17', 800, null, 20);
-- 7499 allen salseman 7369 87/12/20 1600 300 30
insert into emp01 values(7499, 'allen', 'salseman', 7369, '87/12/20', 1600, 300, 30);
-- 7839 king president null null 5000 null null
insert into emp01 values(7839, 'king', 'president', null, null, 5000, null, null);
--3. emp01테이블의 모든 사원의 급여를 10% 인상하세요
update emp01
set sal = sal * 1.1 ;

--4. emp01 테이블에서 king의 입사일을 오늘 날짜로 수정하세요
update emp01
set hiredate = sysdate
where ename = 'king';

--5. emp01 테이블에서 1985년 이후에 입사한 모든 직원을 삭제하세요
delete from emp01
where hiredate > '85/01/01';

--6. emp01테이블에서 커미션을 받지 못하는 모든 직원을 삭제하세요.
delete from emp01
where comm is null or comm = 0;


