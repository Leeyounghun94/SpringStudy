package org.zerock.domain;

import lombok.Data;

@Data
public class Ticket {
	//url을 통해서 json타입으로 받아서 객체 처리 테스트
	
	
	private int tno ;	//티켓번호
	private String owner ;	// 티켓 오너
	private String grade ;	// 티켓 등급
}
