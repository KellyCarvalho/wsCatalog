package com.saleswarning.swcatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saleswarning.swcatalog.dto.CategoryDTO;
import com.saleswarning.swcatalog.dto.ProductDTO;
import com.saleswarning.swcatalog.entities.Category;
import com.saleswarning.swcatalog.entities.Product;
import com.saleswarning.swcatalog.repositories.CategoryRepository;
import com.saleswarning.swcatalog.repositories.ProductRepository;
import com.saleswarning.swcatalog.services.exceptions.DatabaseException;
import com.saleswarning.swcatalog.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repository;
	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
		Page<Product> list = repository.findAll(pageRequest);

		return list.map(x -> new ProductDTO(x));

		// ou
		/*
		 * List<ProductDTO> listDTO1 = new ArrayList<>(); for(Product cat :list ) {
		 * listDTO.add(new ProductDTO(cat)); }
		 */

	}

	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		// optional evitar que o obj seja nulo
		Optional<Product> obj = repository.findById(id);
		Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Product not found"));

		return new ProductDTO(entity, entity.getCategories());
	}

	// update e insert existem mais dados
	@Transactional
	public ProductDTO insert(ProductDTO dto) {

		Product entity = new Product();
		copyDTOToEntity(dto, entity);

		entity = repository.save(entity);

		return new ProductDTO(entity);
	}



	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product entity = repository.getOne(id);
			copyDTOToEntity(dto, entity);
			entity = repository.save(entity);

			return new ProductDTO(entity);
			
		} catch (EntityNotFoundException e) {

			throw new ResourceNotFoundException("ID not found: " + id);

		}

	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("ID not found: " + id);

		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");

		}

	}
	private void copyDTOToEntity(ProductDTO dto, Product entity) {
	entity.setName(dto.getName());
	entity.setDate(dto.getDate());
	entity.setDescription(dto.getDescription());
	entity.setPrice(dto.getPrice());
	entity.setImgUrl(dto.getImgUrl());
	entity.getCategories().clear();
	for(CategoryDTO catDto: dto.getCategories() ) {
		Category category = categoryRepository.getOne(catDto.getId());
		entity.getCategories().add(category);
	}
	
		
	}

}
