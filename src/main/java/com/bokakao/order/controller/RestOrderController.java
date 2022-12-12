package com.bokakao.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bokakao.order.service.OrderService;

import io.swagger.annotations.Api;

@Api(tags = {"RestOrderController Api v1 -  주문"})
@RestController
@RequestMapping("/api/v1/orders")
public class RestOrderController {
	
	@Autowired
	private OrderService orderService;
	
}
