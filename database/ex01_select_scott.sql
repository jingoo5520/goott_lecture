--1. 중복되는 컬럼을 한번씩만 출력하는 키워드는 ( distinct ) 이다.
--1-1. 사원테이블의 직무가 한번씩만 출력되도록 sql을 작성하세요
select distinct JOB
from EMP;
--2. 사원테이블의 모든 정보를 출력
select *
from EMP;
--3. 사원의 이름, 급여, 입사일 만을 출력
select ENAME, SAL, HIREDATE
from EMP;
--4. 사원들이 어떤 부서에 소속되어 있는지 소속 부서번호를 출력하되
--   중복되지 않고 한번씩만 출력
select distinct DEPTNO
from EMP;
--5. emp 테이블 중 부서번호가 10번인 사원의 모든 정보 출력
select *
from EMP
where DEPTNO = 10;
--6. 사원 테이블 중 급여가 2000 미만인 사원의 정보 중에서 사번, 이름, 급여를 출력하세요.
select EMPNO, ENAME, SAL
from EMP
where SAL < 2000;
--7. 이름이 'miller'인 사람의 사번, 이름, 직급을 출력하세요
select EMPNO, ENAME, JOB
from EMP
where ENAME = 'MILLER';
--8. 커미션이 300 또는 500 또는 1400인 사원의 사번, 이름, 커미션을 출력하세요
--   (1) or 연산자 이용
select EMPNO, ENAME, COMM
from EMP
where COMM = 300 or COMM = 500 or COMM = 1400;
--   (2) in 이용
select EMPNO, ENAME, COMM
from EMP
where COMM in(300, 500, 1400);
--9. 급여가 1500과 2500사이인 사원의 사번, 이름, 급여를 출력하세요
--   (1) and 이용
select EMPNO, ENAME, SAL
from EMP
where SAL >= 1500 and SAL <= 2500;
--   (2) between 이용
select EMPNO, ENAME, SAL
from EMP
where SAL between 1500 and 2500;
--10. 이름에 A를 포함하지 않는 사원의 사번, 이름을 출력하세요.
select EMPNO, ENAME
from EMP
where ENAME not like '%A%';
--11. 자신의 직속상관(mgr)이 없는 사원의 이름, 직급, 직속상관의 사번을 출력
select EMPNO, JOB, MGR
from EMP
where MGR is null;
--12. 사번, 이름, 급여를 급여가 높은 순으로 출력하세요
select EMPNO, ENAME, SAL
from EMP
order by SAL desc;
--13. 입사일이 가장 최근 순으로 사번, 이름, 입사일을 출력하세요
select EMPNO, ENAME, HIREDATE
from EMP
order by HIREDATE desc;