package com.szh.eureka.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.szh.dto.User;
import com.szh.service.HelloService;

@RestController
public class RefactorController implements HelloService {

	@Override
	public String hello(@RequestParam String name) {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return "Hello " + req.getLocalPort() + name;
	}

	@Override
	public User hello(@RequestHeader String name, @RequestHeader Integer age) {
		return new User(name, age);
	}

	@Override
	public String hello(@RequestBody User user) {
		return "Hello " + user.getName() + ", " + user.getAge();
	}

}
