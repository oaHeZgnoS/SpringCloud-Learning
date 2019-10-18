package com.szh.feign.po;

import lombok.Data;

@Data
public class User {

	private String name;
	private Integer age;

	@Override
	public String toString() {
		return "name=" + name + ", age=" + age;
	}

	public User(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

}
