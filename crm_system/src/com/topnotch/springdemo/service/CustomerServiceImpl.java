package com.topnotch.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.topnotch.springdemo.dao.CustomerDAO;
import com.topnotch.springdemo.entity.Customer;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	@Qualifier("customerDAO")
	private CustomerDAO customerDAO;
	
	@Override
	public List<Customer> getCustomers() {
		return customerDAO.getCustomers();
	}

	@Override
	public void saveCustomer(Customer customer) {
		customerDAO.saveCustomer(customer);
	}

	@Override
	public Customer getCustomer(int id) {
		return customerDAO.getCustomer(id);
	}

}
