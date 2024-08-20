package org.zerock.mapper;	//	DB와 영속성을 가지는 패키지

import java.util.List;

//import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;

public interface BoardMapper {
	// interface를 선언한 이유는 추상메서드와 xml을 결합하여 구현 클래스를 사용하는 MyBatis
	// 주의 : xml 생성할 때는 resources안에 폴더를 계층별로 만들고 파일명을 인터페이스와 같게 xml을 생성해야 한다.
	
	// 인터페이스에 자체적인 추상메서드를 활용하려면
	//@Select("select * from tbl_board where bno > 0")	// where bno > 0 -> bno가 pk이기 때문에 인덱싱이 되어 있어서 빠르므로 조건을 건다.
	
	public List<BoardVO> getList();	// 인터페이스 안에는 추상메서드.
	// return = List<BoardVO> 이므로 배열 안쪽에 객체가 BoardVO로 완성이 됨.
	
	
	// Board 삽입용 코드
	public void insert(BoardVO board);
	
	
	// 삽입할 번호를 먼저 파악 후 게시물 등록한다.
	public void insertSelectKey(BoardVO board);
	
	// 게시물의 번호를 받아 객체를 출력한다.
	public BoardVO read(Long bno);
	
	// 게시물의 번호를 받아 객체를 수정한다.
	public int update(BoardVO boardVO);
	
	// 게시물의 번호를 받아 객체를 삭제한다.
	public int delete(Long bno);
	
}	
