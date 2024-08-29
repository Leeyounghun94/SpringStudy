package org.zerock.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@RunWith(SpringJUnit4ClassRunner.class)	// 메서드별 테스트용(junit4) 
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")	// 참고할 파일
@Log4j2
public class BoardMapperTests {	//테스트용 코드
	
	@Setter(onMethod_ = @Autowired)	//생성자 자동 주입
	private BoardMapper mapper;
	
	@Test
	public void testGetList() {
		
		mapper.getList().forEach(board -> log.info(board));
	}
	
	
	@Test
	public void testInsert() {
		
		BoardVO boardVO = new BoardVO(); 	//새 객체 생성
		boardVO.setTitle("매퍼로 만든 제목");
		boardVO.setContent("매퍼로 만든 내용");
		boardVO.setWriter("매퍼 사용자");	// 객체에 내용 삽입 완료
		
		mapper.insert(boardVO);
		
		log.info("입력된 객체 : " + boardVO);
		
	}
	
	@Test
	public void testInsertSelectKey() {
		
		BoardVO boardVO = new BoardVO(); 	//새 객체 생성
		boardVO.setTitle("번호 생성 먼저 제목");
		boardVO.setContent("번호 생성 먼저 내용");
		boardVO.setWriter("번호 생성한 나");
		
		mapper.insertSelectKey(boardVO); //번호 먼저 생성 후 insert
		
		log.info(boardVO);		
	}
	
	@Test
	public void testRead() {
		BoardVO boardVO = mapper.read(5L);
		
		log.info(boardVO);
		
	}
	
	@Test
	public void testUpdate() {
		BoardVO boardVO = new BoardVO();	// 새 객체 생성
		boardVO.setBno(5L);	//찾을 번호
		boardVO.setTitle("수정한 제목");
		boardVO.setContent("수정한 내용");
		boardVO.setWriter("김수정");
		
		int count = mapper.update(boardVO);
		log.info("수정된 개수 : " + count);
		log.info("수정된 객체 : " + boardVO);
	}
	
	
	@Test
	public void testdelete() {
		
		log.info("삭제한 개수 : " + mapper.delete(3L));
	}
	
	
	@Test
	public void testPaging() {
		
		Criteria cri = new Criteria();	//새 객체 생성
		
		cri.setPageNum(3);
		cri.setAmount(10);
		// 한 페이지 당 10개씩 출력하는 3페이지에 해당하는 데이터
		
		
		List<BoardVO> list = mapper.getListWithPaging(cri);
		
		list.forEach(board -> log.info(board));
	}
	
	
	@Test
	public void testSearch() {// Criteria 객체의 type과 keyword를 넣어서 원하는 SQL이 생성되는지 확인 하기 위함.
		//중요한 것은 실행 결과가 아닌 실행 할때 만들어지는 SQL이다.
		
		Criteria cri = new Criteria();
		cri.setKeyword("잎새주");
		cri.setType("TC");
		
		List<BoardVO> list = mapper.getListWithPaging(cri);
		
		list.forEach(board -> log.info(board));
	}
}
