package org.zerock.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
// Java Config
// @ContextConfiguration(classes = {org.zerock.config.RootConfig.class} )
@Log4j2

public class ReplyMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
		
	@Test
	public void testMapper() {

		log.info(mapper);
		// INFO  org.zerock.mapper.ReplyMapperTests(testMapper30) - org.apache.ibatis.binding.MapperProxy@2acbc859
	}

	
	@Test
	public void testCreate() {
		
		ReplyVO vo= new ReplyVO();// 새 객채 생성
		
		vo.setBno(11L);
		vo.setReply("매퍼 댓글 테스트");
		vo.setReplyer("매퍼kkw");
		
		mapper.insert(vo);
		//insert into tbl_reply (rno, bno, reply, replyer) values (seq_reply.nextval, 11, '매퍼 댓글 테스트', '매퍼kkw')
	}
	
	
	@Test
	public void testRead() {
		
		Long targetRno = 5L ;
		
		ReplyVO vo = mapper.read(targetRno);
		
		log.info("select + rno : " + vo);
		/* select + rno : ReplyVO(rno=5, bno=3, reply=댓글처리 내용3, replyer=kkw,
			replyDate=Tue Aug 27 20:17:26 KST 2024, updateDate=Tue Aug 27 20:17:26 KST 2024) */
	}

	
	@Test
	public void testUpdate() {
		
		Long targetRno = 10L ;
		
		ReplyVO vo = mapper.read(targetRno);
		
		vo.setReply("테스트 업데이트 했어요 ");
		
		int count = mapper.update(vo);
		
		log.info("수정한 카운트는? " + count);// 수정한 카운트는? 1
		log.info("수정한 객체는? " + vo);
		/* 수정한 객체는? ReplyVO(rno=10, bno=5, reply=테스트 업데이트 했어요 , replyer=kkw,
			replyDate=Tue Aug 27 20:17:54 KST 2024, updateDate=Tue Aug 27 20:17:54 KST 2024) */
	}
	
	@Test
	public void testDelete() {
		
		Long targetRno = 1L ;
		
		log.info("삭제했는데 결과는 ? " + mapper.delete(targetRno));
		// 삭제했는데 결과는 ? 1
	}
	
	
	@Test
	public void testList() {
		
		Criteria cri = new Criteria(); // 빈 객체 생성
		
		log.info("Criteria : " + cri);
		
		List<ReplyVO> replies = mapper.getListWithPaging(cri, 6L);
		
		replies.forEach(reply -> log.info(reply));
		
		/*  |----|----|---------|--------|----------------------|----------------------|
			|rno |bno |reply    |replyer |replydate             |updatedate            |
			|----|----|---------|--------|----------------------|----------------------|
			|2   |6   |댓글처리 내용6 |kkw     |2024-08-27 20:17:23.0 |2024-08-27 20:17:23.0 |
			|8   |6   |댓글처리 내용6 |kkw     |2024-08-27 20:17:44.0 |2024-08-27 20:17:44.0 |
			|9   |6   |댓글처리 내용6 |kkw     |2024-08-27 20:17:53.0 |2024-08-27 20:17:53.0 |
			|----|----|---------|--------|----------------------|----------------------| */
	}
	
	
	@Test
	public void testList2() {
		
		Criteria cri = new Criteria(2, 10);
		
		List<ReplyVO> replies = mapper.getListWithPaging(cri, 6L);
		
		replies.forEach(reply -> log.info(reply));
	}
	
	
	
	
	
	
}
