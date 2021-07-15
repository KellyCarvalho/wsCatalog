package com.saleswarning.swcatalog.resources;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saleswarning.swcatalog.entities.Category;
@RestController
@RequestMapping(value="/categories")
public class CategoryResource implements Serializable{

	private static final long serialVersionUID = 1L;
	@GetMapping
	public ResponseEntity<List<Category>> findAll(){
		List<Category> list = new ArrayList<>();
		list.add(new Category(1L,"Bookes"));
		list.add(new Category(1L,"Electronics"));
		return ResponseEntity.ok().body(list);
		
	}
	
	

}
