package com.saleswarning.swcatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saleswarning.swcatalog.entities.Category;
import com.saleswarning.swcatalog.repositories.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository repository;
	@Transactional(readOnly = true)
	public List<Category> findAll(){
		
		return repository.findAll();
	}

}
