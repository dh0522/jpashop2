package jpabook2.jpashop2.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {

	private String city;
	private String street;
	private String zipcode;

	// 함부로 new로 생성하면 안되겠네 라고 생각해야함
	protected Address(){
	}
	public Address(String city , String street, String zipcode){
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}

}
