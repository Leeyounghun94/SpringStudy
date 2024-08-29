package org.zerock.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

@Data
public class Criteria {// Criteria = 검색의 기준 의미

	// Criteria 용도 = pageNum과 amount 값을 전달하는 용도이지만, 기본값을 1페이지, 10개로 지정해서 처리
	
	
	
	private int pageNum ;	//페이지 번호
	private int amount ;	// 한 페이지당 몇개의 데이터
	
	private String type ;
	private String keyword ;

	public Criteria() {
		this(1,10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	
	public String[] getTypeArr() {// 검색 조건이 각 글자(T,W,C)로 구성 되어 있기 때문에 검색 조건을 배열로 만들어서 한번에 처리
		
		return type == null? new String[] {}: type.split("");
		
	}
	
	
	public String getListLink() {
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
		//import org.springframework.web.util.UriComponentsBuilder; -> 여러 개의 파라미터들을 연결해서 url의 형태로 만들어주는 기능을 가지고 있다.		
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.getAmount())
				.queryParam("type", this.getType())
				.queryParam("keyword", this.getKeyword());
		
		return builder.toUriString();
	}
	
}
