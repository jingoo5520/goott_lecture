--## DDL문 연습문제

-- 1. Scott계정의 emp테이블의 사번, 이름, 직급, 매니저 컬럼과 동일한 컬럼명과 유형을 갖는 테이블을 emp01이라는 이름으로 생성하세요.
create table emp01
as select empno, ename, job, mgr from emp;

-- 2. 아래의 구조로 dept01 테이블을 생성하세요.
---- deptno 숫자 2자리, dname 가변길이문자 14자리, loc 가변길이문자 13자리
create table dept01 (
    deptno number(2),
    dname varchar2(14),
    loc varchar2(13)
);

-- 3. 이미 존재하는 emp01테이블에 날짜시간을 저장할 수 있는 타입으로 hiredate 컬럼을 추가하세요.
alter table emp01 add (hiredate date);

-- 4 emp01테이블의 직급 컬럼의 크기를 20으로 변경하세요
alter table emp01
modify job varchar2(20);

-- 5. emp01테이블의 매니저 컬럼을 삭제하세요.
alter table emp01
drop column mgr;

-- 6. emp01테이블의 이름을 emp02로 변경하세요
rename emp01 to emp02;

-- 7. emp02테이블과 dept01 테이블을 삭제하세요.
drop table emp02;
drop table dept01;
