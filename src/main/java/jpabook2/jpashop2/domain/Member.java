package jpabook2.jpashop2.domain;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Id;


import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {

	@Id @GeneratedValue
	@Column(name = "member_id")
	private Long id;

	private String name;

	@Embedded
	private Address address;

	@OneToMany(mappedBy = "member") // 연관관계의 주인이 아니다.
	// 나는 order 테이블의 member 컬럼에 의해 매핑이 된거이다.
	// 즉 읽기 전용이 된 것.
	private List<Order> orders = new ArrayList<>();
	// -> 이 컬렉션 수정하지마
	// 컬렉션은 필드에서 초기화 하는것이 가장 좋다.

}
