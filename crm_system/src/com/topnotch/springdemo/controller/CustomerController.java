package com.topnotch.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.topnotch.springdemo.entity.Customer;
import com.topnotch.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	@Qualifier("customerService")
	private CustomerService customerSerivce;
	
	@GetMapping("/list")
	public String listCustomers(Model model) {
		List<Customer> customersList = customerSerivce.getCustomers();
		model.addAttribute("customers", customersList);
		return "list-customers";
	}
}
