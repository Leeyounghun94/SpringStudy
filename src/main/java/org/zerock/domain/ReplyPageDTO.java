package org.zerock.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ReplyPageDTO {
	// 객체 생성 시 편리하도록 @AllargsConstructor를 이용하여 replyCnt와 list를 파라미터 처리한다.
	
	private int replyCnt ;	// 댓글 개수
	private List<ReplyVO> list ;	//댓글 리스트
}
