-- 복합키
create table member (
    email varchar2(40),
    passwd varchar2(12) constraint member_passwd_nn not null,
    name nvarchar2(12) constraint member_name_nn not null,
    mobile char(13),
    addr nvarchar2(50),
    constraint member_combo_pk primary key(email, mobile)
);

-- 회원가입
-- 이메일, 모바일을 복합키로 둬서 둘 중 하나만 unique 하면 생성 가능
insert into member values('a@abc.com', '1234', '홍길동', '010-1234-5678', null);
insert into member values('a@abc.com', '1234', '홍길순', '010-3333-5678', null);
-- unique constraint (HR.MEMBER_COMBO_PK) violated
-- 복합키 모두 unique 하지 않다면 생성 불가
insert into member values('a@abc.com', '1234', '홍길서', '010-1234-5678', null);

-- 로그인 처리
-- 성공
select * from member where email = 'a@abc.com' and mobile = '010-1234-5678' and passwd = '1234';

-- 실패
select * from member where email = 'a@abc.com' and mobile = '010-1234-5678' and passwd = '1235';

-- alter table 테이블명
-- add [constraint 제약조건 이름] 제약조건타입(컬럼명);

-- gender 컬럼 추가
alter table member
add gender char(3);

-- 제약조건 추가
alter table member
add constraint member_gender_ck check(gender in ('남', '여'));

update member set addr = '서울특별시 구로구';

-- 제약조건 not null 추가
alter table member
modify addr constraint member_addr_nn not null;

-- 제약조건 이름 변경
alter table member
modify mobile constraint member_mobile_nn not null;


alter table member
rename constraint member_mobile_nn to member_phone_nn;

-- 제약조건 제거
alter table member
drop constraint member_addr_nn;

-- 제약 조건 일시적 비활성화
alter table member
disable constraint member_name_nn;

insert into member values('b@abc.com', '1234', null, '010-1111-1234', null, '남');

-- 제약 조건 비활성화 해제, 활성화
-- 테이블에 조건에 맞지 않는 데이터가 있는 경우 에러 발생
alter table member
enable constraint member_name_nn;

update member set name = '둘리' where email = 'b@abc.com';

alter table member
enable constraint member_name_nn;

-----------------------------------------

-- 컬럼 단위 제약 조건
create table emp01 (
    empno number(4) primary key,
    ename varchar2(10) not null,
    job varchar(9) unique,
    deptno number(4) references dept01(deptno)
);

-- 테이블 단위 제약 조건, not null 조건은 컬럼단위로만 가능
create table emp02 (
    empno number(4),
    ename varchar2(10) not null,
    job varchar(9),
    deptno number(4),
    primary key(empno),
    unique(job),
    foreign key(deptno) references dept01(deptno)
);

drop table member;

create table member (
    userid varchar2(12),
    pwd varchar2(15) constraint member_pwd_nn not null,
    constraint member_user_pk primary key(userid)
);

insert into member values('dooly', '1234');
insert into member values('ddochi', '1234');

create table board (
    no number(10),
    writer varchar2(12),
    title varchar2(40),
    constraint board_no_pk primary key(no),
    constraint board_writer_fk foreign key(writer) references member(userid) on delete cascade
);

insert into board values('1', 'dooly', '아싸1빠');

insert into board values('2', 'ddochi', '2빠');

insert into board values('3', 'dooly', '졸려유~');

select * from board order by no desc;

-- dooly가 탈퇴하면, on delete cacade 옵션 때문에
-- dooly가 쓴 글이 모두 삭제
delete from member where userid = 'dooly';

drop table board;
drop table member;


create table member (
    userid varchar2(12),
    pwd varchar2(15) constraint member_pwd_nn not null,
    constraint member_user_pk primary key(userid)
);

insert into member values('dooly', '1234');
insert into member values('ddochi', '1234');

create table board (
    no number(10),
    writer varchar2(12),
    title varchar2(40),
    constraint board_no_pk primary key(no),
    constraint board_writer_fk foreign key(writer) references member(userid) on delete set null
);

select sha1(12345) from dual;

insert into board values('1', 'dooly', '아싸1빠');

insert into board values('2', 'ddochi', '2빠');

insert into board values('3', 'dooly', '졸려유~');

-- dooly가 탈퇴하면, on delete set null 옵션 때문에
-- dooly가 쓴 글은 삭제되지 않음, writer는 null 이됨


delete from member where userid = 'dooly';

