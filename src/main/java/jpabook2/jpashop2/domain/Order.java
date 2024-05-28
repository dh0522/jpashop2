package jpabook2.jpashop2.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

	@Id @GeneratedValue
	@Column(name = "order_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id") // 매핑을 뭘로 할거냐 -> Fk
	private Member member;

	@OneToMany(mappedBy = "order" , cascade = CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<>();

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL )
	@JoinColumn(name = "delivery_id") // 연관관계 주인
	private Delivery delivery;

	private LocalDateTime orderDate;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;


	// == 연관관계 메서드 == //

	public void setMember( Member member ){
		// 멤버 셋팅
		// 양방향 연관관계 메서드
		this.member = member;
		member.getOrders().add(this);
	}
	public void addOrderItem( OrderItem orderItem){
		orderItems.add(orderItem);
		orderItem.setOrder( this );
	}

	public void setDelivery( Delivery delivery ){
		this.delivery = delivery;
		delivery.setOrder(this);
	}
}














