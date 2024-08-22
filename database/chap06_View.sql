-- 뷰
-- 테이블 조회를 위한 객체
-- 실제 데이터를 저장하는 개념이 아닌, 쿼리문을 저장하고 있는 객체
-- 가상 테이블이라 부르기도 함

-- 복잡한 쿼리문을 쉽게 사용
-- 직접적인 테이블 접근을 제한하기 위해 사용

select * from EMP_DETAILS_VIEW;

select view_name, text from user_views;

-- 뷰 내용
--SELECT
--  e.employee_id,
--  e.job_id,
--  e.manager_id,
--  e.department_id,
--  d.location_id,
--  l.country_id,
--  e.first_name,
--  e.last_name,
--  e.salary,
--  e.commission_pct,
--  d.department_name,
--  j.job_title,
--  l.city,
--  l.state_province,
--  c.country_name,
--  r.region_name
--FROM
--  employees e,
--  departments d,
--  jobs j,
--  locations l,
--  countries c,
--  regions r
--WHERE e.department_id = d.department_id
--  AND d.location_id = l.location_id
--  AND l.country_id = c.country_id
--  AND c.region_id = r.region_id
--  AND j.job_id = e.job_id;

-- 뷰 생성
--create [or replace] [force | noforce] view 뷰이름
--[(컬럼별칭1, 컬럼별칭2, ...)]
--as 저장할 쿼리문
--with check option
--with read only

-- 생성
create or replace view view_emp30
as 
select * from employees where department_id = 30;

-- 조회
select * from view_emp30;

-- 뷰에 원소 추가
insert into view_emp30 values(300, '길동', '고', 'GILDONG', '010.5555.5555', sysdate, 'PU_CLERK', 3000, null, 114, 30);
-- 뷰에 추가해도 뷰 쿼리문의 조건절에 의해 보이지는 않음
insert into view_emp30 values(301, '길서', '고', 'KILSEO', '010.5555.5555', sysdate, 'PU_CLERK', 3000, null, 114, 60);

insert into view_emp30 values(302, '길남', '고', 'GILNAM', '010.5555.1266', sysdate, 'PU_CLERK', 3000, null, 114, 250);

-- 뷰 원소 수정
update view_emp30 set phone_number = '010.5555.1255' where employee_id = 300;

-- 뷰 원소 삭제
-- 뷰에 없으므로 0개 삭제
delete from view_emp30 where employee_id = 302;

delete from view_emp30 where employee_id = 300;

delete from employees where employee_id = 302;

-- 뷰를 통해 볼 수 있는 컬럼에 대해서만 DML 실행
-- with check option
create or replace view view_emp90
as
select employee_id, first_name, last_name, email, job_id, hire_date, department_id from employees
where department_id = 90
with check option;

select * from view_emp90;
insert into view_emp90 values(303, '길동', '고', 'KILDONG', 'AD_VP', sysdate, 90);

-- 급여 수정
-- 없는 컬럼 취급, 에러발생
update view_emp90 set salary = 9000 where employee_id = 303;

-- 입사일 수정
update view_emp90 set hire_date = '05/12/31' where employee_id = 303;

-- 뷰 삭제
drop view view_emp90;

-- with read only
create view view_emp90
as select employee_id, first_name, last_name, email, job_id, hire_date, department_id from employees
where department_id = 90
with read only;

select * from view_emp90;

-- read only error
update view_emp90 set hire_date = '05/12/30' where employee_id = 303;

-- force 옵션으로 view 생성
drop table board;
drop table member;

-- 컴파일 오류와 함께 뷰가 생성되었습니다. 메세지 출력
-- 테이블이 없지만 강제로 view 생성
create or replace force view view_member
as select * from member;

create table member (
    userid varchar2(10)
);

select * from view_member;

insert into member values('abcd');


-- 뷰 컬럼에 별칭을 붙인다면, 해당 뷰에 대한 조건절은 컬럼별칭을 사용
create or replace view view_emp(사번, 이름, 급여, 부서번호)
as
select employee_id, first_name, salary, department_id from employees;

-- 에러 발생
select * from view_emp where department_id = 90;
select * from view_emp where 부서번호 = 90;

-- 부서별로 총 급여, 평균 급여로 뷰 생성
select department_id, sum(salary), avg(salary)
from employees
group by department_id;

-- group by 함수를 사용해서 view 생성시,
create or replace view view_sal
as
select department_id, sum(salary) 급여총액, avg(salary) 급여평균 from employees
group by department_id;

select * from view_sal;

-- data manipulation operation not legal on this view
update view_sal set 급여총액 = 20000 where department_id = 100;

-- 조인으로 뷰 생성
select e.employee_id, e.first_name, d.department_id, d.department_name
from employees e inner join departments d
on e.department_id = d.department_id;

create or replace view view_emp_dept
as
select e.employee_id, e.first_name, d.department_id, d.department_name
from employees e inner join departments d
on e.department_id = d.department_id;

select * from view_emp_dept;

-- 에러발생, 조인을 이용해 생성된 뷰에 행 추가 실패, DML - insert 불가
insert into view_emp_dept values(304, 'Dooly', 30, 'Purchasing');

-- 행 수정 정상 적용
update view_emp_dept set first_name = 'HUIDONG' where employee_id = 303;

-- 행 삭제 정상 적용
delete from view_emp_dept where employee_id = 303;

-- 1) 급여가 높은 순으로 5명만 출력
select employee_id, first_name, salary
from employees
order by salary desc;

-- rownum
-- 가상의 index 컬럼
create table test (
    userid number(3) primary key,
    userName varchar2(15)
);

drop table test;
insert into test values(1, 'user1');
insert into test values(2, 'user2');
insert into test values(3, 'user3');
insert into test values(4, 'user4');
insert into test values(5, 'user5');
insert into test values(10, 'user10');
insert into test values(9, 'user9');
insert into test values(8, 'user8');
insert into test values(7, 'user7');
insert into test values(6, 'user6');


select rownum, test.* from test;

-- 1부터 n까지 찍는건 가능
select rownum, test.* from test where rownum between 1 and 4;

-- n부터 n2까지 찍는건 빈 데이터 출력
select rownum, test.* from test where rownum between 5 and 9;

select * from test where rownum between 1 and 5;

select * from (select rownum, test.* from test) where rownum between 6 and 10;

select * from (select rownum rn, test.* from test) where rn between 6 and 10;

-- TOP-N
-- 의사열(pseudo column)이라 부름
-- 실제 테이블에 존재하지 않음
-- select 절에 의해 추출되는 데이터에 붙는 순번

-- 2) 1번 쿼리문에 rownum 적용
select employee_id, first_name, salary
from (select rownum rn, employee_id, first_name, salary
from employees)
where rn between 5 and 10
order by salary desc;

select rownum rn, employee_id, first_name, salary
from employees
order by salary desc;

create or replace view view_sal_top5
as
select employee_id, first_name, salary
from employees
order by salary desc;

select rownum, s.*
from view_sal_top5 s
where rownum < 6;

select rownum, s.*
from (select employee_id, first_name, salary
from employees
order by salary desc) s
where rownum < 6;

-- 입사일이 가장 최근인 7명을 출력
select employee_id, first_name, hire_date from employees
order by hire_date desc;

select rownum, h. *
from (select employee_id, first_name, hire_date from employees
order by hire_date desc) h
where rownum < 8;

-- 다음 8명 출력
select rownum, h. *
from (select rownum rn, employee_id, first_name, hire_date from employees
order by hire_date desc) h
where rn between 8 and 14;



select * from view_sal_top5;