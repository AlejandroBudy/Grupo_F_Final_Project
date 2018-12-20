package com.upm.es.grupof.productos.controller;

import com.upm.es.grupof.productos.entities.Product;
import com.upm.es.grupof.productos.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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


}
