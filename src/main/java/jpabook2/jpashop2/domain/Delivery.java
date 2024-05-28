package jpabook2.jpashop2.domain;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;


import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {

	@Id @GeneratedValue
	@Column(name = "delivery_id")
	private Long id;

	@OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY )
	private Order order;

	@Embedded
	private Address address;

	@Enumerated(EnumType.STRING)
	// default : ordinal 컬럼명이 숫자로 들어감 1,2,3,4,---
	// 중간에 새로운 status가 생기면 오류가 남-> 따라서 절대 쓰면 안돼 -> 장애남
	private DeliveryStatus status;

}
