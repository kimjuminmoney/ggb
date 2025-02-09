package com.GGB.member.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.GGB.member.domain.Member;
import com.GGB.member.dto.LoginForm;
import com.GGB.member.dto.SingupForm;
import com.GGB.member.repository.LoginRepository;

@Service
public class LoginServiceImpl implements LoginService {

	
	public LoginServiceImpl(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}

	LoginRepository loginRepository;
	
	@Override
	public Member login(LoginForm loginFrom) {
		Optional<Member> member = loginRepository.findByid(loginFrom.getLoginId());
		if(!member.orElseThrow(() -> new IllegalStateException("아이디와 비밀번호를 확인해주세요"))
			.checkPassword(loginFrom.getLoginPass())) {
			throw new IllegalStateException("아이디와 비밀번호를 확인해주세요");
		}
		return member.get();
	}
	
	@Override
	public boolean getLoginId(String id) {
		Optional<Member> member = loginRepository.findByid(id);
		return member.isPresent();
	}
	
	@Override
	public Member addMember(SingupForm formDto) {
		Member member = new Member(formDto);
		return loginRepository.save(member);
	}

}
