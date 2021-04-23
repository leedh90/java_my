package com.mega.mvcproject02;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // 컨트롤러 선언
public class ProductController {// 입력값을 받아서 처리해주는 컨트롤러
	
	@Autowired
	ProductDAO dao;
	// 받아주는 기능 설정, 싱글톤으로 생성 후 하나의 주소값을 호출하여 계속 사용가능
	// ProductDAO의 객체를 dao로 생성
	@RequestMapping("insert")
	// 브라우저에서 입력된 값을 가상주소 insert로 보내주면 아래를 실행
	public void insert(ProductVO productVO) throws Exception {
		// 입력받은 값은 ProductVO타입이고 productVO이라는 변수를 사용해서 찍어 줄 수 있다.
		System.out.println(productVO);
		// 각각의 입력받은 값 확인 productVO를 toString으로 오버라이딩(재정의)해서 입력값을 찍을 수 있다.
		dao.insert(productVO); // ProductDAO class의 insert 메서드 실행
		// 입력받은 productVO 변수를 dao의 insert로 보내준다.
	}
	
	@RequestMapping("select")
	// 브라우저에서 입력된 값을 가상주소 select로 보내주면 아래를 실행
	public void select(ProductVO productVO, HttpSession session) {
		// 입력받은 값은 ProductVO 타입이고 productVO라는 변수명을 사용한다.
		// 세션을 사용하기 위해서 HttpSession을 import 시킨다.
		System.out.println("입력받은 번호는 : " + productVO.contact);
		// 입력받은 값 중 contact를 조건으로 사용하기 때문에 잘 받아왔는지 출력값 확인.
		List<ProductVO> list = dao.read(productVO);
		// 싱글톤인 ProductDAO의 read 메서드를 사용
		// db에 있는 여러개의 row를 가져와야해서 List 타입을 사용한다.
		// db에서 가져오는 data는 ProductVO 타입이다.
		// DAO에서 가져오는 결과값이 있으므로 List 타입의 list 변수에 넣어준다.
		System.out.println(list);
		// 가져온 dao의 리턴 값을 확인 하는 출력
		System.out.println("해당 전화번호 확인");
		session.setAttribute("userCon", list);
		// 넘겨줘서 사용할 VO 값을 따로 세선을 잡아준다.
		session.setAttribute("contact", productVO.contact);
		// 번호값을 세션으로 잡아주고 해당 번호값으로 리스트를 불러올때 사용한다.
	}
	
	@RequestMapping("pdetail")
	// 브라우저에서 입력된 값을 가상주소 pdetail로 보내주면 아래를 실행
	public void pdetail(ProductVO productVO, Model model) {
		System.out.println("입력받은 매물 번호는 " + productVO.pnum);
		// pnum으로 삭제 처리를 하기 때문에 넘어온 매물 번호 확인하는 출력
		ProductVO productVO2 = dao.pdetail(productVO);
		// dao의 pdetail 메서드를 사용해서 productVO2에 넣어주기.
		// 다시 넣어주는 이유 : 방 사진 1개만 등록하면 변경되서 2개가 나올 수 있도록 하기 위해.
		String pic = productVO2.getRoomimg();
		// productVO02의 roomimg 컬럼을 pic 변수에 넣어준다.
		System.out.println(pic);
		String pic2 = pic.replace("1", "2");
		// pic2변수에는 pic변수에 들어있는 값 중 1을 2로 교체해서 pic2로 넣어준다.
		productVO2.setRoomimg2(pic2);
		// 1 -> 2로 변경한 pic2를 productVO2에 set메서드 사용해서 넣어준다.
		System.out.println(pic2);
		String pic3 = pic2.replace("2", "3");
		productVO2.setRoomimg3(pic3);
		model.addAttribute("productVO2", productVO2);
		// 최종적으로 모델을 통해서 views에는 productVO2를 넘겨준다.
		// model은 views까지만 값을 넘겨주는 역할
	}
	
	@RequestMapping("update")
	// 브라우저에서 입력된 값을 가상주소 update로 보내주면 아래를 실행
	public void update(ProductVO productVO) {
		System.out.println("수정할 data는 : " + productVO);
		// 입력받은 값을 bag에 넣고 toString(재정의를)통해서 찍어주면 입력받은값 전체 확인 가능
		dao.update(productVO);
		// DAO 싱글톤의 update 메서드 사용
	}
	
	@RequestMapping("update2")
	// 브라우저에서 입력된 값을 가상주소 update2로 보내주면 아래를 실행	
	public void update2(ProductVO productVO) {
		System.out.println("수정할 data는 : " + productVO);
		// 입력받은 값을 bag에 넣고 toString(재정의를)통해서 찍어주면 입력받은값 전체 확인 가능
		dao.update(productVO);
		// DAO 싱글톤의 update 메서드 사용
	}
	
	@RequestMapping("delete")
	// 브라우저에서 입력된 값을 가상주소 delete로 보내주면 아래를 실행	
	public void delete(ProductVO productVO) {
		System.out.println("삭제하려는 상품 번호는 : " + productVO.pnum);
		// 입력받은 상품번호 확인하는 출력
		dao.delete(productVO); // DAO에 있는 delete 실행 DAO는 맵퍼파일에서 delete 실행
	}
	
	@RequestMapping("delete2")
	// 브라우저에서 입력된 값을 가상주소 delete2로 보내주면 아래를 실행	
	public void delete2(ProductVO bag) {
		System.out.println("삭제하려는 상품 번호는 : " + bag.pnum);
		 // 입력받은 상품번호 확인하는 출력
		dao.delete(bag); // DAO에 있는 delete 실행 DAO는 맵퍼파일에서 delete 실행
	}
	

}
	