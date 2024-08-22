
-- employees을 복사한 copyEMP 테이블을 생성
-- 제약조건까지는 복사 안됨
create table copyEMP
as (select * from employees);

create table copyEMP1
as select employee_id, first_name, salary, hire_date from employees 
where department_id = 30;

-- 행 추가
-- 자동 형변환
insert into copyemp1 values('300', '김삼백', 3000, '2001/01/01');

insert into copyemp1 values('301', '김삼백일', 3100, '2001-01-02');

select * from copyemp1;

-- 조건이 false인 경우 
-- 테이블의 구조만 복사, 만들어진 행은 없음
create table copyEMP3
as select * from employees
where 1 = 0;

-- 테이블 삭제
drop table copyemp;
drop table copyemp1;
drop table copyemp3;

-- 새로운 테이블 만들기
-- DDL
-- 1) creat
create table member01 (
    userid varchar2(12),
    passwd varchar2(16),
    name varchar2(10),
    mobile varchar2(13),
    birthday date,
    gender char(1)
);

drop table bookorder;
drop table book;
drop table member;

create table member (
    userid varchar2(12),
    passwd varchar2(16),
    name varchar2(12)
);


insert into member01 values('dooly123', '1234', '둘리', '010-1234-5678', '1998-01-01', 'M');
insert into member01 values('doner', '1234', '도우너', '010-5555-1212', '1998-12-01', 'M');

-- not enough values 에러 발생, value 개수 부족
insert into member01 values('huidong', '1234', '희동', '010-9999-1234', '2002-03-01');

-- too many values 에러 발생, value 개수 초과
insert into member01 values('huidong', '1234', '희동', '010-9999-1234', '2002-03-01', 'M', 'U');

-- 특정 컬럼에만 값을 넣고 싶을 때
-- 빈 곳에는 null이 들어감
insert into member01(userid, passwd, name) values('abc123', 1234, '홍길동');

-- value too large for column "HR"."MEMBER01"."NAME" (actual: 21, maximum: 10) 에러 발생
insert into member01(userid, passwd, name) values('cdf456', 1234, '김수한무거북이');

-- 2) Alter
create table emp01 (
    empno number(4),
    ename varchar2(10),
    sal number(6),
    hiredate date
);

-- 2-1) 테이블 새로운 컬럼 추가
alter table emp01 add (job varchar2(10));

-- member01 테이블에 hobby 컬럼을 추가하라.
-- 가변 문자열(크기 50)
alter table member01 add (hobby varchar(50));

-- 2-2) modify column: 기존 컬럼 수정
-- 해당 컬럼에 데이터가 없는 경우: 데이터 타입, 사이즈 모두 변경 가능 
-- 해당 컬럼에 데이터가 있는 경우: 데이터 타입, 사이즈 업 가능
alter table emp01
modify ename varchar2(6);

alter table member01
modify gender varchar2(1);

-- 다음 문장은 에러
-- column to be modified must be empty to change datatype
alter table member01
modify birthday varchar2(15);

-- 컬럼의 최대 byte가 8이었기 때문에 12 -> 10으로 줄이는건 가능
alter table member01
modify userid varchar2(10);

-- 이 경우에는 불가능, 최대 byte가 8인데 데이터 조건을 6byte로 바꾸려 함
alter table member01
modify userid varchar2(6);

-- 2-3) drop column: 기존 컬럼 삭제
alter table member01
drop column birthday;

-- 3) truncate table: 테이블 데이터 모두 삭제, 구조만 남아있음
truncate table member01;

-- 4) rename to: 테이블 이름 변경하기
rename member01 to members;

-- rename table, column
alter table members
rename column userid to memberId;

-- 5) drop table: 테이블 삭제하기
drop table emp01;
drop table members;







alter table member
add email varchar2(40);

alter table member
modify userid varchar2(16);

alter table member
drop column email;

alter table member 
rename column name to username;

rename member to members;
