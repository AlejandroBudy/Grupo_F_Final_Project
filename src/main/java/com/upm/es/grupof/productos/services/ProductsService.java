package com.upm.es.grupof.productos.services;


import com.upm.es.grupof.database.DataBaseLoader;
import com.upm.es.grupof.productos.entities.Category;
import com.upm.es.grupof.productos.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {


	@Autowired
	DataBaseLoader dataBase;

	public Product getProductByName(String name) {
		try {
			return this.dataBase.getProductByName(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public void createProduct(Product product) throws Exception {
		if (this.dataBase.getProductByName(product.getName()) == null){
			this.dataBase.createProd(product);
		}

	}
  
	public int deleteProduct(Product product) throws Exception{
	    this.dataBase.deleteProduct(product);
	    return 0;
	}

	public void updateProduct(Product oldProduct, Category category, String name) throws Exception{
        this.dataBase.updateProduct(oldProduct, category, name);
	}
}
