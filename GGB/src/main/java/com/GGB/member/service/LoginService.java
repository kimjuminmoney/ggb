package com.GGB.member.service;

import com.GGB.member.domain.Member;
import com.GGB.member.dto.LoginForm;
import com.GGB.member.dto.SingupForm;

import jakarta.validation.Valid;

public interface LoginService {

	public Member login(LoginForm loginFrom);

	public boolean getLoginId(String singupId);

	public Member addMember(@Valid SingupForm singupFrom);
}
