package net.lyhcloud.sample;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;

@Component // 스프링이 객체를 관리한다!
@Data 	//dto 관리용 lombok
//@AllArgsConstructor	// 모든 필드값을 활용하는 생성자 만든다.
public class Chef {
	
	private String name;
	private int age;
	private Date regdate;

}
