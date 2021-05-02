package com.topnotch.springdemo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.topnotch.springdemo.entity.Customer;

@Repository("customerDAO")
@Transactional
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Customer> query = session.createQuery("from Customer order by firstName,lastName",Customer.class);
		
		List<Customer> customersList = query.getResultList();
		
		return customersList;
	}

	@Override
	public void saveCustomer(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomer(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		Customer customer = session.get(Customer.class, id);
		
		return customer;
	}

	@Override
	public void deleteCustomer(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Customer customer = session.get(Customer.class, id);
		
		session.delete(customer);
	}
	
	@Override
	public List<Customer> searchCustomers(String data){
		
		Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Customer.class);
		criteria.add(Restrictions.like("firstName", data)).add(Restrictions.like("lastName", data));
		
		return criteria.list();
		
	}
}
