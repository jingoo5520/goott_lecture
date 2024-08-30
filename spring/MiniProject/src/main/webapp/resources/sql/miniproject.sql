-- member 테이블 생성
CREATE TABLE `my`.`member` (
  `userId` VARCHAR(20) NOT NULL,
  `userPwd` VARCHAR(200) NOT NULL,
  `userName` VARCHAR(12) NULL,
  `mobile` VARCHAR(13) NULL,
  `email` VARCHAR(50) NULL,
  `registerDate` DATETIME NULL DEFAULT now(),
  `userImg` VARCHAR(50) NOT NULL DEFAULT 'avatar.png',
  `userPoint` INT NULL DEFAULT 100,
  PRIMARY KEY (`userId`),
  UNIQUE INDEX `mobile_UNIQUE` (`mobile` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);

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