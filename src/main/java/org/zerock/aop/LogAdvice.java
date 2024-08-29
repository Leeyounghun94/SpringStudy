package org.zerock.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Aspect// 해당 객체가 Aspect를 구현한 것을 나타내기 위해서 사용한다.
@Log4j2
@Component// aop와는 관계가 없으나 스프링에서 빈으로 인식하기 위해 사용한다.
public class LogAdvice {

	// 여태까지는 Log4j2 를 이용하여 log.info를 작성하였지만 Aop의 advice는 관심사를 실제로 구현한 코드 이므로
	// 지금부터는 로그를 기록해주는 LogAdvice를 설계해야 한다.
	
	
	@Before("execution(* org.zerock.service.SampleService*.*(..))")
	// execution(* org.zerock.service.SampleService*.*(..)) 이 부분은 AspectJ의 표현식이며 execution 의 경우 
	// 접근 제한자와 특정 클래스의 메서드를 지정할수 있다. * -> 클래스의 이름과 메서드의 이름을 의미한다.
	// 
	public void logBefore() {
		
		log.info("LogAdvice.logBefore() 메서드 실행 !");
		
	}
		
	@Before("execution(* org.zerock.service.SampleService*.doAdd(String, String)) && args(str1, str2)")
	//&& args(str1, str2) 이 부분은 변수명을 지정하였으며 , 2 종류 정보 이용하여 logBeforeWithParam() 메서드의 파라미터 설정하게 된다.
	//&& args를 이용하는 설정은 간단히 파라미터를 찾아서 기록할때는 유용하나, 파라미터가 다른 여러 종류의 메서드를 적용하는 것에 대해서는 간단하지 않다.
	public void logBeforeWithParam(String str1, String str2) {
		
		log.info("LogAdvice.logBeforeWithParam() 메서드 실행 !");
		log.info("str1 : " + str1);
		log.info("str2 : " + str2);
		
	}
	
	
	@AfterThrowing(pointcut = "execution(* org.zerock.service.SampleService*.*(..))", throwing="exception")
	// @AfterThrowing -> 지정된 대상이 예외를 발생한 후에 동작하면서 문제를 찾을수 있게 도와준다.
	public void logException(Exception exception) {
		
		log.info("LogAdvice.logException() 메서드 실행 !");
		log.info("Exception 발생 ! !");
		log.info("exception : " + exception);
	}
	
	@Around("execution(* org.zerock.service.SampleService*.*(..))")
	//Around = 조금 특별하게 동작하는데 직접 대상 메서드를 실행할 수 있는 권한을 가지고 있고, 메서드의 실행 전과 실행 후 처리가 가능하다.
	public Object logTime( ProceedingJoinPoint pjp) {//ProceedingJoinPoint = @Around와 같이 결합해서 파라미터나 예외 등 처리할 수가 있다.
		
		long start = System.currentTimeMillis();
		
		log.info("Target : " + pjp.getTarget());
		log.info("Param : " + Arrays.toString(pjp.getArgs()));
		
		
		//invoke Method
		Object result = null;
			
		try {
			
		result = pjp.proceed();
		
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		
		log.info("TIME : " + (end - start));
		
		
		return result;
	}
	
	
	
	
	
	
	
	
}
