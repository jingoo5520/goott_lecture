--추가 연습문제
--1. 전체 사원 중 ALLEN과 같은 직무(JOB)인 사원들의 
--직무, 사원번호, 사원이름, 월급, 부서번호, 부서이름을 출력하세요.
select job, empno, ename, sal, e.deptno, dname
from emp e, dept d
where e.deptno = d.deptno and job = (select job from emp where ename = 'ALLEN');

--2. 전체 사원의 평균 급여보다 높은 급여를 받는 사원들의 
--사원 정보, 부서정보, 급여 등급 정보(SALGRADE 테이블참고)를 출력하세요. 
--단, 출력할 때 급여가 많은 순으로 정렬하되 급여가 같을 경우 사원번호를 기준으로 오름차순 정렬하세요
select empno, ename, e.deptno, dname, sal, grade
from emp e, dept d ,salgrade s
where e.deptno = d.deptno
and s.losal <= e.sal and s.hisal >= e.sal
and sal > (select avg(sal) from emp)
order by sal desc, empno asc;


--3. 10번 부서에 근무하는 사원 중 30번 부서에는 존재하지 않는 직무(JOB)을 가진 사원들의
--사원 번호, 이름, 직무, 부서번호호, 부서이름, LOC를 출력하세요.

select empno, ename, job, e.deptno, dname, loc
from emp e, dept d
where e.deptno = d.deptno
and e.deptno = 10
and e.job not in (select job from emp where deptno = 30);


--4. 직무가 salesman인 사람들의 최고 급여보다 높은 급여를 받는 사원들의
--사원번호, 이름, 월급, GRADE(SALGRADE 테이블 참고)를 출력하세요.

select e.empno, e.ename, e.sal, s.grade
from emp e, salgrade s
where s.losal <= e.sal and s.hisal >= e.sal
and e.sal > all(select sal from emp where job = upper('salesman'));

select e.empno, e.ename, e.sal, s.grade
from emp e, salgrade s
where s.losal <= e.sal and s.hisal >= e.sal
and e.sal > (select max(sal) from emp where job = upper('salesman'));