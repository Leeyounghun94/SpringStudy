package net.lyhcloud.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

//필수 3가지 -> pom.xml 에 spring-test 코드(36행 근처) 를 넣어야 3가지가 활성화 
@RunWith(SpringJUnit4ClassRunner.class) //메서드 별로 테스트
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")	// 참조할 파일
@Log4j2 // 로그 출력용
public class SampleTests {
	
	@Setter(onMethod_ = @Autowired)	//setRestaurant(Restaurant)
	private Restaurant restaurant ;	// Restaurant restaurant = new Restaurant (restaurant); 로 만들었는데 지금은 자기가 알아서 만들어 준다.
	
	
	@Test //import org.junit.Test; -> 메서드별로 테스트가 진행 됨(메서드명 블럭 -> 우클릭 -> RunAS -> JUnit)
	
	public void textExist() {
		
		assertNotNull(restaurant); //assertNotNull : 객체가 null인지 여부 확인
		
		log.info(restaurant);
		log.info("-----------------------------------");
		log.info(restaurant.getChef());	// restaurant 객체에 있는 Chef 필드를 가져오거랏
		
		 //INFO  net.lyhcloud.sample.SampleTests(textExist30) - Restaurant(chef=Chef(name=null, age=0, regdate=null), restaurantName=null, openTime=null, closeTime=null)
		 //INFO  net.lyhcloud.sample.SampleTests(textExist31) - -----------------------------------
		 //INFO  net.lyhcloud.sample.SampleTests(textExist32) - Chef(name=null, age=0, regdate=null)
		
	}

}
