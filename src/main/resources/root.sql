create user book_ex identified by book_ex default tablespace users temporary tablespace temp;

grant connect, dba to book_ex;

select dbms_xdb.gethttpport() from dual; --8080 포트 사용중임을 확인!

exec dbms_xdb.sethttpport(9090); -- 포트 변경하는 쿼리인데 이클립스에서는 먹히지 않는 명령어 -> sqlplus에서 해야 함.