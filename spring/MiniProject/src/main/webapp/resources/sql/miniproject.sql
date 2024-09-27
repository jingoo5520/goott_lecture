-- member 테이블 생성
CREATE TABLE `member` (
  `userId` varchar(20) NOT NULL,
  `userPwd` varchar(200) NOT NULL,
  `userName` varchar(12) DEFAULT NULL,
  `mobile` varchar(13) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `hobby` varchar(60) DEFAULT NULL,
  `registerDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `userImg` varchar(50) NOT NULL DEFAULT 'avatar.png',
  `userPoint` int DEFAULT '100',
  PRIMARY KEY (`userId`),
  UNIQUE KEY `mobile_UNIQUE` (`mobile`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- hborad 테이블 생성
CREATE TABLE `my`.`hboard` (
  `boardNo` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(20) NOT NULL,
  `content` VARCHAR(2000) NULL,
  `writer` VARCHAR(8) NULL,
  `postDate` DATETIME NULL DEFAULT now(),
  `readCount` INT NULL DEFAULT 0,
  `ref` INT NULL DEFAULT 0,
  `step` INT NULL DEFAULT 0,
  `refOrder` INT NULL DEFAULT 0,
  PRIMARY KEY (`boardNo`),
  INDEX `hboard_member_fk_idx` (`writer` ASC) VISIBLE,
  CONSTRAINT `hboard_member_fk`
    FOREIGN KEY (`writer`)
    REFERENCES `my`.`member` (`userId`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION)
COMMENT = '계층형게시판';

CREATE TABLE `my`.`pointdef` (
  `pointWhy` VARCHAR(20) NOT NULL,
  `pointScore` INT NULL,
  PRIMARY KEY (`pointWhy`))
COMMENT = '유저에게 적립할 포인트에 대한 정책 정의 테이블\n어떤 사유로 몇 포인트를 지급하는지에 대해 정의';

CREATE TABLE `my`.`pointlog` (
  `pointLogNo` INT NOT NULL AUTO_INCREMENT,
  `pointWho` VARCHAR(8) NOT NULL,
  `pointWhen` DATETIME NULL DEFAULT now(),
  `pointWhy` VARCHAR(20) NOT NULL,
  `pointScore` INT NOT NULL,
  PRIMARY KEY (`pointLogNo`),
  INDEX `pointlog_member_fk_idx` (`pointWho` ASC) VISIBLE,
  CONSTRAINT `pointlog_member_fk`
    FOREIGN KEY (`pointWho`)
    REFERENCES `my`.`member` (`userId`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
COMMENT = '어떤 멤버에게 어떤 사유로 몇 포인트가 언제 지급되었는지 기록 테이블';

-- boardupfiles 테이블 생성
CREATE TABLE `boardupfiles` (
  `boardUpfileNo` int(11) NOT NULL AUTO_INCREMENT,
  `newFileName` varchar(100) NOT NULL,
  `originFileName` varchar(100) NOT NULL,
  `thumbFileName` varchar(100) DEFAULT NULL,
  `ext` varchar(20) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `boardNo` int(11) DEFAULT NULL,
  `base64Img` text,
  PRIMARY KEY (`boardUpfileNo`),
  KEY `boardupfiles_boardNo_fk_idx` (`boardNo`),
  CONSTRAINT `boardupfiles_boardNo_fk` FOREIGN KEY (`boardNo`) REFERENCES `hboard` (`boardNo`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='게시판에 업로드되는 파일을 기록하는 테이블';


-- 회원가입
insert into member(userId, userPwd, userName, mobile, email) 
values("kildong", sha1(md5("1234")) ,"홍길동", "010-1111-1111", "kildong123@abc.com" );
insert into member(userId, userPwd, userName, mobile, email) 
values("kilsun", sha1(md5("1234")) ,"홍길순", "010-2222-2222", "kilsun123@abc.com" );

-- 게시글 등록
insert into hboard(title, content, writer)
values("첫 번째 게시글", "내용1", "kildong");

insert into hboard(title, content, writer)
values("두 번째 게시글", "내용2", "kilsun");

use my;
select * from member;
select * from hboard;

commit;

-- 포인트 획득 로그
INSERT INTO `my`.`pointdef` (`pointWhy`, `pointScore`) VALUES ('회원가입', '100');
INSERT INTO `my`.`pointdef` (`pointWhy`, `pointScore`) VALUES ('로그인', '1');
INSERT INTO `my`.`pointdef` (`pointWhy`, `pointScore`) VALUES ('글작성', '10');
INSERT INTO `my`.`pointdef` (`pointWhy`, `pointScore`) VALUES ('댓글작성', '2');


-- 게시글에 첨부한 파일정보를 저장
-- 번호 가져오기
select max(boardNo) from hboard;

-- 업로드된 첨부파일을 저장하는 쿼리문
-- insert into boardupfiles(newFileName, originFileName, thumbFileName, ext, size, boardNo, base64Img) 
-- values(#{newFileName}, #{originFileName}, #{thumbFileName}, #{ext}, #{size}, #{boardNo}, #{base64Img});

-- 게시글 번호로 조회
select * from hboard where boardNo = 33;

-- 업로드 파일 조회
select * from boardupfiles where boardNo = 39;

select boardNo, title, content, writer from hboard where boardNo = 39;

-- 게시글 상세페이지
-- boardNo 번째 글의 hboard 모든 컬럼과, 해당 글의 모든 업로드 파일과, 작성자의 이름과 이메일을 가져오기 위한 쿼리문 작성

select h.*, f.*, m.userId, m.email
from hboard h left outer join boardupfiles f on h.boardNo = f.boardNo
join member m on h.writer = m.userId
where h.boardNo = 39;

SHOW CREATE TABLE boardreadlog;

-- boardreadlong 테이블 생성
CREATE TABLE `boardreadlog` (
  `boardReadLogNo` int NOT NULL AUTO_INCREMENT,
  `readWho` varchar(130) NOT NULL,
  `readWhen` datetime DEFAULT CURRENT_TIMESTAMP,
  `boardNo` int DEFAULT NULL,
  PRIMARY KEY (`boardReadLogNo`),
  CONSTRAINT `boardreadlog_boardno_fk` 
  FOREIGN KEY (`boardReadLogNo`) REFERENCES `hboard` (`boardNo`)) 
  ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci 
  COMMENT='조회수 처리를 위한 클라이언트 ip와 게시글을 읽은 시간, 게시글 번호를 저장하기 위한 테이블';

select * from boardreadlog;

insert into boardreadlog(readWho, boardNo) values('127:0:0:1', 39);

select datediff(now(), readWhen) 
from boardreadlog 
where readWho = '127:0:0:1' and boardNo = 39;

-- 1) ipAddr 유저가 n번 글을 언제 조회했는지
select readWhen
from boardreadlog 
where readWho = '127:0:0:1' and boardNo = 39;

-- 2) 1번의 결과가 null이면 insert
insert into boardreadlog(readwho, boardNo) values(?, ?);

-- 3) 1번의 결과가 null이 아니면 현재 시간과 이전에 읽은 날짜시간의 차이를 구해야함
-- null인 경우에는 -1 출력
select ifnull(datediff(now(), (select readWhen from boardreadlog where readWho = '127:0:0:1' and boardNo = 39)) , -1) as diff;


--  조회수 증가(update)
-- update hboard set readCount = readCount + 1 where boardNo = #{boardNo};

-- 조회 시간 업데이트(조회 시간에서 24시간 경과 후에만)
-- update boardreadlog set readWhen = now() 
-- where readWho = #{readWho} and boardNo = #{boardNo};

delete from boardreadlog;
delete from hboard;
delete from boardupfiles;

select * from hboard;
select * from boardreadlog;
select * from boardupfiles;

select ifnull(datediff(now(), (select readWhen from boardreadlog where readWho = '127:0:0:1' and boardNo = 46)) , -1) as datediff;

insert into boardreadlog(readWho, boardNo) values('127:0:0:1', 46);

drop table hboard;
drop table boardupfiles;
drop table boardreadlog;

SHOW COLUMNS FROM boardreadlog;
SHOW COLUMNS FROM hboard;

-- 계층형 게시판 답글 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
-- 1) 기존 게시글의 ref 컬럼 값을 boardNo 값으로 update
-- 2) 앞으로 저장할 게시글에 ref 컬럼 값을 boardNo 값으로 update
update hboard set ref = ?
where boardNo = ?

-- 2-2) 부모글에 대한 다른 답을이 있는 상태에서, 부모글의 답글이 추가되는 경우,
-- (자리 확보를 위해) 기존의 답글의 refOrder값을 수정(+1) 해야함.
update hboard set refOrder = refOrder + 1
where ref = ? and refOrder > ?

-- 2-1) 답글을 입력받아 저장
insert into hboard(title, content, writer, ref, step, refOrder) 
values(?, ?, ?, ?, ?);

-- 3) 리스트 재정렬
select * from hboard order by ref desc, refOrder asc;

-- 
alter table hboard add column isDelete char(1) null default 'N' after `refOrder`;
alter table hboard drop column isDelete;

update hboard set isDelete = 'Y'
where boardNo = ?;

-- 페이징 처리
select * from hboard order by ref desc, refOrder asc;
select * from hboard order by ref desc, refOrder asc limit 10;
select * from hboard order by ref desc, refOrder asc limit 5, 10;

-- 글 총 개수
select count(*) from hboard;

-- 전체 페이지 수
-- (전체 페이지수 / 페이지당 보여질 글의 개수)
select count(*) / 10 from hboard;

-- ?번 페이지에서 보여줄 글의 시작 index
-- 1페이지 0 
-- 2페이지 10
-- 페이지번호 - 1 * (페이지당 보여질 글의 개수)

-- 페이징 블럭
-- 1) 1개의 페이징 블럭에서 보여줄 페이지 수: 10
-- 1-2) 현재 페이지가 속한 페이징 블럭의 번호
-- ex) 7페이지는 1번블럭, 14페이지 - 2번블럭, 30페이지 - 3번블럭
-- 1번블럭: 1 ~ 10 -> 시작페이지 1, 마지막페이지 10
-- 2번블럭: 11 ~ 20 -> 시작페이지 11, 마지막페이지 20
-- 3번블럭: 21 ~ 30 -> 시작페이지 21, 마지막페이지 30
-- ...
-- 10번블럭 : 91 ~ 100 -> 시작페이지 91
-- 페이지 / 10 -> 나머지가 0이면 -> 페이지 / 10 몫
-- 페이지 / 10 -> 나머지가 0이 아니면 -> 페이지 / 10의 몫 + 1 

-- ?번 블럭에서 시작할 페이지 번호
-- 블럭 * (페이징 블럭당 보여질 페이질 수)  - (페이징 블럭당 보여질 페이질 수 - 1)
-- 페이징 블럭당 보여질 페이질 수(블럭 - 1) + 1

-- 3) 현재 ?번 페이징 블럭에서 출력할 마지막 페이지 번호
-- 2)값 + (페이징 블럭당 보여질 페이질 수 - 1)

select * from hboard;
-- 게시판 검색
-- title로 검색
select * from hboard where title  like '%번째%';

-- writer로 검색
select * from hboard where writer like '%do%';

-- content로 검색
select * from hboard where content like '%33%';

select * from member;

-- 아이디 중복 체크
select count(*) from member where userId = 'kildong';

-- 회원가입 쿼리문
insert into member(userId, userPwd, userName, gender, mobile, email, hobby, userImg)
values(?, sha1(md5(?)), ?, ?, ?, ?, ?, ?);

insert into member(userId, userPwd, userName, gender, mobile, email, hobby)
values(?, sha1(md5(?)), ?, ?, ?, ?, ?);


-- 로그인
select * from member;
select * from member where userId = 'abcd' and userPwd = sha1(md5('1234'));

-- 자동 로그인
ALTER TABLE `my`.`member` 
ADD COLUMN `sesId` VARCHAR(50) NULL AFTER `userPoint`,
ADD COLUMN `allimit` DATETIME NULL AFTER `sesId`;

-- 자동로그인 정보를 저장하는 쿼리문
-- update member set sesId = #{sesId}, allimit = #{allimit} where userId = #{userId};

-- 자동로그인 정보가 쿠키에 저장된 유저를 자동으로 로그인 시키기 위한 유저 정보 조회
-- select * from member where sesId = #{sesId} and allimit > now();

select * from member;
select * from hboard where boardType = 'cboard';

-- 댓글 게시판hboard
update hboard set boardType = 'hboard';

select * from hboard where boardType = 'cboard';

select h.*, f.*, m.userName, m.email
		from hboard h left outer join boardupfiles f on h.boardNo = f.boardNo
		join member m on h.writer = m.userId
		where h.boardNo = 345 and boardType = 'cboard';

CREATE TABLE `comment` (
  `commentNo` int NOT NULL,
  `commenter` varchar(8) DEFAULT NULL,
  `content` varchar(500) NOT NULL,
  `regDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `boardNo` int DEFAULT NULL,
  PRIMARY KEY (`commentNo`),
  KEY `boardNo_board_fk_idx` (`boardNo`),
  KEY `commenter_member_fk_idx` (`commenter`),
  CONSTRAINT `boardNo_board_fk` FOREIGN KEY (`boardNo`) REFERENCES `hboard` (`boardNo`) ON DELETE CASCADE,
  CONSTRAINT `commenter_member_fk` FOREIGN KEY (`commenter`) REFERENCES `member` (`userId`) ON DELETE CASCADE
) COMMENT = "댓글 테이블",
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

