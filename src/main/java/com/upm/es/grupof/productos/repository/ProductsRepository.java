package com.upm.es.grupof.productos.repository;

import com.upm.es.grupof.productos.entities.Category;
import com.upm.es.grupof.productos.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductsRepository extends CrudRepository<Product, Long> {

	/**
	* Inner methods:
	* 	save(Product)
	*	delete(Product)
	*	find(Product)
	*	find(Long)
	*	findAll()
	* */

	List<Product> findByName(String name);
	List<Product> findByCategory(Category category);
}
