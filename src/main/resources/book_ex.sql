-- 프로젝트에 ojdbc8.jar 연결 후에 진행한다.
-- 	1. 프로젝트 우 클릭 -> 빌드 패스 - 콘피그빌드패스 -> 라이브러리 탭에 가서  classPath 누르고 ojdbc8.jar 연결
--	2. deployment assembly 가서 java build path -> ojdbc8.jar 추가 -> apply
-- 	3. 결과 -> Referenced Libraies에 ojdbc8.jar 보이면 성공 !

create sequence seq_board; -- 자동 번호 객체 생성

create table tbl_board (
	bno number(10,0),
	title varchar2(200) not null,
	content varchar2(2000) not null,
	writer varchar2(50) not null,
	regdate date default sysdate,
	updatedate date default sysdate
); -- tbl_board 테이블 생성(번호, 제목, 내용, 작성자, 작성일, 수정일)

alter table tbl_board add constraint pk_board primary key (bno);

select * from tbl_board;

insert into tbl_board (bno, title, content, writer) values (seq_board.nextval, '테스트 제목', '테스트 내용', 'user00');
insert into tbl_board (bno, title, content, writer) values (seq_board.nextval, '테스트 제목', '테스트 내용', 'user00');
insert into tbl_board (bno, title, content, writer) values (seq_board.nextval, '테스트 제목', '테스트 내용', 'user00');
insert into tbl_board (bno, title, content, writer) values (seq_board.nextval, '테스트 제목', '테스트 내용', 'user00');
insert into tbl_board (bno, title, content, writer) values (seq_board.nextval, '테스트 제목', '테스트 내용', 'user00');
insert into tbl_board (bno, title, content, writer) values (seq_board.nextval, '테스트 제목', '테스트 내용', 'user00');

--재귀 복사 통해 데이터의 개수를 늘린다. 반복 실행
insert into tbl_board (bno, title, content, writer)
(select seq_board.nextval, title, content, writer from tbl_board);

--bno 에다가 +1 추가한 값으로 역순으로 정렬
select * from tbl_board order by bno + 1 desc; -- 책에서는 8.27초 나왔지만 나는 25 ms.
select * from tbl_board order by bno desc; -- +1 지웠더니 18ms.

-- select문 전달할때 힌트를 사용할수가 있다. 힌트는 내가 전달한 select문을 이렇게 실행해 주세요 라는 뜻
select /*+INDEX_DESC (tbl_board pk_board) */* from tbl_board;

--FULL 힌트 -> select실행할때 테이블 전체를 스캔할것을 명시함. 단, 모든 데이터를 스캔하기 때문에 상당히 느려지게 실행된다.
select /*+ FULL(tbl_board */* from tbl_board order by bno desc;

-- /*+INDEX_ASC 라는 힌트는 주로 order by를 사용한다고 생각. 테이블이름과 인덱스 이름을 같이 파라미터로 사용한다.
select /*+INDEX_ASC(tbl_board pk_board) */* from tbl_board where bno > 0;

--rownum -> SQL 실행 결과에 넘버링 해준다(페이징 처리 하기 위해) 해당 데이터가 몇번째로 나오는지 알수가 있다.
select rownum rn, bno, title from tbl_board;

-- FULL힌드를 이용해서 전체 데이터 조회하고 다시 정렬하는 방식 
-- rownum은 데이터 가져올때 적용이 되나 정렬 과정에서는 변경되지 않는다. 즉, 정렬은 나중에 처리된다는 의미
select /*+ FULL(tbl_board */rownum rn, bno, title from tbl_board where bno > 0;

-- 가장 먼저 찾은 데이터 부터 rownum = 1부터 시작
select /*+INDEX_ASC(tbl_board pk_board) */rownum rn, bno, title, content from tbl_board;

-- pk_board 인덱스가 역으로 테이블에 접근 했기 때문에 bno값이 가장 높은 데이터가를 가장 먼저 가져온다.
select /*+INDEX_ASC(tbl_board pk_board) */rownum rn, bno, title, content from tbl_board where bno > 0;

-- 가장 높은 번호의 게시물의 10개 출력인데, 인덱스를 역순으로 접근한것을 확인.
select /*+INDEX_DESC(tbl_board pk_board) */rownum rn, bno, title, content from tbl_board where rownum <= 10;

-- 인라인뷰 처리 -> select문 안쪽 from에 다시 select 이며, 어떠한 결과를 구하는 select문이 있고, 그 결과를 다시 대상으로 삼아서 select 하는 것
-- 기존 20개의 데이터에서 2페이지에 해당하는 10개만 가져옴.
select bno, title, content from (
								select /*+INDEX_DESC(tbl_board pk_board) */
								rownum rn, bno, title, content from tbl_board where rownum <= 20
								)
								where rn > 10;
								
								
--다중 항목 검색
select * from ( select /*+INDEX_DESC(tbl_board pk_board) */ 
rownum rn, bno, title, content, writer, regdate, updatedate from tbl_board where title like '%Test%' or content like '%Test%'
and rownum <= 20 ) where rn > 10 ;


-- 댓글 처리하기 위해 기존 데이터를 삭제한다.
delete from tbl_board; --  더미데이터 삭제

drop sequence seq_board; -- 자동번호 객체 삭제

create sequence seq_board; -- 다시 자동번호 생성(1번부터 생성)

select * from tbl_board order by bno desc;

insert into TBL_BOARD (bno, title, content, writer) values (seq_board.nextval, '댓글처리 제목', '댓글처리 내용', 'kkw'); --더미데이터 삽입 (총 11개)


-- 댓글 처리 위한 테이블생성과 처리

create table tbl_reply (
	rno number(10,0),  -- 댓글 번호
	bno number(10,0),  -- fk(게시판번호)
 	reply varchar2(1000) not null, -- 댓글
 	replyer varchar2(50) not null, -- 댓글 작성자
	replyDate date default sysdate,
	updateDate date default sysdate );
	
create sequence seq_reply ; -- 댓글용 자동번호객체 추가

alter table tbl_reply add constraint pk_reply primary key (rno); 
-- pk를 rno로 지정(롤이름 : pk_reply)

alter table tbl_reply add constraint fk_reply_board foreign key (bno) references tbl_board (bno); 
-- tbl_reply의 bno(자)와 tbl_board의 bno(부)를 연결 (부모가 있어야 자식이 있다) 
	
	
insert into tbl_reply (rno, bno, reply, replyer) values (seq_reply.nextval, '7', '댓글처리 내용7', 'kkw');
insert into tbl_reply (rno, bno, reply, replyer) values (seq_reply.nextval, '6', '댓글처리 내용6', 'kkw');
insert into tbl_reply (rno, bno, reply, replyer) values (seq_reply.nextval, '5', '댓글처리 내용5', 'kkw');
insert into tbl_reply (rno, bno, reply, replyer) values (seq_reply.nextval, '4', '댓글처리 내용4', 'kkw');
insert into tbl_reply (rno, bno, reply, replyer) values (seq_reply.nextval, '3', '댓글처리 내용3', 'kkw');
insert into tbl_reply (rno, bno, reply, replyer) values (seq_reply.nextval, '2', '댓글처리 내용2', 'kkw');

select * from tbl_reply;

select rno, bno, reply, replyer, replydate, updatedate from tbl_reply where bno = 10 order by rno asc;


--인덱스를 이용한 페이징 쿼리(특정 게시물의 rno 순번대로 데이터 조회)
select /*+INDEX(tbl_reply idx_reply) */
	rownum rn, bno, rno, reply, replyer, replyDate, updatedate from tbl_reply where bno = 11 and rno > 0;

-- 10개씩 2페이지를 가져오는 데이터
select rno, bno, reply, replyer, replydate, updatedate from (
	select /*+INDEX(tbl_reply idx_reply) */ rownum rn, bno, rno, reply, replyer, replyDate, updatedate from tbl_reply
	where bno = 11
	and rno > 0
	and rownum <= 20
)	where rn > 10 ; 
