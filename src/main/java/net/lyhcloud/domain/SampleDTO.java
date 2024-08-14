package net.lyhcloud.domain;	//대부분 스프링에서 DTO를 domain에 묶어서 관리 한다.

import lombok.Data;

@Data //lombok이 DTD를 관리한다.(tostring, getter, setter, equals, 생성자 등을 자동으로 생성한다.)
public class SampleDTO {

	//필드
	private String name;
	private int age ;
	
	
	
	//생성자
	
	
	
	
	//메서드
}
