-- SELECT 문으로 특정 데이터 추출
-- SELECT * | [조회할 컬럼명1, 조회할 컬럼명2, 조회할 컬럼명3, ...]
-- FROM 테이블명;

-- countries 테이블의 모든 컬럼 조회
select * from countries;

select * from emp;

-- 부서 테이블의 모든 컬럼 조회
select * from departments;

-- 사원 블의 모든 컬럼 조회
select * from employees;

-- 부서 테이블에서 부서명만 조회
select department_name from departments;

-- 지역 테이블에서 도로명 주소만 조회

-- 사원 테이블에서 사원명, 급여 조회
select first_name, salary from employees;

-- 2. 컬럼명에 별칭 짓기 
select first_name as name from employees;

-- as 키워드 생략
select first_name 이름 from employees;

-- 별칭에 공백, 특수문자를 포함하는 경우 큰따옴표("")로 묶기
select first_name "이름" from employees;

-- 3. distinct 키워드: 중복된 데이터는 한 번씩만 출력
select distinct job_id from employees;

-- 4. where 절을 사용하여 조건 지정
select *
from employees
where employee_id <= 150;

-- 사원 테이블에서 이름이 adam인 사원의 사번, 이름, 입사일 조회
-- 문자열은 작은 따옴표를 이용해 표시하는데, 대/소문자 구분
select employee_id, first_name, hire_date 
from employees
where first_name = 'Adam';

-- 사원테이블에서 급여가 5000이상인 사원들의 사번, 이름, 급여 조회
select employee_id, first_name, salary
from employees
where salary >= 5000;

-- 지역테이블에서 지역 번호가 1800번 이하인 지역의 모든 컬럼 조회
select *
from locations
where location_id <= 1800;

-- 사원 테이블에서 입사일이 2002년 이전인 사원의 사번, 이름, 급여, 입사일 조회
select employee_id, first_name, salary, hire_date
from employees
where hire_date < '02/01/01';

-- 조건 연산자를 인결할 때 논리 연산자(AND, OR, NOT)를 사용할 수 있다.
-- 사번이 130번보다 작거나 급여가 5000 초과인 사원들의 사번, 급여 출력
select employee_id, salary
from employees
where employee_id < 130 or salary > 5000;

select empno, sal
from emp
where hiredate < '82/01/01' and sal > 2000;

-- 사번이 130번보다 작고 급여가 5000 초과인 사원들의 사번, 급여 출력
select employee_id, salary
from employees
where employee_id < 130 and salary > 5000;

-- 부서번호가 100번이 아닌 모든 사원들의 모든 컬럼을 조회
select * 
from employees
where department_id != 100;

-- between A and B: A 이상이고 B 이하
-- 급여가 5000이상, 7000이하인 사원들의 이름과 급여 출력
select first_name, salary
from employees
where salary between 5000 and 7000;

select ename, sal
from emp
where sal between 3000 and 7000;

-- 입사년도가 2002년에서 2005년인 사원들의 모든 정보 출력
select *
from employees
where hire_date between '02/01/01' and '05/01/01';

SELECT *
FROM emp
WHERE hiredate BETWEEN '80/01/01' AND '81/01/01';
-- 5) 패턴을 이용하여 검색하는 like 연산자
-- 컬럼명 like 패턴
-- 패턴은 아래의 2가지 이용 가능
-- '%' 문자가 없거나 하나 이상의 문자가 어떤 값이든 상관없이 검색
-- '_' 하나의 문자가 어떤 값이 오든 상관없이 검색

-- 이름이 's'로 시작하는 모든 사원들의 정보를 출력
select *
from employees
where first_name like 'S%';

-- 이름이 'n'로 시작하는 모든 사원들의 정보를 출력
select *
from employees
where first_name like '%n';

-- 이름의 끝에서 두 번째 글자가 'a'인 모든 사원들의 정보를 출력
select *
from employees
where first_name like '%a_';

-- 이름의 끝에서 두 번째 글자가 'E'인 모든 사원들의 정보를 출력
select *
from emp
where ename like '%E_';

-- null을 위한 연산자
-- 커미션을 받는 사원들의 모든 정보 출력
select *
from employees
where commission_pct is not null;



-- 7. 정렬을 위해서는 order by 절 사용
-- 사원들을 급여 내림차순으로 정렬
-- desc(내림 차순) asc(오름 차순:default)
select *
from employees
order by salary;

-- 정렬은 콤마','로 구분하여 어러개 사용 가능
-- 부서번호가 10번인 사원들의 모든 정보를 출력하라.
-- 급여 오름차순으로 정렬하고, 급여가 같은 경우 이름 내림차순으로 정렬
select *
from employees
where department_id = 50
order by salary, first_name desc;

-- 4) in (A, B, C) 연산자 : A 또는 B 또는 C
-- 부서번호가 10번 또는 50번또는 100번인 사원들의 모든 정보 출력
select * from employees where department_id in (10, 50, 100);
select * from employees where department_id = 10 or department_id = 50 or department_id = 100;

-- 부서번호가 10번 또는 50번또는 100번인 사원들의 모든 정보 출력
select * from emp where deptno in (10, 20);
