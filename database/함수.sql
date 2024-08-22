select 5 + 3 from employees;

-- 가상의 테이블을 이용하여 단순 입력에 대한 출력 결과 확인 가능
select 5 + 3 from dual;

select * from employees;

-- 단일 행 함수
-- 행마다 함수가 실행되는 함수(테이블 행의 개수=결과 개수)
-- ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
-- (1) 문자 함수
-- ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
-- 1) lower(): 소문자 변환;
select first_name from employees;

select 'DataBase', lower('DataBase') from dual;


-- 이름이 lex인 사원의 모든 정보 출력
-- 소문자 'lex' 만 검색
select * from employees where first_name = 'lex';
-- 대소문자 'lex' 검색
select * from employees where lower(first_name)= 'lex';

-- 2) upper(): 대문자 변환
select 'DataBase', upper('DataBase') from dual;
select * from employees where upper(first_name)= 'LEX';

-- 직급이 'it_prog'인 사원의 모든 정보를 출력
select * from employees where job_id = upper('it_prog');
select * from employees where lower(job_id) = 'it_prog';

-- 3) initcap(): 첫 글자만 대문자로, 나머지는 소문자로 변환
select 'database', initcap('DataBase') from dual;

-- 이름이 lex인 사원의 모든 정보 출력
select * from employees where initcap(first_name) = 'Lex';
select * from employees where first_name = initcap('lex');

-- 4) 문자를 연결하는 concat 함수
select concat('data', 'base') from dual;
select concat('data', 'base', 'oracle') from dual;  -- error, 매개변수는 2개만
select concat('data', concat('base', 'oracle')) from dual; -- 매개변수가 여러개인 경우
select 'data' || 'base' || 'oracle' from dual; -- 문자 결합 방법2, 개수 제한 없음

-- 모든 사원의 이름을 '성, 이름' 형태로 출력하고 column 명을 full_name 으로 출력하라.
select last_name || ',' || first_name as full_name from employees;

-- 5) length(): 문자의 길이를 구하는 함수
select length('database') from dual;
select length('데이터베이스') from dual;
select lengthb('database') from dual; -- 8
select lengthb('데이터베이스') from dual; -- 18
-- 오라클에서 영문자는 한 자당 1byte, 한글은 한 글자당 3byte

-- 이름이 6글자 이하인 사람들의 이름을 소문자로 출력하세요
select lower(first_name) from employees where length(first_name) <= 6;

-- 6) substr(): 문자열의 일부를 추출하는 함수
-- oracle은 zero-base 아님, 1부터 시작
-- substr(str, startIndex, length)
select substr('database', 1) from dual; -- database
select substr('database', 1, 4) from dual; -- data
select substr('database', -4, 4) from dual; -- base

-- 입사연도가 2005년인 사원들의 모든 정보를 출력
select * from employees where substr(hire_date, 1, 2) = '05';

-- 7) instr(): 특정 문자의 위치를 반환
-- instr(str, string, (startIndex))
select instr('database', 'b') from dual; -- 5
select instr('database', 'a') from dual; -- 2, 첫 번째 a
select instr('database', 'a', 5) from dual; -- 6, 5번째 문자부터 검색, 세 번째 a 찾음

-- 이름의 세 번째 자리가 i인 사원들의 모든 정보 출력
select * from employees where instr(first_name, 'i') = 3;
select * from employees where substr(first_name, 3, 1) = 'i';
select * from employees where first_name like '__i%';

-- 8) trim(): 문자열의 앞 뒤의 특정 문자를 잘라주는 함수
select trim('a' from 'aaaaDataBaseaaaa') from dual; -- DataBase
select trim(' ' from 'Data Base') from dual; -- Data Base
select trim('      Data Base       ') from dual; -- Data Base

select trim('database') from dual;

-- 8-1) ltrim(): 문자열 기준 왼쪽만 잘라줌
select '0001000', ltrim('0001000', '0') from dual; -- 1000

-- 8-2) rtrim(): 문자열 기준 오른쪽만 잘라줌
-- trim(option, del string, str)
-- option: laeding, trailing, both
select '0001000', rtrim('0001000', '0') from dual; -- 0001

select rtrim('Database  ') from dual; -- Database

select trim(leading '_' from '__Database__') from dual; -- Database
-- 8-3) 여러 옵션들
select '[' || trim('_' from '__database__') || ']' from dual; -- [database]
-- leading: 문자열 왼쪽만 잘라줌 = ltrim()
select '[' || trim(leading '_' from '__database__') || ']' from dual; -- [database__]
-- trailing: 문자열 오른쪽만 잘라줌 = rtrim()
select '[' || trim(trailing '_' from '__database__') || ']' from dual; -- [__database]
-- both: 문자열 양쪽 잘라줌

-- 9) replace()
-- replace(str, 교체할 문자, (대체 문자)
select '010-5555-1234', replace('010-5555-1234', '-', ' ') from dual;
select '010-5555-1234', replace('010-5555-1234', '-') from dual;

-- 10) lpad, rpad
-- 10-1) lpad(str, length, 채울 문자)
select lpad('DataBase', 10, '$') from dual; -- $$DataBase

-- 10-2) rpad()
select rpad('DataBase', 10, '@') from dual; -- DataBase@@



-- ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
-- (2) 숫자 함수
-- ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
-- 1) abs(): 절대값을 구하는 함수
select abs(-15) from dual; -- 15

-- 2) floor(): 소수점 내림
select floor(3.141592) from dual; -- 3

-- 2-1) ceil(): 소수점 올림
select ceil(3.141592) from dual; -- 4

-- 3) round(): 특정 자리수에서 반올림하는 함수
select round(3.141592, 2) from dual; -- 3.14
select round(3.141592, 3) from dual; -- 3.142
-- 정수에도 사용
select round(314.1592, -2) from dual; -- 300

-- 4) trunc(): 특정 자릿수에서 잘라냄(그냥 잘라냄)
select trunc(3.141592, 2) from dual; -- 3.14
select trunc(3.141592, 4) from dual; -- 3.1415
select trunc(314.1592, -2) from dual; -- 300

-- 5) mod(): 나머지 값을 반환하는 함수
select mod(34, 2) from dual; -- 0
select mod(34, 3) from dual; -- 1

-- 6) sign(): 양수(1), 음수(-1), 0(0), 구분
select sign(10) from dual; -- 1
select sign(-10) from dual; -- -1
select sign(0) from dual; -- 0

-- 7) power(): 거듭 제곱
select power(2, 3) from dual; -- 2^3 = 8

-- 8) sqrt(): 제곱근
select sqrt(10) from dual;

-- 문제) 사원들의 연봉을 구하려고 한다.
-- 연봉 = salary * 12 + ((salary * 12) * comm)
-- = (salary * 12) * (1 + comm)
-- 연봉을 구해 소수점 이하 2자리까지만 출력
-- 연봉 컬럼의 컬럼명은 annual salary 라고 출력
-- 사번, 이름, 연봉(annual salary)

select employee_id, first_name, trunc(((salary * 12) * (1 + commission_pct)), 2) as "annual salary"
from employees;



-- ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
-- (3) 날짜(시간) 함수: 데이터 타입이 DATE인 데이터를 대상으로 하는 함수
-- ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
-- 1) 현재 날짜를 반환하는 sysdate
select sysdate() from dual;
select sysdate + 1 as 내일 from dual;

select SYSDATETIME from dual;

-- 사원들이 입사일로부터 현재까지 몇 일 지났는지 구해보자
select first_name, floor((sysdate - hire_date)) || '일 지남' as "입사일로부터 지난 날" from employees;

-- 2) months_between(): 두 날짜 사이의 간격(개월수)를 계산
select first_name, hire_date, (months_between(sysdate, hire_date)) from employees;

-- 3) add_months(): 개월 수를 더하는  함수
select first_name, hire_date, add_months(hire_date, 3) from employees;

-- 4) next_day(): 해당 요일에 가장 가까운 날짜 반환
select sysdate, next_day(sysdate, '월요일') from dual;
select sysdate, next_day(sysdate, 'Friday') from dual; -- error, 한국어 설정

-- 5) last_day(): 해당 달의 마지막 날짜 반환
select sysdate, last_day(sysdate) from dual;

-- 6) round(): 기준을 정해 반올림
select sysdate, round(sysdate, 'month') from dual;

select sysdate, round(to_date('21-02-16 22:00:00'), 'month') from dual;

select sysdate, round(sysdate, 'year') from dual;

-- 7) trunc(): 기준을 정해 버림
select sysdate, trunc(sysdate, 'month') from dual;

select sysdate, trunc(sysdate, 'year') from dual;


-- ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
-- (4) 변환 함수: 문자, 숫자, 날짜 데이터를 서로 다른 타입의 데이터로 변환
-- ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

-- 1) to_char(): 숫자, 날짜 데이터를 문자열로 변환
-- to_char(날짜, '출력형식')
select sysdate, to_char(sysdate, 'yyyy-mm-dd') from dual;
select sysdate, to_char(sysdate, 'yyyy. mm. dd') from dual;
select sysdate, length(to_char(sysdate, 'yyyy. mm. dd')) from dual;
select sysdate, to_char(sysdate, 'yyyy-mm-dd dy am hh24:mi:ss') from dual;
select to_char(123456, '9999999') from dual; -- 9는 자리수를 나타내며 자리수가 맞지 않으면 채우지는 않는다.
select to_char(123456, '0000000') from dual; -- 0은 자리수를 나타내며 자리수가 맞지 않으면 0으로 채운다.

-- L999999
select first_name, salary, to_char(salary, '$999,999') from employees;

-- 2) to_date(): 숫자, 문자 데이터를 날짜형으로 변환
-- 입사일이 2006년 1월 3일인 사원을 검색
select first_name, hire_date from employees where hire_date = '06/01/03'; 
select first_name, hire_date from employees where hire_date = to_date(20060103); 

select to_date('2010-07-14', 'yyyy-mm-dd') from dual;

-- 3) to_number(): 숫자형으로 변환
select '10000' + '20000' from dual;
select '10,000' + '20,000' from dual; -- error
-- to_number(문자, 문자의 패턴)
select to_number('10,000', '999,999') + to_number('20,000', '999,999') from dual;

-- ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
-- (5) 기타 함수
-- ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

-- 1) nvl(): 첫 번째 인자로 받은 값이 null인 경우 두 번째 인자의 값으로 변경
-- 문제) 사원들의 연봉을 구하려고 한다.
-- 연봉 = salary * 12 + ((salary * 12) * comm)
-- = (salary * 12) * (1 + comm)
-- 연봉을 구해 소수점 이하 2자리까지만 출력
-- 연봉 컬럼의 컬럼명은 annual salary 라고 출력
-- 사번, 이름, 연봉(annual salary)

select employee_id, first_name, trunc(((salary * 12) * (1 + nvl(commission_pct, 0))), 2) as "annual salary"
from employees;

-- 2) decode()
select first_name, department_id, 
decode(department_id, 90, 'Executive',
60, 'IT',
100, 'Finance',
'default'
) as "부서이름"
from employees;

-- 3) case 함수
select first_name, department_id, 
case when department_id = 90 then 'Executive'
     when department_id = 100 then 'Finance'
     when department_id = 60 then 'IT'
     else 'default'
     end as "부서명"
    from employees;
    
-- 4) rank() 함수
select first_name, salary, 
rank() over (order by salary desc) as "rank",
dense_rank() over (order by salary desc) as "dense_rank",
row_number() over (order by salary desc) as "row_number" 

from employees;

-- ----------------------------------------------------------------
-- 그룹 함수
-- 테이블 전체에서 한 번 실행되는 함수(결과 1개)
-- ----------------------------------------------------------------
-- 1) sum()
select sum(salary) from employees;
select first_name, sum(salary) from employees; -- error: not a single-group group function

-- 2) avg()
select to_char(round(avg(salary), 2), 'L999,999,99') from employees;

-- 3) max(), min(): 최대, 최소값
select max(salary), min(salary) from employees;

-- 4) count(): 행의 개수를 세어주는 함수
-- null 인 데이터는 세지 않음
select count(*) as "전체직원수" from employees;
select count(commission_pct) as "커미션을 받는 직원수" from employees;
select count(department_id) from employees;

-- 5) stddev(): 표준 편차
select stddev(salary) from employees;
-- 6) variance(): 분산

select variance(salary) from employees;
select sqrt(variance(salary)) from employees;

-- group by 절
-- group by 컬럼명

-- 소속 부서별 급여 총액과, 급여 평균을 구하라
select department_id, sum(salary), avg(salary) from employees
group by department_id;

-- 직무별 급여 총액과 급여 평균
select job_id, sum(salary) as "합계", avg(salary) from employees
group by job_id
order by 합계 desc;

select department_id, sum(salary), avg(salary) from employees
where avg(salary) >= 5000
group by department_id; -- error: group function is not allowed here

-- having 절: group 절에서 컬럼에 조건을 부여할 때
select department_id, avg(salary) 
from employees
group by department_id
having avg(salary) >= 5000;

-- where 절 사용 방법
-- 그룹화를 시킨 컬럼은 where 절로 조건을 제한하지 못함
select job_id, department_id, sum(salary), avg(salary)
from employees
where department_id between 50 and 100
group by job_id, department_id
order by job_id;

-- 직급별(job_id) 급여 최대값과, 급여 최소값을 구하되,
-- 최대 급여가 7000 이상인 부서만 출력하세요.

select job_id, max(salary), min(salary)
from employees
group by job_id
having max(salary) >= 7000
order by max(salary);

select concat(first_name, last_name) as full_name
from employees
order by full_name;

select job_id, count(job_id) as cnt_job_id, department_id, sum(salary), avg(salary)
from employees
where department_id between 50 and 100
group by job_id, department_id
having avg(salary) >= 7000
order by cnt_job_id;

