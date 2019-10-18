package com.szh.feign.service;

import org.springframework.cloud.openfeign.FeignClient;

import com.szh.service.HelloService;

@FeignClient("HELLO-SERVICE")
public interface RefactorHelloService extends HelloService {

}
