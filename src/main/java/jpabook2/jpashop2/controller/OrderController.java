package jpabook2.jpashop2.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jpabook2.jpashop2.domain.Member;
import jpabook2.jpashop2.domain.Order;
import jpabook2.jpashop2.domain.item.Item;
import jpabook2.jpashop2.repository.OrderSearch;
import jpabook2.jpashop2.service.ItemService;
import jpabook2.jpashop2.service.MemberService;
import jpabook2.jpashop2.service.OrderService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	private final ItemService itemService;
	private final MemberService memberService;

	@GetMapping("/order")
	public String createForm(Model model){

		List<Member> members = memberService.findMembers();
		List<Item> items = itemService.findItems();

		model.addAttribute("members",members);
		model.addAttribute("items",items);

		return "order/orderForm";
	}

	@PostMapping("/order")
	public String order( @RequestParam("memberId") Long memberId,
						 @RequestParam("itemId") Long itemId ,
		 				 @RequestParam("count") int count){
		orderService.order(memberId, itemId,count);
		return "redirect:/orders";
	}


	@GetMapping("/orders")
	public String orderList( @ModelAttribute("orderSearch")OrderSearch orderSearch, Model model){

		List<Order> orders = orderService.findOrders(orderSearch);
		model.addAttribute("orders",orders);

		return "order/orderList";
	}

	@PostMapping("/orders/{orderId}/cancel")
	public String cancelOrder( @PathVariable("orderId") Long orderId){

		orderService.cancelOrder(orderId);

		return "redirect:/orders";
	}
}
















