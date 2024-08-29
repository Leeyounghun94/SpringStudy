package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller	//스프링이 컨트롤러 역할을 제공
@Log4j2
@RequestMapping("/board/*")		// http://localhost:80/board/???
@AllArgsConstructor	//모든 필드값으로 생성자 만들어준다.
public class BoardController {


	//필드
	private BoardService service ;	//BoardController boardController = new BoardController(BoardService);

/*	@GetMapping("/list")		// http://localhost:80/board/???
	public void list(Model model) {//	import org.springframework.ui.Model;	-> 스프링이 관리하는 메모리

		log.info("BoardController.list 메서드 실행");
		model.addAttribute("list", service.getlist());	//name : list, Object : List<BoardVO>
	}	*/
	
	@GetMapping("/list")		// http://localhost:80/board/???
	public void list(Criteria cri, Model model) {
		
		log.info("BoardController.list 메서드 실행");
		model.addAttribute("list", service.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, 123));
		// pageMake 라는 이름의 PageDTO 객체를 만들어서 Model에 담아준다. dtd 구성하기 위한 데이터 수가 필요한데 아직은 없기 때문에 123으로 함
		
		int total = service.getTotal(cri);
		
		log.info("total : " + total);
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	
	
	
	@GetMapping("/register")	// http://localhost:80/board/register
	public void register() {
		
		log.info("BoardController.register 메서드 실행");
		
		//리턴이 void 인데 url과 같은 jsp를 찾는다. // http://localhost:80/board/register.jsp
	}// 매개값 없으면 getmapping
	

	@PostMapping("/register")	//	http://localhost:80/board/register
	public String register(BoardVO board, RedirectAttributes rttr) {
		//RedirectAttributes rttr -> 1회성을 갖는 값을 제공(addFlashAttribute("이름". "값");

		log.info("BoardController.register 메서드 실행");
		service.register(board);	//프론트에서 form 값이 객체로 넘어온다.

		rttr.addFlashAttribute("result", board.getBno());	// 객체에 있는 bno 값을 1회성으로 가지고 있다(Model영역에서)

		return "redirect:/board/list";	// jsp = response.sendRedirect() 	
		// 등록 후에는 list페이지로 보낸다. http://localhost:80/board/list
	}// 보내는 매개값이 있으면 postmapping

	@GetMapping({"/get", "/modify"})
	// 이중화 작업 : http://localhost:80/board/get -> board/get.jsp
	// 이중화 작업 : http://localhost:80/board/modify -> board/modify.jsp
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {

		log.info("BoardController.get 메서드 실행");
		model.addAttribute("board", service.get(bno));
		//서비스 계층에 get메서드에 bno값을 넣어주면 객체가 나온다.(sql 통해서)
	}


	@PostMapping("/modify")	// http://localhost:80/board/modify
	public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {

		log.info("BoardController.modify 메서드 실행");

		if (service.modify(board)) { // service.modify의 리턴 타입이 boolean
			rttr.addFlashAttribute("result", "success");	// 수정 성공 시 success 메세지를 보낸다.
		} else {
			rttr.addAttribute("pageNum", cri.getPageNum());
			rttr.addAttribute("amount", cri.getAmount());
			rttr.addAttribute("type", cri.getType());
			rttr.addAttribute("keyword", cri.getKeyword());
			
			rttr.addFlashAttribute("result", "fail"); // 수정 실패 시 fail 메시지를 보낸다.
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());

		return "redirect:/board/list" + cri.getListLink();	//결론 -> http://localhost:80/board/list
	}


	@PostMapping("/remove")	// 번호를 받아 delete 쿼리를 실행한다.
	public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("BoardController.remove 메서드 실행");

		if (service.remove(bno)) { // service.remove의 리턴 타입이 boolean
			rttr.addFlashAttribute("result", "success");	// 수정 성공 시 success 메세지를 보낸다.
		} else {
			rttr.addFlashAttribute("result", "fail"); // 수정 실패 시 fail 메시지를 보낸다.
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());

		return "redirect:/board/list" + cri.getListLink();	//결론 -> http://localhost:80/board/list

	}




}