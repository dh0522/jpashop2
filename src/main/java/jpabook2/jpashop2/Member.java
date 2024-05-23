package jpabook2.jpashop2;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.Setter;

// repository -> entity찾아 주는 애
@Getter @Setter
@Entity
public class Member {

	@Id @GeneratedValue
	private Long id;
	private String username;


}
