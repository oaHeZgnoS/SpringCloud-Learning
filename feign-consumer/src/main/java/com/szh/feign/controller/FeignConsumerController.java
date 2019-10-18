package com.szh.feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szh.feign.po.User;
import com.szh.feign.service.HelloService;
import com.szh.feign.service.RefactorHelloService;

@RestController
public class FeignConsumerController {

	@Autowired
	HelloService helloService;

	@Autowired
	RefactorHelloService refactorHelloService;

	@GetMapping("/feign-consumer")
	public String helloConsumer() {
		return helloService.hello();
	}

	@GetMapping("/feign-consumer2")
	public String helloConsumer2() {
		StringBuilder sb = new StringBuilder();
		sb.append(helloService.hello()).append("\n");
		sb.append(helloService.hello("szh")).append("\n");
		sb.append(helloService.hello("szh", 127)).append("\n");
		sb.append(helloService.hello(new User("szh", 127))).append("\n");
		return sb.toString();
	}

	@GetMapping("/feign-consumer3")
	public String helloConsumer3() {
		StringBuilder sb = new StringBuilder();
		sb.append(refactorHelloService.hello("MIMI")).append("\n");
		sb.append(refactorHelloService.hello("MIMI", 20)).append("\n");
		sb.append(refactorHelloService.hello(new com.szh.dto.User("MIMI", 20))).append("\n");
		return sb.toString();
	}
}
