select * from member;
insert into member values('dooly');

rollback;

-- 세이브포인트 생성
savepoint afterInsert;

update member set userid = 'doochi' where userid = 'abcd';

-- 세이브포인트로 롤백
rollback to afterInsert;


-- 문제
-- 1) insert문
-- 2) savepoint a;
-- 3) delete문
-- 4) savepoint b;
-- 5) update문
-- 6) rollback to a;
-- 7) insert문
-- 8) savepoint c;
-- 9) delete문
-- 10) commit;

-- 위의 sql문을 순서대로 수정시, DB에 영구적으로 반영되는 문장은?
-- 하나의 행만 남음

