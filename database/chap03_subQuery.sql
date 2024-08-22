-- 서브 쿼리

-- 100번 사원이 소속되어 있는 부서의 부서명을 알아보자.
-- 1) 100번 사원이 소속되어있는 부서번호를 알아낸다. (employees 테이블)
select department_id 
from employees
where employee_id = 100;

select deptno
from emp
where empno = 7369;

-- 2) 1번에서 알아낸 부서번호의 부서 이름을 알아낸다(dept 테이블)
select department_name from departments where department_id = 90;

-- 서브쿼리 사용
-- 두 번 요청을 한 번의 요청으로 줄이고, 서버 부하를 줄임
-- 세미콜론(';') 하나당 한 번의 요청이라 생각하면 됨
select department_name from departments
where department_id = (select department_id 
from employees
where employee_id = 100);

-- 사번, 이름, 사언들이 소속되어있는 부서의 부서명을 출력
select employee_id, first_name, department_id
from employees, departments
where department_id = (select department_id from employees);
-- 서브쿼리는 메인 쿼리문의 테이블에 있는 컬럼 정보밖에 얻지 못함
-- 위 문제를 해결하기 위해 조인(join) 사용

-- executive 부서의 국가코드, 주, 시, 도로명 주소를 출력
-- 서브쿼리
select country_id, state_province, city, street_address
from locations
where location_id = (select location_id from departments where department_name = 'Executive');

-- empno = 7782 인 사원이 소속되어 있는 부서의 부서명 출력
-- 1) empno = 7782 인 사원이 소속되어 있는 부서 찾기
select deptno 
from emp 
where empno = 7782; -- 10

-- 2) 찾아낸 부서의 이름을 출력
select dname 
from dept 
where deptno = 10;

-- 3) 서브쿼리 사용
select dname 
from dept 
where deptno = (select deptno from emp where empno = 7782);

-------------------------------------------------------------------------------------
-- 서브 쿼리의 종류
-------------------------------------------------------------------------------------
-- 1) 단일형 서브쿼리(single row subquery): 
-- 내부 select 문장으로 부터 하나의 행만 반환 받음
-- 사용 가능한 연산자로는 =, <, <=, >, >=, != 가 있음

-- 문제) 
-- 'diana' 와 같은 부서에 다니는 동료들의 모든 정보 출력
select * from employees where department_id = (select department_id from employees where first_name = initcap('diana'));

-- 사원들의 평균 급여보다 더 많은 급여를 받는 사원의 사번, 이름, 급여 출력
select employee_id, first_name, salary from employees where salary > 
(select avg(salary) from employees);

select * from emp;

-- 평균 이상의 급여를 받는 사원의 사번, 이름, 급여 출력
select empno, ename, sal from emp
where sal > (select avg(sal) from emp);

-- 2) 다중행 서브쿼리
-- 서브쿼리에서 반환되는 행의 개수가 2개 이상인 경우
-- 사용 연산자
-- in: 메인 쿼리의 비교 조건이 서브쿼리의 결과 중 하나라도 일치하면 참
-- any: 메인 쿼리의 비교 조건이 서브쿼리의 결과와 하나 이상이 일치하면 참
-- all: 메인 쿼리의 비교 조건이 서브쿼리의 결과와 모든 값이 일치하면 참

-- 급여를 7000 이상 받는 사원이 소속된 부서와 동일한 부서에서 근무하는 사원들의 정보를 출력
-- 1) 급여를 7000 이상 받는 사원이 소속된 부서
select department_id from employees where salary >= 7000;
-- 2) 
select * from employees where department_id in 
(select department_id from employees where salary >= 7000);

select * from employees where department_id in 
(select distinct department_id from employees where salary >= 12000);

-- 급여를 1000 이하로 받는 사원이 소속된 부서에서 근무하는 사원들의 정보 출력
select empno, ename, deptno from emp where deptno 
in (select deptno from emp where sal <= 1000);

select empno, ename, deptno from emp where sal <= 1000;

select * from emp;
select * from dept;

-- 'SALES' 부서에서 근무하는 직원들의 정보를 조회
select empno, ename
from emp
where deptno = (select deptno from dept where dname = 'SALES');



-- all 연산자
-- 문제) 30번 부서에 소속된 사원 중에서 급여를 가장 많이 받는 사원보다
-- 더 많이 받는 사원의 이름, 급여를 출력
-- all 연산자 미사용
select first_name, salary from employees
where salary > 
(select max(salary) from employees where department_id = 30 );

-- all 연산자 사용
select first_name, salary from employees
where salary > all (select salary from employees where department_id = 30);

-- 30번 부서에 소속된 모든 사원들 보다 급여를 많이 받는 사원 정보 출력
select empno, ename, sal
from emp
where sal > (select max(sal) from emp where deptno = 30);

select sal from emp where deptno = 30;

-- 아래와 동일, 모든 요소에 대한 조건을 만족해야함
-- 이 경우에는 11000 보다 크면 다른 조건을 모두 충족
select first_name, salary from employees
where salary > all (11000, 3100, 2900, 2800, 2600, 2500);

select salary from employees where department_id = 30; -- (11000, 3100, 2900, 2800, 2600, 2500)

-- any 연산자
-- < any()
select first_name, salary from employees where salary < any(4000, 6000, 9000, 12000);

-- MANAGER 직업을 가진 사원들 보다 낮은 급여를 받는 사원들 출력
-- = MANAGER 직업을 가진 사원들 중 급여가 가장 높은 사원 보다 낮은 급여를 받는 사원들 출력
select empno, ename, job, sal
from emp
where sal < any(select sal from emp where job = 'MANAGER');

select * from emp;

-- > any()
select first_name, salary from employees where salary > any(4000, 6000, 9000, 12000);

-- = any()
select first_name, salary from employees where salary = any(4000, 6000, 9000, 12000);
select first_name, salary from employees where salary in (4000, 6000, 9000, 12000);

-- 문제) 30번 부서에 소속된 사원 중에서 급여를 가장 적게 받는 사원보다
-- 더 많이 받는 사원의 이름, 급여를 출력하세요.
select first_name, salary 
from employees 
where salary > any (select salary from employees where department_id = 30);

-- 다중열 서브쿼리
select * from employees
where (department_id, salary)
in (select department_id, max(salary) from employees
group by department_id);

-- 부서별로 가장 급여가 많은 사원의 모든 정보 출력
select * from emp
where (deptno, sal)
in (select deptno, max(sal) from emp group by deptno);

select * from emp
where sal =

group by deptno;


select employee_id, first_name, salary, department_id, 
(select department_name from departments where employees.department_id = departments.department_id)
from employees;
