package ru.svyaznoybank.handbook.ejb.dto;

import ru.svyaznoybank.handbook.ejb.util.BeanUtil;
import ru.svyaznoybank.handbook.jpa.domain.User;

public class UserDto extends EntityDto {
	
	private String nickname;
	
	public UserDto(User user) {
		BeanUtil.copyProperties(user, this, "offerTypes", "handbookTypes");
	}

	// --- Getters / Setters ---
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}