-- join
-- 두개 이상의 테이블에서 결과를 얻기 위한 sql문

-- 사번이 100번인 사원의 정보(사번, 이름, 부서번호)와 그가 속한 부서명까지 출력

select employee_id, first_name, employees.department_id, department_name
from employees, departments
where employees.department_id = departments.department_id
and employee_id = 100;

select * from emp;
select e.empno, e.ename, e.deptno, d.dname
from emp e join dept d
on e.deptno = d.deptno
where e.empno = 7844;

-- CEO가 휴가철을 맞이하여 모든 직원에게 선물을 택배로 보내려 한다.
-- 모든 직원들이 택배를 무사히 받을 수 있도록
-- 사무실의 주소, 사원 정보 출력

select e.employee_id, e.first_name, e.phone_number, street_address, country_name, r.region_name
from employees e, departments d, locations l, countries c, regions r
where e.department_id = d.department_id
and d.location_id = l.location_id
and l.country_id = c.country_id
and c.region_id = r.region_id;


-- -----------------------------------------------------------------------------
-- 조인의 종류
-- -----------------------------------------------------------------------------

-- 1) Cross Join (교차조인): 단순하게 두 개 이상의 테이블을 곱연산
select * from employees, departments;
select count(*) from employees, departments;
select count(*) from employees;
select count(*) from departments;
select 107 * 27 from dual;

select * from emp, dept;

-- 2) Equi Join (등가 조인): 가장 많이 사용
select e.first_name, d.department_name
from employees e, departments d
where e.department_id = d.department_id;

select e.empno, e.ename, e.deptno, d.dname
from emp e join dept d on e.deptno = d.deptno
where e.empno = 7844;


-- 3) Non-Equl Join(비등가 조인)
-- 조인할 테이블 사이의 컬럼 값이 직접적으로 일치하지 않을 때 사용

-- 예시
-- scott 접속
select e.empno, e.ename, e.sal, s.grade
from emp e join salgrade s
on e.sal between s.losal and s.hisal;

select e.empno, e.ename, e.sal, s.grade
from emp e, salgrade s
where e.sal between s.losal and s.hisal;
-- losal, hisal 이 grade에 따라 나눠져있고, 서로의 범위가 겹치지않음



-- 4) Self join
-- 하나의 테이블에서 조인을 하여 원하는 데이터를 얻는 조인
-- null 요소 제거
select worker.employee_id,  worker.first_name || '의 직속상사는' || manager.first_name || '입니다.' as 직속상사
from employees worker, employees manager
where worker.manager_id = manager.employee_id
order by worker.employee_id;

-- 사원의 정보와 매니저 이름을 출력
select e.empno, e.ename, e.mgr, m.ename as mname
from emp e join emp m
on e.mgr = m.empno;

-- 5) outer join(외부 조인)
-- 행이 조인 조건에 만족하지 않는 경우(null 등)
-- 조인 조건에 만족하지 않는 행들도 나타내고 싶을 때 사용

select * from dept;

SELECT e.empno, e.ename, e.deptno, d.dname
FROM emp e
right JOIN dept d ON e.deptno = d.deptno;

SELECT d.deptno, d.dname, e.empno, e.ename
FROM dept d
left JOIN emp e ON d.deptno = e.deptno;

-- left outer join(왼쪽 외부 조인)
-- 아무 부서에도 배치되지않은 사원까지 조회
select e.first_name, d.department_name
from employees e, departments d
where e.department_id = d.department_id(+);

-- right outer join(오른쪽 외부 조인)
-- 아무 사원도 배치되지 않은 부서까지 조회
select e.first_name, d.department_name
from employees e, departments d
where e.department_id(+) = d.department_id;

-- 사원과 소속 부서를 출력하되, 소속된 사원이 없는 부서도 출력
select e.ename, d.dname
from emp e right join dept d
on e.deptno = d.deptno;

-- 양쪽에 다 outer join을 할 순 없음, 에러 발생

-- ANSI join : ANSI에서 제정한 DBMS와의 호환성을 위해 만든 것
-- 1) ANSI Cross join
select count(*) from employees cross join departments;

-- 2) ANSI INNER join: = equi join
select e.first_name, d.department_name
from employees e inner join departments d
on e.department_id = d.department_id
where first_name like '%n';

-- using() 절을 이용하여 join 조건 지정
-- 조인되는 컬럼명이 동일 해야함
-- using 절에는 테이블 별칭 사용 불가, 괄호 생략 불가
select e.first_name, d.department_name
from employees e inner join departments d
using(department_id);

-- natural join
-- 조인이 되는 컬럼명이 동일할 때
-- DBMS가 알아서 매칭
select e.employee_id, e.first_name, d.department_name
from employees e natural join departments d;

-- 기대와 다른 결과값이 나오는 이유
-- 동일한 컬럼명이 두 개 이상이고, 두 조건에 모두 맞는 경우(AND)만 출력

-- 3) ANSI outer join 
select e.first_name, d.department_name
from employees e, departments d
where e.department_id = d.department_id(+);

-- 왼쪽 테이블(사원)에 누락된 정보를 모두 보여줘라
select e.first_name, d.department_name
from employees e left outer join departments d
on e.department_id = d.department_id;

-- 왼쪽 컬럼(사원 이름)은 모두 보여줘라
select e.first_name, d.department_name
from employees e right outer join departments d
on e.department_id = d.department_id;

-- 양쪽 컬럼 모두 보여줘라. ANSI는 가능
select e.first_name, d.department_name
from employees e full outer join departments d
on e.department_id = d.department_id;



-- 조인 문장이 지켜야 할 조건
-- 1) 기본키(primary key), 외래키(foreign key)컬럼을 통해서 서로 다른 테이블을 연결
-- 필수사항은 아님
-- 2) where 절을 사용하여 조인 조건을 기술(조인 조건의 개수 = 연결하는 테이블 수 - 1)
-- 3) 명확성을 위해 컬럼명 앞에 테이블 별칭 기술

