package jpabook2.jpashop2;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository // entity를 찾아주는 애
public class MemberRepository {

	// entitymanager
	@PersistenceContext
	private EntityManager em;

	public Long save(Member member){
		em.persist(member);
		return member.getId();
		// command 랑 쿼리를 분리해라
		// Id만 조회하게 함
	}

	public Member find(Long id ){
		return em.find(Member.class , id);
	}
}
