package com.szh.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.szh.feign.po.User;

// 这里的服务名不区分大小写
@FeignClient(name = "hello-service", fallback = HelloServiceFallback.class)
public interface HelloService {

	@RequestMapping("/hello")
	String hello();

	@GetMapping("/hello1")
	String hello(@RequestParam("name") String name);

	@GetMapping("/hello2")
	User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age);

	@PostMapping("/hello3")
	String hello(@RequestBody User user);
}
