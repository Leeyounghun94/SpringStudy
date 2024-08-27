package org.zerock.service;

import java.util.List;

import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyService {//	구현클래스가 있어야한다 (필수) ReplyServiceImpl.java
	
	public int register(ReplyVO vo);//	댓글 등록용
	
	public ReplyVO get(Long rno);//	댓글 한 개 가져와
	
	public int modify(ReplyVO vo);//	객체 수정한 후 int로 리턴
	
	public int remove(Long rno);//	댓글 한 개 삭제 후 int로 리턴
	
	public List<ReplyVO> getList(Criteria cri, Long bno);//	게시글의 번호를 이용하여 모든 댓글을 리스트로 출력한다.

}
