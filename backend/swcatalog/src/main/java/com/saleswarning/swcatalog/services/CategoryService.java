package com.saleswarning.swcatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saleswarning.swcatalog.dto.CategoryDTO;
import com.saleswarning.swcatalog.entities.Category;
import com.saleswarning.swcatalog.repositories.CategoryRepository;
import com.saleswarning.swcatalog.services.exceptions.DatabaseException;
import com.saleswarning.swcatalog.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository repository;

	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll(){
		List<Category> list = repository.findAll();
		List<CategoryDTO> listDTO = list.stream().map(x-> new CategoryDTO(x)).collect(Collectors.toList());
	
		//ou
  /* 
   * 	List<CategoryDTO> listDTO1 = new ArrayList<>();
   * for(Category cat :list ) {
	   listDTO.add(new CategoryDTO(cat));
   }*/
		
		
		
		
		return listDTO;
	}
	
	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		//optional evitar que o ID seja nulo
		Optional<Category> obj =repository.findById(id);
		Category entity = obj.orElseThrow(()-> new ResourceNotFoundException("Category not found"));
		
		return new CategoryDTO(entity) ;
	}
	
	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		
		Category entity = new Category();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		
		
		return new CategoryDTO(entity);
	}
	
	@Transactional
	public CategoryDTO update(Long id,CategoryDTO dto) {
		try {
			Category entity = repository.getOne(id);
			entity.setName(dto.getName());
			entity=repository.save(entity);
			
			return new CategoryDTO(entity);
		}catch (EntityNotFoundException e) {
			
			throw new ResourceNotFoundException("ID not found: "+id);
			
		}
	
		
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("ID not found: "+id);
			
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
			
		}
		
		
	
		
	}
	
	

}
