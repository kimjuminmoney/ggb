package com.GGB.member.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {
	@NotEmpty(message = "ID를 입려해주세요")
	private String loginId;
	@NotEmpty(message = "비밀번호를 입려해주세요")
	private String loginPass;

}
