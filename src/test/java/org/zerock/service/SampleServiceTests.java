package org.zerock.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j2
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")

public class SampleServiceTests {

	@Setter(onMethod_ = @Autowired)
	private SampleService service ;
	
	
	@Test
	public void testClass() {
		
		log.info(service);
		log.info(service.getClass().getName());
		log.info("SampleServiceTests.testClass() 메서드 실행 ");
	/*	org.zerock.service.SampleServiceImpl@4ad3d266
		com.sun.proxy.$Proxy35
		SampleServiceTests.testClass() 메서드 실행 */
		
	}
	
	@Test
	public void testAdd() throws Exception {
		
		log.info("SampleServiceTests.testAdd() 메서드 실행");
		log.info(service.doAdd("123", "456"));
		//org.zerock.service.SampleServiceTests(testAdd38) - 579
		
		//args를 이용하여 파라미터 추적
	/*	str1 : 123
		str2 : 456
		org.zerock.service.SampleServiceTests(testAdd38) - 579	*/
		
		
	//@Around 와 ProceedingJoinPoint 에서
	/*	 INFO  org.zerock.aop.LogAdvice(logTime60) - Target : org.zerock.service.SampleServiceImpl@3730ab42
		 INFO  org.zerock.aop.LogAdvice(logTime61) - Param : [123, 456]
		 INFO  org.zerock.aop.LogAdvice(logBefore29) - LogAdvice.logBefore() 메서드 실행 !
		 INFO  org.zerock.aop.LogAdvice(logBeforeWithParam38) - LogAdvice.logBeforeWithParam() 메서드 실행 !
		 INFO  org.zerock.aop.LogAdvice(logBeforeWithParam39) - str1 : 123
		 INFO  org.zerock.aop.LogAdvice(logBeforeWithParam40) - str2 : 456
		 INFO  org.zerock.aop.LogAdvice(logTime78) - TIME : 4
		 INFO  org.zerock.service.SampleServiceTests(testAdd38) - 579	
		 -> @Around가 먼저 동작하고 @Before 등 이 실행된 후에 메서드가 실행되는데 시간이 로그로 기록 되는걸 볼 수 있다. (4초) */
		
	}
	
	
	@Test
	public void testAddError() throws Exception {
		
		log.info(service.doAdd("123", "ABC"));
		log.info("SampleServiceTests.testAddError() 메서드 실행");
		//테스트에서 고의적으로 오류를 발생시켰는데
		
	/*	str1 : 123
		str2 : ABC
		LogAdvice.logException() 메서드 실행 !
		Exception 발생 ! !
		exception : java.lang.NumberFormatException: For input string: "ABC" */
	}
	
	
	
	
	
	
}
