package jpabook2.jpashop2.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook2.jpashop2.domain.Member;

@Repository
public class MemberRepository {

	@PersistenceContext
	private EntityManager em;

	public void save(Member member){
		em.persist(member);
	}

	public Member findOne( Long id ){
		return em.find(Member.class , id) ;
	}

	// jpql
	// from의 대상이 entity객체에 대한 쿼리..
	public List<Member> findAll() {
		List<Member> result = em.createQuery("select m from Member m", Member.class)
			.getResultList();
		return result;

		/** 합치면 inline
		 *  return em.createQuery("select m from Member m", Member.class)
		 * 			.getResultList();
		 */
	}

	public List<Member> findByName( String name ){
		return em.createQuery("select m from Member m where m.name = :name", Member.class )
			.setParameter("name",name)
			.getResultList();
	}
}
