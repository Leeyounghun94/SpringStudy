package org.zerock.service;

import java.util.List;

import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyPageDTO;
import org.zerock.domain.ReplyVO;

public interface ReplyService {// Service 담당, 인터페이스는 구현클래스가 필수로 있어야 한다. (Implment)
	
	public int register(ReplyVO vo);	// 댓글 등록하기
	
	public ReplyVO get(Long rno); // 댓글 한 개 가져오기
	
	public int modify(ReplyVO vo);	// 객체 수정하고 나서 int 로 리턴
	
	public int remove(Long rno); // 댓글 한 개 삭제하고 나서 int로 리턴.
	
	public List<ReplyVO> getList(Criteria cri, Long bno); // 게시글의 번호를 이용하여 모든 댓글을 리스트로 출력!
	
	public ReplyPageDTO getListPage(Criteria cri, Long bno);// 댓글과 댓글 수 처리

}
