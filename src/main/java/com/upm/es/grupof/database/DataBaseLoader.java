package com.upm.es.grupof.database;

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
		if(productList == null || productList.size() == 0) throw new Exception("Not Product Found");
		return productList.iterator().next();
	}

	public int deleteProduct(Product product) throws Exception{
		getProductByName(product.getName());
		repository.delete(product);
		return 0;
	}

	public void createProd(Product product){
		repository.save(product);
	}

	public void updateProduct(Product oldProduct, Category newCategory, String newName) throws Exception{
	    Product product = getProductByName(oldProduct.getName());
	    if (product.getCategory().equals(oldProduct.getCategory())){
            product.setCategory(newCategory);
            product.setName(newName);
        }
        else {
            throw new Exception("Product category doesn't match");
        }
    }
}
