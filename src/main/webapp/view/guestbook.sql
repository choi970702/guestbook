create table guestbook
(
idx number not null primary key,
name varchar2(50),
subject varchar2(50),
content varchar2(4000),
email varchar2(50),
pwd varchar2(50),
regdate date
);

create SEQUENCE guestbook_seq
INCREMENT by 1
start with 1
MAXVALUE 999999999
NOCYCLE;

insert into guestbook values 
(guestbook_seq.nextval,'홍길동', '제목','content','a@naver.com','1111',sysdate);
select * from guestbook;