package com.saleswarning.swcatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saleswarning.swcatalog.entities.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
