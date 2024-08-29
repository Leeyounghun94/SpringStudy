package org.zerock.service;

import org.springframework.stereotype.Service;


@Service// 스프링이 서비스를 관리한다.
public class SampleServiceImpl implements SampleService {

	@Override
	public Integer doAdd(String str1, String str2) throws Exception {
		
		return Integer.parseInt(str1) + Integer.parseInt(str2);
	}

}
