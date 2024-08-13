package net.lyhcloud.sample;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;


@Component	//스프링이 객체를 관리 -> 필수 : root-context.xml에 가서 context : componet-scan 패키지 추가(그러면 파란색 S가 붙여지면서 스프링이 관리한다)
@Data		//lombok이 dto처럼 관리
public class Restaurant {
	
	//필드
	@Setter(onMethod_ = @Autowired) // 자동으로 setChef()를 컴파일 시 생성한다.
	private Chef chef ;				// setChef(Chef)
	private String restaurantName ;
	private Date openTime ;
	private Date closeTime ;
	
	
	
	
	//생성자
	
	
	
	//메서드

}
