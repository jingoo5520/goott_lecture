--1. 급여가 3000에서 5000사이인 직원의 이름과 소속 부서명을 출력
select e.ename, d.dname, e.sal
from emp e, dept d
where e.deptno = d.deptno
and e.sal between 3000 and 5000;

select e.ename, d.dname, e.sal
from emp e inner join dept d
on e.deptno = d.deptno
where e.sal between 3000 and 5000;

select e.ename, d.dname, e.sal
from emp e inner join dept d
using(deptno)
where e.sal between 3000 and 5000;

select e.ename, d.dname, e.sal
from emp e natural join dept d
where e.sal between 3000 and 5000;

--2. 직급이 manager인 사원의 이름, 부서명을 출력하세요
select e.ename, d.dname
from emp e inner join dept d
on e.deptno = d.deptno
where e.job = upper('manager');

select e.ename, d.dname, e.job
from emp e, dept d
where e.deptno = d.deptno
and e.job = upper('manager');

--3. accounting 부서 소속 사원의 이름과 입사일 출력하세요
select e.ename, e.hiredate, d.dname
from emp e inner join dept d
on e.deptno = d.deptno
where d.dname = upper('accounting');

--4. 커미션을 받는 사원의 이름과 그가 속한 부서명을 출력
select e.ename, d.dname, e.comm
from emp e inner join dept d
on e.deptno = d.deptno
where comm is not null and comm != 0;

--5. 뉴욕에서 근무하는 사원의 이름과 급여를 출력
select e.ename, e.sal, d.loc
from emp e inner join dept d
on e.deptno = d.deptno
where d.loc = 'NEW YORK';

--6. 급여가 1200 이하인 사원 이름과 급여와 그가 근무하는 근무지를 출력하세요
select e.ename, e.sal, d.loc
from emp e inner join dept d
on e.deptno = d.deptno
where e.sal <= 1200;

--7. smith와 동일한 근무지에서 근무하는 사원의 이름을 출력하세요
select e.ename, d.loc
from emp e inner join dept d
on e.deptno = d.deptno
where e.deptno = (select deptno from emp where ename = upper('smith'))
and e.ename != upper('smith'); 
-- <> 연산자는 뭐지?
-- self join 도 사용 가능
--select worker.ename
--from emp worker inner join emp coworker
--on worker.deptno = coworker.deptno
--where worker.ename = coworker.deptno
--and coworker.ename != upper('smith'); 


--8. 매니저가 king인 사원들의 이름과 직급을 출력하세요
select worker.ename, worker.job, mgr.ename
from emp worker, emp mgr
where worker.mgr= mgr.empno and mgr.ename = upper('king');

--9. 월급이 2000 초과인 사원들의 부서번호, 부서이름, 사원번호, 사원이름, 월급을 출력하세요
select e.deptno, d.dname, e.empno, e.ename, e.sal
from emp e inner join dept d
on e.deptno = d.deptno
where e.sal > 2000;

--10. 각 부서별 평균 급여, 최대급여, 최소급여, 사원수를 출력하되, 부서번호, 부서이름도 같이 출력하세요.
select round(avg(e.sal), 2) as avg , max(e.sal) as max, min(e.sal) as min, count(*) empCount, e.deptno,
(select dname from dept where deptno = e.deptno) as dname
from emp e, dept d
where e.deptno = d.deptno
group by e.deptno;

--11. 모든 부서 정보와 사원 정보를 부서번호, 사원 이름순으로 정렬하여 출력하세요
select d.*, e.*
from dept d inner join emp e
on d.deptno = e.deptno(+)
order by d.deptno, e.ename;

select d.deptno, d.dname, d.loc, e.empno, e.ename, e.job, e.sal 
from emp e right outer join dept d
on e.deptno = d.deptno
order by d.deptno, e.ename;


--12. 모든 부서 정보, 사원 정보, 급여 등급 정보, 각 사원의 매니저의 정보를 
-- 부서번호, 사원 번호 순서로 정렬하여 다음과 같이 출력하세요.
--부서번호, 부서이름, 사원번호, 사원이름, losal, hisal, grade, 매니저번호, 매니저이름
select d.deptno, d.dname, e.empno, e.ename, s.losal, s.hisal, s.grade, e.mgr,
(select ename from emp where empno = e.mgr) as MNAME
from emp e inner join dept d on e.deptno = d.deptno inner join salgrade s on 
e.sal between s.losal and s.hisal;

select d.deptno, d.dname, e.empno, e.ename, s.losal, s.hisal, s.grade, m.mgr
from emp e right outer join dept d
on e.deptno = d.deptno
left outer join salgrade s
on e.sal between s.losal and s.hisal
left outer join emp m
on m.empno = e.mgr
order by d.deptno, e.empno;

