package net.lyhcloud.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TimeMapper {//보드메퍼, 멤버메퍼 crud 있어야됨
	
	//	roo-context.xml 에서 적용한 mybatis를 스캔 당한다.	
	//	<mybatis-spring:scan base-package="net.lyhcloud.mapper"/> <!-- 패키지를 통해서 mybatis가 검색한다. -->

	// 인터페이스는 추상메서드가 있다. (추상메서드는 실행 블럭 없이 ; 으로 끝난다)
	
	
	// C
	@Insert("insert into board values ()")
	public String createBoard();
	
	
	// R
	@Select("select sysdate from dual")
	public String getTime();
	
	//	@select로 쿼리를 처리하는 것에 한계가 있어서 xml을 활용한다.
	//	resources 폴더에 패키지명과 같은 폴더를 다단계로 생성하고 xml 파일명을 인터페이스 명과 일치 시켜야 한다.(mybatis 규칙)
	public String getTimeXML();
	
	// U
	@Update("update set values ()")
	public String updateBoard();
	
	
	// D
	@Delete("delete from board where 조건")
	public String deleteBoard();
}
