package com.web.dto;

public class LoginDto {
	/** 아이디 */
	private String id;
	/** 이름 */
	private String name;
	/** 비밀번호 */
	private String password;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "SampleLoginDto [id=" + id + ", name=" + name + ", password=" + password + "]";
	}
}
