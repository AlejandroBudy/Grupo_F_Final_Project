package com.upm.es.grupof.productos.controller;

import com.upm.es.grupof.productos.entities.Category;
import com.upm.es.grupof.productos.entities.Product;
import com.upm.es.grupof.productos.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/products")
public class ProductsController {


	@Autowired
	private ProductsService productsService;

	@RequestMapping(value = "/{name}",
			method = RequestMethod.GET,
			consumes = "application/json",
			produces = "application/json")
	public Product getProduct(@PathVariable("name") String name){
		return this.productsService.getProductByName(name);
	}

	//url type /newprod?name=manzana&category=ropa
	@RequestMapping(method = RequestMethod.POST,
			consumes = "application/json",
			produces = "application/json")
	public void createProduct(@RequestBody Product product) throws Exception {
		Category category;
		this.productsService.createProduct(product);


	}


}
