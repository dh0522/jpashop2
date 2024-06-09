package jpabook2.jpashop2.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook2.jpashop2.domain.item.Book;
import jpabook2.jpashop2.domain.item.Item;
import jpabook2.jpashop2.repository.ItemRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

	private final ItemRepository itemRepository;

	@Transactional
	public void saveItem(Item item){
		itemRepository.save(item);
	}

	@Transactional
	public void updateItem( Long itemId ,  String name ,int price , int stockQuantity){

		Item findItem = itemRepository.findOne(itemId); // itemId로 DB에서 영속성 엔티티를 가져온 것

		findItem.setPrice(price);
		findItem.setName(name);
		findItem.setStockQuantity(stockQuantity);


		/**
		 * 이후에 아무것도 저장할 필요가 없다.
		 * ex ) itemRepository.save( findItem ) 이런거..
		 * 왜냐면 이미 findItem이 영속성 엔티티이기 때문에
		 * jpa에서 변경을 감지하여 다 관리하고 있고 바로 업데이트해줌
		 */
	}

	public List<Item> findItems(){
		return itemRepository.findAll();
	}

	public Item findOne(Long itemId){
		return itemRepository.findOne(itemId);
	}



}
