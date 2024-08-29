package org.zerock.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyPageDTO;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController// rest 방식으로 응답하며, view-jsp 가 아닌 json과 xml 로 나타난다.
@RequestMapping("/replies")//	 http://localhost:80/replies/????
@Log4j2
@AllArgsConstructor// 생성자 구현 -> new ReplyController(ReplyService);

public class ReplyController {//	Rest방식의 컨트롤러로 구현 + Ajax 처리 한다.

	
	private ReplyService service ;
	
	
	// URL = http://localhost:80/replies/new
	@PostMapping(value = "/new",
			consumes = "application/json",
			produces = MediaType.TEXT_PLAIN_VALUE)// 입력값이 json 으로
	public ResponseEntity<String> create(@RequestBody ReplyVO vo) {
		//리턴은 200이나 500 으로 처리 된다.
	
		log.info("ReplyVO 객체 json 입력 값");// 파라미터로 넘어온 값 출력 테스트
		
		int insertCount = service.register(vo);	// SQL 처리 후 결과값이 1 또는 0 이 나온다.
		
		log.info("서비스 + 매퍼 처리 결과는 ?  : " + insertCount);
		
		return insertCount == 1 ? new ResponseEntity<>("Success", HttpStatus.OK)	// 200이면 정상
								: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500이면 서버 오류
		//삼항 연산자, IF리턴 할때 정상 처리, 오류 처리 인지 전달해야 함.
	}
	
	
	//http://localhost:80/replies/pages/11/1 -> xml
	//http://localhost:80/replies/pages/11/1.json -> json
	@GetMapping(value = "/pages/{bno}/{page}",
						produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ReplyPageDTO> getList(@PathVariable("page") int page, @PathVariable("bno") Long bno) {
		
		log.info("ReplyContoller.getList() 메서드 실행, 찾을 번호 " + bno);
		log.info("페이지 번호" + page);
		log.info("찾을 번호" + bno);
		
		Criteria cri = new Criteria(page, 10);// 현재 페이지 와 리스트 개수를 전달
		log.info("cri : " + cri);
		log.info("get Reply List bno : " + bno);
		
		return new ResponseEntity<>(service.getListPage(cri, bno), HttpStatus.OK);// 200 으로 정상 처리함.
	}
	
	//	http://localhost:80/replies/4
	@GetMapping(value= "/{rno}",
				produces = { MediaType.APPLICATION_XML_VALUE,
								MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno) {
		
		log.info("ReplyController.get() 메서드 실행, 찾을 rno : " + rno);
		
		return new ResponseEntity<>(service.get(rno), HttpStatus.OK);
	}
	
	
	@RequestMapping(method = { RequestMethod.PUT, RequestMethod.PATCH}, value = "/{rno}", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> modify(@RequestBody ReplyVO vo, @PathVariable("rno") Long rno) {
		
		vo.setRno(rno);// 가지고 있는 객체에다가 rno 삽입
		
		log.info("ReplyController.modify() 메서드 실행, 수정하려는 rno : " + rno);
		
		return service.modify(vo) == 1 
				? new ResponseEntity<>("Success", HttpStatus.OK)	// 200이면 정상
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500이면 서버 오류
	}
	
	
	@DeleteMapping(value= "/{rno}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {
		
		log.info("ReplyController.remove() 메서드 실행, 삭제하려는 rno : " + rno);
		
		return service.remove(rno) == 1
				? new ResponseEntity<>("Success", HttpStatus.OK)	// 200이면 정상
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500이면 서버 오류
	}
	
	
}
