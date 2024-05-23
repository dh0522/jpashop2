package jpabook2.jpashop2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {


	// model -> controller에서 데이터를 넣어서 view로 넘길 수 있음
	@GetMapping("hello")
	public String hello(Model model ){
		model.addAttribute("data","hello!!!!");
		return "hello";
		// return == 화면 이름
	}
}
