package com.hk.dtos;

import java.io.Serializable;
//DTO 만들기 순서
//-멤버필드 작성
//-default 생성자
//-생성자 오버로딩:생성자를 통해서 필드 초기화
//-getter,setter 메서드 작성
//-toString() 오버라이딩
//편하게 작성하기 위해 지원하는 라이브러리-->lombok 라이브러리
import java.sql.Date;
public class UserDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private int seq;
	private String id;
	private String name;
	private String password;
	private String address;
	private String email;
	private String enabled;
	private String role;
	private Date regDate;
	public UserDto() {
		
	}
	public UserDto(int seq, String id, String name, String password, String address, String email, String enabled,
			String role, Date regDate) {
		super();
		this.seq = seq;
		this.id = id;
		this.name = name;
		this.password = password;
		this.address = address;
		this.email = email;
		this.enabled = enabled;
		this.role = role;
		this.regDate = regDate;
	}
	public UserDto(String id, String name, String password, String address, String email) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.address = address;
		this.email = email;
	}
	public UserDto(String id, String address, String email) {
		super();
		this.id = id;
		this.address = address;
		this.email = email;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "UserDto [seq=" + seq + ", id=" + id + ", name=" + name + ", password=" + password + ", address="
				+ address + ", email=" + email + ", enabled=" + enabled + ", role=" + role + ", regDate=" + regDate
				+ "]";
	}	
}
