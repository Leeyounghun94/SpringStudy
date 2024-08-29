package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardMapper {

		// interface를 선언한 이유 : 추상메서드와 xml을 결합하여 구현 클래스를 사용하는 MyBatis
		// 주의할것이 있는데 xml생성할 때는 resources안에 폴더를 계층별로 만들어야하며, 파일명을 인터페이스와 같게 xml을 생성해야 한다.
	
		// 인터페이스에 자체적인 추상메서드를 활용하려면 
		
//	@Select("select * from tbl_board where bno > 0")	// where bno > 0 -> bno가 pk이기 때문에 인덱싱이 되어 있어서 빠르기 때문에 조건을 건다.	
	public List<BoardVO> getList();	//인터페이스 안에는 추상메서드.
	 
	
	
	// board 삽입용 코드
	public void insert(BoardVO board);
	
	// 삽입할 번호를 먼저 파악한 후 게시물 등록한다.
	public void insertSelectKey(BoardVO board);
	
	// 게시물의 번호를 받아 객체를 출력한다.
	public BoardVO read(Long bno);
	
	//게시물의 번호를 받아서 객체를 수정한다.
	public int update(BoardVO board);
	
	//게시물의 번호를 받아서 객체를 삭제한다.
	public int delete(Long bno);
	
	// 마이바티스의 페이징 처리
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	// 마이바티스에서 전체 데이터 개수 처리
	public int getTotalCount(Criteria cri);
}
