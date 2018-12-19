package com.upm.es.grupof.productos.controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProductsControllerTest {

	@Test
	public void ShouldReturnGreeting(){
		ProductsController controller = new ProductsController();
		assertEquals(controller.dummyMethod(),"Hello world");
	}
}
