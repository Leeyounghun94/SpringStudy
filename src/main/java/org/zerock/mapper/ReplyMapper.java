package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyMapper {

	// xml 와 연동해서 SQL 처리한다.
	
	//  Interface는 추상 메서드가 필요함.
	
	public int insert(ReplyVO vo);	// 외부에서 폼으로 ReplyVO 객체가 넘어오고 리턴은 int가 된다.

	public ReplyVO read(Long rno);	// 댓글의 번호를 가지고 댓글(객체)를 가져온다.

	public int update(ReplyVO reply);	// 객체가 넘어가서 수정된다 결과는 int 처리

	public int delete (Long rno);	// 댓글의 번호를 가지고 레코드를 삭제 후에 int가 리턴이 된다.	
	//여기까지가 CRUD 처리
	
	//댓글 리스트 : 페이징 처리 + Bno(pk + fk) -> 여러 개의 파라미터인 경우 @Param()을 사용하면 편리하다.
	public List<ReplyVO> getListWithPaging(@Param("cri") Criteria cri, @Param("bno") Long bno);
	//										ㄴ페이징처리 기준점				ㄴ 게시물 번호를 pk+fk
	
	
	//댓글의 숫자 파악
	public int getCountByBno(Long bno);
}
