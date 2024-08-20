package org.zerock.domain;	//domain 패키지는 객체를 담당한다!

import java.util.Date;

import lombok.Data;

@Data	//getter, setter, toString, equal, 생성자 등을 관여 한다.

public class BoardVO {
	
	// tbl_board에 있는 객체를 담당!

	private Long bno;	//번호
	private String title;	//제목
	private String content;	//내용
	private String writer;	//작성자
	private Date regate;	//작성일
	private Date updateDate;	//수정일
}
