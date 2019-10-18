package com.szh.feign.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class HelloServiceFallback implements HelloService {

	@Override
	public String hello() {
		return "error";
	}

	@Override
	public String hello(@RequestParam("name") String name) {
		return "error";
	}

	@Override
	public com.szh.feign.po.User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age) {
		return new com.szh.feign.po.User("未知", 0);
	}

	@Override
	public String hello(@RequestBody com.szh.feign.po.User user) {
		return "error";
	}

}
