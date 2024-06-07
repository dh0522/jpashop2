package jpabook2.jpashop2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j // == Logger log = LoggerFactory.getLogger(getClass());
public class HomeController {


	@RequestMapping("/")
	public String home(){
		log.info("home controller");
		return "home"; // home.html로 감
	}
}
