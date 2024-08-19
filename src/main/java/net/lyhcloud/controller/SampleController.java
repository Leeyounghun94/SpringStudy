package net.lyhcloud.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;
import net.lyhcloud.domain.SampleDTO;
import net.lyhcloud.domain.SampleDTOList;
import net.lyhcloud.domain.TodoDTO;

@Controller // servlet-context.xml에 있는 <context:component-scan
			// base-package="net.lyhcloud.controller" /> 이 코드가 관리 한다.
@RequestMapping("/sample/*") // url 생성됨 http://localhost:80/sample/* , *은 하위 폴더/ **은 하위의 하위(하위의 모든 것)
@Log4j2 // 이게 있어야 log를 찍을 수 있다.
public class SampleController {

	// 필드

	// 생성자

	// 메서드

	/*
	 * @DateTimeFormat을 DTO 필드에 명시하면 아래코드는 사용 안한다.
	 * 
	 * @InitBinder //문자열을 날짜 형식으로 변경용 public void InitBinder(WebDataBinder binder) {
	 * 
	 * SimpleDateFormat simpeDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	 * binder.registerCustomEditor(java.util.Date.class, new
	 * CustomDateEditor(simpeDateFormat, false));
	 * 
	 * }
	 */

	@RequestMapping("") // http://localhost:80/sample/"" -> "" = 빈값
	public void basic() {
		// 리턴타입이 void인 경우 : 경로와 같은 URL.jsp를 찾는다.
		// 파일 [/WEB-INF/views/sample.jsp]을(를) 찾을 수 없습니다.
		log.info("SampleController.basic() 메서드 실행");
	}

	@RequestMapping(value = "/basic", method = { RequestMethod.GET, RequestMethod.POST }) // http://localhost:80/sample/basic
	// {RequestMethod.GET = get메서드 호출, RequestMethod.POST = post 메서드로 호출}
	public void basicGet() {
		log.info("SampleController.basicGet() GET+ POST 방식의 메서드 호출용");
	}

	// 여기까지 구형 방식

	@GetMapping("/basicOnlyGet") // http://localhost:80/sample/basicOnlyGet
	public void basicGetOnly() {
		// 파일 [/WEB-INF/views/sample/basicOnlyGet.jsp]을(를) 찾을 수 없습니다.
		log.info("SampleController.basicGetOnly() GET방식의 메서드 호출용");
	}

	@PostMapping("/basicOnlyPost") // http://localhost:80/sample/basicOnlyPost
	public void basicPostOnly() {
		// 파일 [/WEB-INF/views/sample/basicOnlyPost.jsp]을(를) 찾을 수 없습니다.
		log.info("SampleController.basicPostOnly() GET방식의 메서드 호출용");
	}

	@GetMapping("/ex01") // http://localhost:80/sample/ex01
	public String ex01(SampleDTO sampleDTO) {

		log.info("SampleController.ex01(SampleDTO sampleDTO) 메서드 실행" + sampleDTO);
		// SampleController.ex01(SampleDTO sampleDTO) 메서드 실행 SampleDTO(name=null, age=0)

		return "ex01"; // 이 결과가 /WEB-INF/views/ex01.jsp
	}

	@GetMapping("/ex02") // http://localhost:80/sample/ex02?id=kkw&age=30
	public String ex02(@RequestParam("id") String name, @RequestParam("age") int age) {
		// @RequestParam 파라미터로 사용된 변수 이름과 전달되는 파라미터 이름이 다른 경우 유용
		// 타입을 정해서 넣을 수 있어서 안전하다.
		SampleDTO sampleDTO = new SampleDTO();
		sampleDTO.setName(name);
		sampleDTO.setAge(age);
		log.info("name : " + sampleDTO.getName());
		log.info("age : " + sampleDTO.getAge());

		return "ex02";
	}

	// 리스트 처리

	@GetMapping("/ex02List") // http://localhost:80/sample/ex02List?ids=111&ids=222&ids=333
	public String ex02List(@RequestParam("ids") ArrayList<String> ids) {

		log.info("ids : " + ids);

		return "ex02List";
	}

	// 배열 처리
	@GetMapping("/ex02Array") // http://localhost:80/sample/ex02Array?ids=111&ids=222&ids=333
	public String ex02Array(@RequestParam("ids") String[] ids) {

		log.info("array ids : " + Arrays.toString(ids));
		return "ex02Array";
	}

	@GetMapping("/ex02Bean") // http://localhost:80/sample/ex02Bean?list[0].name=kkw&list[0].age=30 -> 특수 문자
								// [] 로 오류가 난다.
								// http://localhost:80/sample/ex02Bean?list%3Flist%5B0%5D.name%3Dkkw%26list%5B0%5D.age%3D30
	public String ex02Bean(SampleDTOList list) { // 리스트 객체를 매개값으로 받음

		log.info("list dtos : " + list);

		return "ex02Bean";
	}

	// 상단에서 만든 initBinder를 활용한 날짜타입 입력

	@GetMapping("/ex03") //// http://localhost:80/sample/ex03?title=study&dueDate=2024-08-14&check=true
	public String ex03(TodoDTO todoDTO) {
		log.info("todo : " + todoDTO);
		return "ex03";
	}

	@GetMapping("/ex04") // http://localhost:80/sample/ex04?name=kkw&age=30&page=3
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
		// @ModelAttribute("page") -> url 통해서 넘어온 값을 model에 담아라 !
		log.info("dto : " + dto);
		log.info("page : " + page);

		return "/sample/ex04"; // http://localhost:80/sample/ex04.jsp
		// view : sevlet-context.xml에서 담당 -> 실제 경로는 /WEB-INF/views/sample/ex04.jsp 가 있어야
		// 함
	}

	// return 타입 테스트

	@GetMapping("/ex05") // http://localhost:80/sample/ex05 -> 경로는 /WEB-INF/views/sample/ex05.jsp 가 있어야 함
	public void ex05() {// void = /WEB-INF/views/sample/ex05.jsp를 찾는다.
		log.info("/ex05 메서드 실행");
		// 파일 [/WEB-INF/views/sample/ex05.jsp]을(를) 찾을 수 없습니다.
	}

	// 컨트롤러에서 처리한 값을 json으로 출력 테스트

	@GetMapping("/ex06") // http://localhost:80/sample/ex06
	public @ResponseBody SampleDTO ex06() { // @ResponseBody SampleDTO = 응답 바디영역에 객체를 담아 리턴한다.

		SampleDTO dto = new SampleDTO(); // 빈 객체 생성
		dto.setName("엠비씨");
		dto.setAge(20);

		return dto; // json {"name":"엠비씨","age":20} -> 백엔드 개발자는 json으로만 보내고 프론트 개발자는 화면에 div, table
					// 등 이용해서 보여준다.
	}

	// 응답 헤더에 값을 추가하여 보냄

	@GetMapping("/ex07") // http://localhost:80/sample/ex07
	public ResponseEntity<String> ex07() {

		log.info("/ex07 메서드 실행.....");

		String msg = "{\"name\":\"엠비씨\", \"age\":20}"; // json {"name":"엠비씨","age":20}
		HttpHeaders header = new HttpHeaders(); // httpHeaders 객체 생성
		header.add("Context-Type", "application/json;charset=UTF-8"); // 헤더에 타입 추가 json임을 명시

		return new ResponseEntity<>(msg, header, HttpStatus.OK); // HttpStatus.OK 200 정상 코드임을 보냄
	}

	@GetMapping("/exUpload")
	public void exUpload() {// http://localhost:80/sample/ex08

		log.info("/exUpload 메서드 실행.....");

		// 리턴이 void -> http://localhost:80/sample/ex08
	}

	@PostMapping("exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {

		files.forEach(file -> {
			log.info("-----------------------------");
			log.info("name : " + file.getOriginalFilename()); // 원본 파일명 출력
			log.info("size : " + file.getSize()); // 파일 크기 출력
			log.info("toString : " + file.toString()); // toString 메서드 출력

		});

	}

}
