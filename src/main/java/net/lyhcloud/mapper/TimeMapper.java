package net.lyhcloud.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {//interface = 실행메서드가 없는 추상메서드 

	//MyBatis는 쿼리문 처리를 xml + interface로 만든다.
	//interface에는 호출할 메서드명만 기술함
	//xml에는 같은 이름으로 쿼리를 만든다.
	
	
	
	// C 추상 메서드
	
	// R 추상 메서드
	public String getTimeXML();	// resources/lyhcloud/mapper/TimeMapper.xml의 쿼리를 실행 한다.
	
	// U 추상 메서드
	
	// D 추상 메서드
	
	//xml을 꼭 사용하지 않아도 된다.
	@Select("select sysdate from dual")
	public String getTime();
}
