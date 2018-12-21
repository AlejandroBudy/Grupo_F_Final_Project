package com.upm.es.grupof.productos.controller;

import com.upm.es.grupof.productos.entities.Category;
import com.upm.es.grupof.productos.entities.Product;
import com.upm.es.grupof.productos.services.ProductsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;


public class ProductsControllerTest {

	@InjectMocks
	private ProductsController controller;

	@Mock
	private ProductsService service;

	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		when(this.service.getProductByName("jeans"))
				.thenReturn(new Product(Category.ROPA, "jeans"));
	}

	@Test
	public void shouldReturnProductWhenExits(){
		Product product =
				this.controller.getProduct("jeans");
		assertNotNull(product);
		assertEquals(product.getCategory(), Category.ROPA);
		assertEquals(product.getName(),"jeans");
	}
}
