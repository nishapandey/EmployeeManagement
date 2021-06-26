package com.example.employeeManagement;

import java.beans.JavaBean;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class EmployeeService {
	
	@Autowired
	EmployeeRepository repository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	public List<EmployeeEntity> getEmployees(){
		return repository.findAll();
	} 
	
	public EmployeeEntity getEmployee(Long id){
		return repository.getById(id);
	}
	
	public EmployeeEntity saveEmployee(EmployeeEntity entity){
		  entityManager.persist(entity);   
		  return entity;
	}
	
	public void deleteEmployee(Long id){
		 repository.deleteById(id);
	}
	
}
