package com.topnotch.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.topnotch.springdemo.dao.CustomerDAO;
import com.topnotch.springdemo.entity.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	@Qualifier("customerDAO")
	private CustomerDAO customerDAO;
	
	@RequestMapping("/list")
	public String listCustomers(Model model) {
		List<Customer> customersList = customerDAO.getCustomers();
		model.addAttribute("customers", customersList);
		return "list-customers";
	}
}
