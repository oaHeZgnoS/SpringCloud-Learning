package com.szh.eureka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HelloService {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "helloFallback")
	public String hello() {
		long start = System.currentTimeMillis();

		String res = restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class).getBody();
		
		long end= System.currentTimeMillis();
		log.info("Spend time : "+ (end - start));
		return res;
	}

	public String helloFallback() {
		return "error";
	}
}
