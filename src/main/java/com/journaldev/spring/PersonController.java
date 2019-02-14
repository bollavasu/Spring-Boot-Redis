package com.journaldev.spring;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@RestController
public class PersonController {
	
	@Autowired
	private StringRedisTemplate template;
	
	@RequestMapping("/")
	public String welcome() throws Exception {
		setValueInRedis();
		return "Welcome to Spring Boot REST...";
	}
	
	public void setValueInRedis()throws Exception {
		
	    ValueOperations<String, String> ops = this.template.opsForValue();
		String key = "spring.boot.redis.test";
		if (!this.template.hasKey(key)) {
			ops.set(key, "Good morning, How are you ?");
		}
		System.out.println("Found key " + key + ", value=" + ops.get(key));	
		
	}
	
}
