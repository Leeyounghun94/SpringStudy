package net.lyhcloud.sample;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
@ToString	//lombok이 객체출력을 문자열로 처리
@Getter		//getter만 생성

//@AllArgsConstructor	//필드에 있는 모든 값을 이용해서 생성자를 만든다.
//@NoArgsConstructor	//기본 생성자용 new SampleHotel();
@RequiredArgsConstructor	//위에 두개는 세트 인데 이거는 따로! / @NonNull, final이 붙은 필드만 생성자 값을 넣음(즉, 커스텀)
public class SampleHotel {

	@NonNull
	private Chef chef;
	private final String HotelName;
	private Date HotelAge;
	
	/*	public SampleHotel(Chef chef) {	//SampleHotel sampleHotel = new SampleHotel(chef); 로 만들었다.
		this.chef = chef;
		//생성자 -> 객체 생성 시 Chef를 만든다.
		} 
		 */
	
}
