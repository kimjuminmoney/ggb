package com.GGB.testData;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.GGB.member.domain.Member;
import com.GGB.member.repository.LoginRepository;

@Component
public class DataInit implements ApplicationRunner {

	private final LoginRepository loginRepository;
	
	

	public DataInit(LoginRepository loginRepository) {
		super();
		this.loginRepository = loginRepository;
	}



	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		/*
		 * Member member1 = new Member(); member1.setId("test");
		 * member1.setPassword("test"); member1.setName("김주민");
		 * member1.setEmail("dkrnlqkqn586@naver.com"); loginRepository.save(member1);
		 */
	}

}
