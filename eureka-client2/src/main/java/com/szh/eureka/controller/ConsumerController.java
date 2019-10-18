package com.szh.eureka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.szh.eureka.service.HelloService;

@RestController
public class ConsumerController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HelloService helloService;

	@RequestMapping(value = "/v1/ribbon-consumer", method = RequestMethod.GET)
	public String testBalance() {
		String res = restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class).getBody();
		return res;
	}

	@RequestMapping(value = "/ribbon-consumer", method = RequestMethod.GET)
	public String helloConsumer() {
		return helloService.hello();
	}
}
