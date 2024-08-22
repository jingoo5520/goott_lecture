-- 시퀀스
create table board (
    no number(6) primary key,
    title varchar2(100),
    writer varchar2(20)
);

drop table board;

-- 1) count
select count(*) + 1 from board;

insert into board values(1, '감기조심하세요', 'dooly');
insert into board values(2, '감기조심하세요2', 'ddochi');
insert into board values((select count(*) + 1 from board), '감기조심하세요3', 'huidong');

-- 2) max
select max(no) + 1 from board;
insert into board values((select max(no) + 1 from board), '감기조심하세요4', 'kildong');

-- 시퀀스 생성
--create sequence 시퀀스이름
--[INCREMENT BY n]
--[START WITH n]
--[MAXVALUE n | NOMAXVALUE]
--[MAXVALUE n | NOMINVALUE]
--[CYCLE | NOCYCLE]
--[CACHE n | NOCACHE]

create sequence seq_test
start with 1
increment by 1;



-- 시퀀스 목록 보기
select * from user_sequences;

select seq_test.currval from dual; -- 아직 정의되지 않음

insert into board values(seq_test.nextval, 'test1', 'dooly');

select seq_test.currval from dual; 
select seq_test.nextval from dual; -- 참조하는 것만으로도 증가 실행됨

select seq_test.currval from dual;
select seq_test.nextval from dual;

select seq_test.currval from dual;
select seq_test.nextval from dual;

-- 모니터 생산
create sequence seq_test2
start with 1
increment by 1
maxvalue 5
cycle
cache 2;

create table product (
    serialNo varchar2(10) constraint product_serialno_pk primary key,
    prodName varchar2(14)
);

select to_char(sysdate,'yymmdd') from dual;

insert into product values(to_char(sysdate,'yymmdd') || '-' || seq_test2.nextval, 'monitor');

select * from product;

insert into product values(to_char(sysdate,'yymmdd') || '-' || seq_test2.nextval, 'monitor');
insert into product values(to_char(sysdate,'yymmdd') || '-' || seq_test2.nextval, 'monitor');
insert into product values(to_char(sysdate,'yymmdd') || '-' || seq_test2.nextval, 'monitor');
insert into product values(to_char(sysdate,'yymmdd') || '-' || seq_test2.nextval, 'monitor');

select * from product; -- 5개

-- 에러발생, 1, 2, 3, 4, 5 다음 1이 추가되서 중복 에러 발생
insert into product values(to_char(sysdate,'yymmdd') || '-' || seq_test2.nextval, 'monitor');
select seq_test2.currval from dual; -- seq_test.currval = 1

-- 시퀀스 삭제
drop sequence seq_test;
drop sequence seq_test2;

-- 시퀀스를 삭제해도 이미 들어가있는 데이터는 변함없음




-- 인덱스
-- primary key (기본 키), 고유키(unique)일 경우에 자동으로 생성됨
-- 사용자가 특정 테이블의 열에 지정할 수도 있음

-- 조회
select * from user_indexes
where table_name = 'EMPLOYEES';

-- 생성
-- CREAT INDEX 인덱스 이름
-- ON 테이블 이름 (컬럼명)

select * from user_indexes where table_name = 'BOARD';

create index ind_board_writer
on board(writer);





