package com.saleswarning.swcatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saleswarning.swcatalog.entities.Category;
import com.saleswarning.swcatalog.entities.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
