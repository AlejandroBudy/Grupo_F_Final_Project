package com.upm.es.grupof.productos.database;

import com.upm.es.grupof.productos.entities.Category;
import com.upm.es.grupof.productos.entities.Product;
import com.upm.es.grupof.productos.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DataBaseLoader {

	@Autowired
	private ProductsRepository repository;

	@PostConstruct
	private void initDataBase(){

		repository.save(new Product(Category.ROPA,"Jeans"));
		repository.save(new Product(Category.ROPA,"T-shirt"));
		repository.save(new Product(Category.ROPA,"Jersey"));
		repository.save(new Product(Category.ROPA,"Coat"));

		repository.save(new Product(Category.COMIDA,"Avocado"));
		repository.save(new Product(Category.COMIDA,"Omelette"));
		repository.save(new Product(Category.COMIDA,"Fruit"));
		repository.save(new Product(Category.COMIDA,"Burger"));

	}


	public Product getProductByName(String name) throws Exception {
		List<Product> productList = repository.findByName(name);
		Product product = productList.iterator().next();
		if(product == null) throw new Exception("Not Product Found");
		return product;
	}

	public void deleteProduct(Product product){
		repository.delete(product);
	}
}
