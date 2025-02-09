package com.GGB.member.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SingupForm {
	@NotEmpty(message = "ID를 입력해주세요")
	private String singupId;
	@NotEmpty(message = "비밀번호를 입력해주세요")
	private String singupPass;
	@NotEmpty(message = "이름을 입력해주세요")
	private String singupName;
	@NotEmpty(message = "이메일를 입력해주세요")
	private String singupEmail;

}
