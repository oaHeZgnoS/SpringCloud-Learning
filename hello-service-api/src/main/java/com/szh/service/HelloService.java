package com.szh.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.szh.dto.User;

@RequestMapping("/refactor")
public interface HelloService {

	@GetMapping("/hello4")
	String hello(@RequestParam("name") String name);

	@GetMapping("/hello5")
	User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age);

	@PostMapping("/hello6")
	String hello(@RequestBody User user);
}
