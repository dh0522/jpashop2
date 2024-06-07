package jpabook2.jpashop2.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import jpabook2.jpashop2.domain.Address;
import jpabook2.jpashop2.domain.Member;
import jpabook2.jpashop2.service.MemberService;
import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	// get == 화면을 열고
	@GetMapping("/members/new")
	public String createForm(Model model){
		// controller -> view로 넘어갈 때 model에 담아서 넘김
		model.addAttribute("memberForm", new MemberForm());
		return "members/createMemberForm";
	}

	// post == 실제 데이터를 등록
	// @valid 어노테이션을 쓰면 memberForm 에서 받는 데이터중 notempty 와 같은 기능들을 다 validation 해줌
	// binding result 가 있으면 , 만약 받은 form 에서 받은 데이터 중 에러가 있는채로 result error 로 들어옴
	// binding result 없으면 whitelabel 오류가 난다.
	@PostMapping("/members/new")
	public String create(@Valid MemberForm form , BindingResult result){

		if( result.hasErrors() ){
			return "members/createMemberForm";
		}

		Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

		Member member = new Member();
		member.setName(form.getName());
		member.setAddress(address);

		memberService.join(member);
		// 저장되고 첫번째 페이지로 돌아가 있으면
		return "redirect:/";
	}


	@GetMapping("/members")
	public String list(Model model){
		List<Member> members = memberService.findMembers();
		model.addAttribute("members",members);
		// model.addAttribute("members", memberService.findMembers()); 로 인라인으로 구현해도 된다.

		return "members/memberList";
	}
}
