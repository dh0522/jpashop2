package jpabook2.jpashop2.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import jpabook2.jpashop2.domain.Member;
import jpabook2.jpashop2.repository.MemberRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;

	@Test
	public void 회원가입() throws Exception{

		Member member = new Member();
		member.setName("kim");

		Long savedId = memberService.join(member);


		assertEquals(member, memberRepository.findOne(savedId));

	}


	@Test
	public void 중복_회원_예외() throws Exception{

		Member member1 = new Member();
		member1.setName("kim1");

		Member member2 = new Member();
		member2.setName("kim1");

		memberService.join(member1);
		try{
			memberService.join(member2);
		}catch( IllegalStateException e ){
			return;
		}


		fail("예외가 발생해야 한다.");

	}
}






