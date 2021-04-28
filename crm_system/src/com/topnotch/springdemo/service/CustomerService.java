package com.topnotch.springdemo.service;

import java.util.List;

import com.topnotch.springdemo.entity.Customer;

public interface CustomerService {
	public List<Customer> getCustomers();
	public void saveCustomer(Customer customer);
}
