package net.lyhcloud.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TodoDTO {// 컴퓨터에서 Todo는 일과표

	private String title;	//	제목(할일)
	
	@DateTimeFormat(pattern = "yyyy/MM/dd") // @InitBinder 안쓴다.
	private Date dueDate;	//	날짜(적용시간)
	private boolean check;	//	성공 여부
	
	
}	
