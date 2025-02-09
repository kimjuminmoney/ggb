package com.GGB.member.domain;

import com.GGB.member.dto.SingupForm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@Data
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long member_id;
	
	@Column(name = "id", nullable =  false, unique =  true)
	private String id;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String email;
	
	public Member (SingupForm formDto) {
		this.id = formDto.getSingupId();
		this.password = formDto.getSingupPass();
		this.name = formDto.getSingupName();
		this.email = formDto.getSingupEmail();
	}
	
	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}
	
	

}
