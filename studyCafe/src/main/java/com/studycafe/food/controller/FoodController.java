package com.studycafe.food.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studycafe.food.domain.Cart;
import com.studycafe.food.domain.Food;
import com.studycafe.food.domain.Order;
import com.studycafe.food.service.FoodService;


@Controller
public class FoodController {

	private static final Logger logger = LoggerFactory.getLogger(FoodController.class);
	
	@Autowired
	FoodService foodService;
	
	@GetMapping(value="/food/main")
	public String reqMain() {
		return "/food/mainP";
	}
	
	//main페이지 호출
	@RequestMapping(value="/food/mainC", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> reqMainC() throws Exception {
		System.out.println("reqMainC");
		Map<String, Object> map = new HashMap<String, Object>();
		int u_number = 1; //session으로 변환 예정
		List<Food> list = foodService.getFoodList();
		List<Cart> cartList = foodService.getCart(u_number);
		System.out.println(list);
		System.out.println(cartList);
		map.put("list", list);
		map.put("cartList", cartList);
		map.put("u_number", u_number);
		return map;
	}
	
	//장바구니에 상품 추가
	@GetMapping("/food/addCart")
	public void addCart(int food_no,HttpServletResponse res) throws Exception {
		int u_number = 1;
		Cart cart = new Cart(u_number, food_no);
		Cart check = foodService.existCart(cart);
		if(check != null) {
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
	        out.println("<script>alert('이미 주문 목록에 있습니다.'); location.href='/scafe/food/main';</script>");
			out.flush();
		}else{
			foodService.addCart(cart);
			res.sendRedirect("/scafe/food/main");
		}
	}
	
	//장바구니 수량 변경
	@GetMapping("/food/changeQ")
	public void changeQuantity(int pm, int food_no,HttpServletResponse res) throws Exception {
		int u_number = 1;
		Cart cart = new Cart(u_number, food_no);
		if(pm == 1) {
			foodService.upQuantity(cart);
		}else {
			foodService.downQuantity(cart);
		}
		
		res.sendRedirect("/scafe/food/main");
	}
	
	@GetMapping("/food/deleteCart")
	public void deleteCart(int food_no, HttpServletResponse res) throws Exception {
		int u_number = 1;
		if(food_no == 0) {
			foodService.deleteAllCart(u_number);
		}else {
			Cart cart = new Cart(u_number, food_no);
			foodService.deleteCart(cart);
		}
		res.sendRedirect("/scafe/food/main");
	}
	
	
	@GetMapping("/food/order")
	public String order(String order_no, int order_price, Model model) throws Exception {
		int u_number = 1;
		Map<String,Object> map = new HashMap<String, Object>();
		List<Cart> cart = foodService.getCart(u_number);
		map.put("order_no", order_no);
		map.put("u_number", u_number);
		map.put("order_price", order_price);
		map.put("cart", cart);
		foodService.insertOrder(map);
		foodService.insertDeatil(map);
		foodService.deleteAllCart(u_number);
		Order order = foodService.getOrderByNo(order_no);
		model.addAttribute("order", order);
		
		return "/food/success";
		
	}
	
	
	 
}

