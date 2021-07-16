package com.saleswarning.swcatalog.resources;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saleswarning.swcatalog.dto.CategoryDTO;
import com.saleswarning.swcatalog.entities.Category;
import com.saleswarning.swcatalog.services.CategoryService;
@RestController
@RequestMapping(value="/categories")
public class CategoryResource implements Serializable{
	@Autowired
	private CategoryService service;

	private static final long serialVersionUID = 1L;
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll(){
		List<CategoryDTO> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
		
	}
	
	

}
