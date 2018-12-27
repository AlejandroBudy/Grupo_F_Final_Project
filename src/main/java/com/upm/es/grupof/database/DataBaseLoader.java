package com.upm.es.grupof.database;

import com.upm.es.grupof.productos.entities.Category;
import com.upm.es.grupof.productos.entities.Product;
import com.upm.es.grupof.productos.repository.ProductsRepository;
import com.upm.es.grupof.users.entities.User;
import com.upm.es.grupof.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
public class DataBaseLoader {

	@Autowired
	private ProductsRepository productsRepository;

	@Autowired
	private UserRepository userRepository;



	@PostConstruct
	private void initDataBase(){

		productsRepository.save(new Product(Category.ROPA,"Jeans"));
		productsRepository.save(new Product(Category.ROPA,"T-shirt"));
		productsRepository.save(new Product(Category.ROPA,"Jersey"));
		productsRepository.save(new Product(Category.ROPA,"Coat"));

		productsRepository.save(new Product(Category.COMIDA,"Avocado"));
		productsRepository.save(new Product(Category.COMIDA,"Omelette"));
		productsRepository.save(new Product(Category.COMIDA,"Fruit"));
		productsRepository.save(new Product(Category.COMIDA,"Burger"));

		GrantedAuthority[] userRoles = { new SimpleGrantedAuthority("ROLE_USER") };
		userRepository.save(new User("Alejandro","Torres",
				"atorresato@gmail.com","test", Arrays.asList(userRoles)));
	}


	public Product getProductByName(String name) throws Exception {
		List<Product> productList = productsRepository.findByName(name);
		if(productList == null || productList.size() == 0) throw new Exception("Not Product Found");
		return productList.iterator().next();
	}

	public int deleteProduct(Product product) throws Exception{
		getProductByName(product.getName());
		productsRepository.delete(product);
		return 0;
	}

	public void createProd(Product product){
		productsRepository.save(product);
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
