package org.zerock.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController//	Rest방식으로 응답한다! -> view-jsp가 아닌 json, xml로 나온다.
@RequestMapping("/replies")//	http://localhost:80/replies/???
@Log4j2
@AllArgsConstructor//	new ReplyController(ReplyService);
public class ReplyController {//	Rest방식의 컨트롤러로 구현 + Ajax 처리 한다.

	//private boardService bservice ; -> 나중에 이렇게 응용해봐~
	private ReplyService service ;
	
	
	// url = http://localhost:80/replies/new
	@PostMapping(value = "/new", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE )// 입력값은 json 으로	
	public ResponseEntity<String> create(@RequestBody ReplyVO vo) {
		// 리턴은 200 or 500으로 처리 된다.
		log.info("ReplyVO 객체 json 입력 값 : " + vo);// 파라미터로 넘어온 값 출력 테스트
		
		int insertCount = service.register(vo);//	SQL처리 후 결과값이 1 or 0 이 나온다.
		
		log.info("서비스 + 매퍼 처리 결과 : " + insertCount);
						
		return insertCount == 1 ? new ResponseEntity<>("Success", HttpStatus.OK)	// 200이면 정상
								: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	// 500 이면 서버 오류
		//삼항 연산자, if로 리턴할 때 정상 처리인지 오류 값 인지를 전달 해야 한다.
	}
	
	
	//http://localhost:80/replies/pages/11/1 -> xml
	//http://localhost:80/replies/pages/11/1.json -> json
	@GetMapping(value = "/pages/{bno}/{page}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<ReplyVO>> getList(@PathVariable("page") int page, @PathVariable("bno") Long bno) {
		
		log.info("ReplyController.getList() 메서드 실행. 찾을 번호 " + bno);
		log.info("페이지 번호 " + page);
		log.info("찾을 번호 " + bno);
		
		Criteria cri = new Criteria(page, 10);//	현재 페이지와 리스트 개수를 전달
		log.info("Criteria " + cri);
		
		return new ResponseEntity<>(service.getList(cri, bno), HttpStatus.OK);//	200으로 정상 처리
		
		/* [{"rno":7,"bno":11,"reply":"댓글11","replyer":"kkw","replyDate":1724723533000,"updateDate":1724723533000},
		 * {"rno":13,"bno":11,"reply":"댓글11","replyer":"kkw","replyDate":1724723541000,"updateDate":1724723541000},
		 * {"rno":19,"bno":11,"reply":"댓글11","replyer":"kkw","replyDate":1724723559000,"updateDate":1724723559000},
		 * {"rno":25,"bno":11,"reply":"매퍼 댓글 테스트","replyer":"매퍼kkw","replyDate":1724724711000,	*/
	}
	
	//	http://localhost:80/replies/4
	@GetMapping(value = "{rno}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno) {
		
		log.info("ReplyController.get메서드 실행 / 찾을 rno : " + rno);
		
		return new ResponseEntity<>(service.get(rno), HttpStatus.OK);
	}
	
	
	//	http://localhost:80/replies/4
	@GetMapping(value = "{rno}", produces = { MediaType.TEXT_PLAIN_VALUE})// 얘는 json으로 나올 필요가 없음.
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {
		
		log.info("ReplyController.remove 메서드 실행 / 삭제할 rno : " + rno);
		
		return service.remove(rno) == 1
				? new ResponseEntity<>("Success", HttpStatus.OK)	// 200이면 정상
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	// 500 이면 서버 오류
	}
	
	
	// http://localhost:80/replies/4
	@RequestMapping(method = { RequestMethod.PUT, RequestMethod.PATCH },
					value = "/{rno}",
					consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> modify(@RequestBody ReplyVO vo, @PathVariable("rno") Long rno) {
		//						이미 폼(form = 프론트) 에 있는 값, 수정할 번호
		
		vo.setRno(rno);// 이미 가지고 있는 객체에 rno 값을 넣음
		
		log.info("ReplyController.modify 메서드 실행 / 수정할 rno : " + rno);
	
		log.info("수정할 객체 : " + vo);		
				
		return service.modify(vo) == 1
				? new ResponseEntity<>("Success", HttpStatus.OK)	// 200이면 정상
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	// 500 이면 서버 오류
	}
}
