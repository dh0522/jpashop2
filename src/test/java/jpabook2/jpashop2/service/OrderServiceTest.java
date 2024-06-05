package jpabook2.jpashop2.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jpabook2.jpashop2.domain.Address;
import jpabook2.jpashop2.domain.Member;
import jpabook2.jpashop2.domain.Order;
import jpabook2.jpashop2.domain.OrderStatus;
import jpabook2.jpashop2.domain.item.Book;
import jpabook2.jpashop2.domain.item.Item;
import jpabook2.jpashop2.exception.NotEnoughStockException;
import jpabook2.jpashop2.repository.OrderRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

	@Autowired
	EntityManager em;
	@Autowired OrderService orderService;
	@Autowired
	OrderRepository orderRepository;

	@Test
	public void 상품주문() throws Exception {

		Member member = createMember();

		Book book = createBook("JPA~", 10000,10);

		int orderCount = 2;

		Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

		Order getOrder = orderRepository.findOne(orderId);

		assertEquals(OrderStatus.ORDER, getOrder.getStatus(), "상품 주문시 상태는 ORDER");
		assertEquals(1, getOrder.getOrderItems().size(),"주문한 상품 종류 수가 정확해야한다.");
		assertEquals(10000*orderCount, getOrder.getTotalPrice(), "주문 가격은 가격*수량 이다.");
		assertEquals(8, book.getStockQuantity(),"주문 수량만큼 재고가 줄어야 한다.");
	}



	@Test
	public void 상품주문_재고수량초과() throws Exception {

		Member member = createMember();
		Item item = createBook("JPA~", 10000,10);
		int orderCount = 11;

		assertThrows(NotEnoughStockException.class,
			()-> orderService.order(member.getId(), item.getId(), orderCount),
			"재고 수량 부족 예외가 발생해야 한다.");

	}

	@Test
	public void 주문취소() throws Exception{

		Member member = createMember();
		Book item = createBook("jpa", 10000, 10);

		int orderCount = 2;

		Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

		orderService.cancelOrder(orderId);

		Order getOrder = orderRepository.findOne(orderId);

		assertEquals(OrderStatus.CANCEL,getOrder.getStatus(),"주문 취소시 상태는 CANCEL이다");
		assertEquals(10, item.getStockQuantity(),"주문 취소된 상품은 그만큼 재고가 증가해야 한다.");
	}


	private Book createBook(String name , int price, int stockQuantity) {
		Book book = new Book();
		book.setName(name);
		book.setPrice(price);
		book.setStockQuantity(stockQuantity);
		em.persist(book);
		return book;
	}

	private Member createMember() {
		Member member = new Member();
		member.setName("회원1");
		member.setAddress(new Address("서울", "강가", "123-123"));
		em.persist(member);
		return member;
	}
}



























