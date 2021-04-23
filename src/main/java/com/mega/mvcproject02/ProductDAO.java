package com.mega.mvcproject02;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
// 어노테이션의 기능 해당 클래스가 특정한 역할을 하게 등록한다, 싱글톤으로 만들어준다.
// 한개만 객체생성, 한개만 만드는게 싱글톤
// 같은 주소값을 계속 가져다가 사용한다.

public class ProductDAO {

	@Autowired
	SqlSessionTemplate my; // mybatis 사용하는 싱글톤

	// 상품 등록 관련 crud 생성

	// 상품 등록(create)
	public void insert(ProductVO productVO) { // 컨트롤러에서 dao.insert 기능을 사용하면 아래를 실행
		my.insert("product.insert", productVO); // mybatis의 기능중 insert를 실행
		// mybatis를 활용해서 mapper에 있는 namespace가 product인것에서 insert id를 가진 기능을 수행
		System.out.println("맵퍼 보내기 성공");
		// my.insert가 실행됫는지 확인 하는 출력값
	}

	// 해당 번호로 등록된 상품 조회(read)
	public List<ProductVO> read(ProductVO productVO) {	
		// 컨트롤러에서 dao.read 기능을 사용하면 아래를 실행
		// mapper의 sql문을 통해 리턴되는 값이 List 형태이기 때문에 리턴 타입을 List 받아줘야한다.
		if (productVO.getContact().equals("01011111111")) {// 관리자 번호 변경 가능
			// 입력값의 ProductVO중 contact가 01011111111일때는 아래를 실행
			List<ProductVO> list = my.selectList("product.all", productVO);
			// list 변수에 mybatist에 selectList 메서드 사용
			// mapper의 namespace가 product이고 id가 all인 sql문 사용
			System.out.println("맵퍼 보내기 성공");
			// 보냈는지 확인하는 출력
			return list;
			// selectList로 db에서 가져온 data를 리턴해준다.
		} else { // 입력된 contact값이 01011111111이 아닐때는 아래를 실행
			List<ProductVO> list = my.selectList("product.select", productVO);
			// list 변수에 mybatist에 selectList 메서드 사용
			// mapper의 namespace가 product이고 id가 select인 sql문 사용
			System.out.println("맵퍼 보내기 성공");
			// 보냈는지 확인하는 출력
			return list;
			// selectList로 db에서 가져온 data를 리턴해준다.
		}
	}

	// 상품 상세 조회(read)
	public ProductVO pdetail(ProductVO productVO) {
		// 컨트롤러에서 dao.pdetail 기능을 사용하면 아래를 실행
		// 리턴되는 타입이 db있는 1개의 row이기 때문에 ProductVO 타입으로 리턴이 된다.
		System.out.println("맵퍼 보내기 성공");
		return my.selectOne("product.detail", productVO);
		// 리턴은 항상 맨 마지막에 위치!
		// 리턴을 mybatis의 selectOne 메서드를 사용해서 sql문을 통해서 db의 1개의 row값을 가져온다.
	}

	// 상품 수정(update)
	public void update(ProductVO productVO) {// 컨트롤러에서 dao.update 기능을 사용하면 아래를 실행
		my.update("product.update", productVO);
		// 수정할 data는 ProductVO 타입으로 입력을 받는다.
		// mybatis의 update 메서드를 통해서 mapper의 sql문 사용해서 db의 data를 수정한다.
		System.out.println("맵퍼 보내기 성공");
	}

	// 상품 삭제(delete)
	public void delete(ProductVO productVO) {// 컨트롤러에서 dao.delete 기능을 사용하면 아래를 실행
		// 입력받은 번호값을 mybatis를 통해서 mapper로 전송
		my.delete("product.delete", productVO);
		// mapper의 spl문을 통해서 db의 data를 삭제한다.
		System.out.println("맵퍼 보내기 성공");
	}
}
