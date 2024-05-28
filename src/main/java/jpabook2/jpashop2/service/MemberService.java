package jpabook2.jpashop2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook2.jpashop2.domain.Member;
import jpabook2.jpashop2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
// 데이터 변경하는 것에서는 꼭 Tranactional이 있어야함.
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {


	private final MemberRepository memberRepository;


	// 회원 가입
	@Transactional
	public Long join( Member member ){

		// 중복회원 검증
		validateDuplicateMember(member);

		memberRepository.save(member);
		return member.getId();

	}

	private void validateDuplicateMember(Member member) {
		// exception

		List<Member> findMembers = memberRepository.findByName(member.getName());
		if( !findMembers.isEmpty() ){
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}

	}

	// 회원 전체 조회

	public List<Member> findMembers(){
		return memberRepository.findAll();
	}

	public Member findOne(Long memberId){
		return memberRepository.findOne(memberId);
	}


}

















