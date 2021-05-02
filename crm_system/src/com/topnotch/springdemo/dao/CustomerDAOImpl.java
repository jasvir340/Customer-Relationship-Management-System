package com.topnotch.springdemo.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.topnotch.springdemo.entity.Customer;

@Repository("customerDAO")
@Transactional
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private CriteriaBuilder criteriaBuilder;
	
	public CriteriaBuilder getCrtieriaBuilder(Session session) {
		return session.getCriteriaBuilder();
	}
	
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
		
		criteriaBuilder = getCrtieriaBuilder(session);
		CriteriaQuery<Customer> crit = criteriaBuilder.createQuery(Customer.class);
		Root<Customer> root = crit.from(Customer.class);
		
		Predicate firstNamePredicate = criteriaBuilder.like(root.get("firstName"), "%"+data+"%");
		Predicate lastNamePredicate = criteriaBuilder.like(root.get("lastName"), "%"+data+"%");
		
		crit.select(root).where(criteriaBuilder.or(firstNamePredicate,lastNamePredicate));
		
		Query<Customer> query = session.createQuery(crit);
		
		return query.getResultList();
		
	}
}
