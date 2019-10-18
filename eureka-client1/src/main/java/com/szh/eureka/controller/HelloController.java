package com.szh.eureka.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.szh.eureka.po.User;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HelloController {

	@Autowired
	private Registration registration; // 服务注册

	@Autowired
	private DiscoveryClient client; // 服务发现客户端

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(/*HttpServletRequest req*/) throws InterruptedException {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		ServiceInstance instance = serviceInstance(req.getLocalPort());
		String result = "/hello, host:port=" + instance.getUri() + ", " + "service_id:" + instance.getServiceId();
		// 让处理线程等待几秒钟
		int sleepTime= new Random().nextInt(3000);
		/*log.info("sleepTime:" + sleepTime);
		Thread.sleep(sleepTime);*/
		log.info(result);
		return result;
	}

	@RequestMapping(value = "/hello1", method = RequestMethod.GET)
	public String hello1(@RequestParam String name) {
		return "Hello " + name;
	}

	@RequestMapping(value = "/hello2", method = RequestMethod.GET)
	public User hello2(@RequestHeader String name, @RequestHeader Integer age) {
		return new User(name, age);
	}

	@RequestMapping(value = "/hello3", method = RequestMethod.POST)
	public String hello3(@RequestBody User user) {
		return "Hello " + user.getName() + ", " + user.getAge();
	}

	public ServiceInstance serviceInstance(int port) {
		List<ServiceInstance> list = client.getInstances(registration.getServiceId());
		if (list != null && list.size() > 0) {
			for (ServiceInstance itm : list) {
				log.info("在服务注册表中的本次循环到的实例: {}, {}:{}", itm.getServiceId(), itm.getHost(), itm.getPort());
				boolean same = false;
				try {
					same = InetAddress.getByName(itm.getHost()).isSiteLocalAddress();
				} catch (UnknownHostException e) {
					log.error("判断自身服务主机是否和本次循环到的该服务主机属于同一机器的过程中出错, cause by: {}", e.getMessage());
				}
				if (itm.getPort() == port && same)
					return itm;
			}
		}
		return null;
	}

	public static void main(String[] args) throws UnknownHostException {
		/*
		 * InetAddress.getAllByName("peer1");
		 * InetAddress.getAllByName("CJPC-130");
		 * InetAddress.getAllByName("127.0.0.1");
		 */
		String s1 = InetAddress.getLocalHost().getHostName();
		System.out.println(s1); // CJPC-130
		String s2 = InetAddress.getLocalHost().getCanonicalHostName();
		System.out.println(s2); // CJPC-130
		boolean b1 = InetAddress.getLocalHost().isSiteLocalAddress();
		System.out.println(b1); // true
		boolean b2 = InetAddress.getByName("CJPC-130").isSiteLocalAddress();
		System.out.println(b2); // true
		boolean b3 = InetAddress.getByName("peer1").isSiteLocalAddress();
		System.out.println(b3); // false
		boolean b4 = InetAddress.getByName("127.0.0.1").isSiteLocalAddress();
		System.out.println(b4); // false
	}
}
