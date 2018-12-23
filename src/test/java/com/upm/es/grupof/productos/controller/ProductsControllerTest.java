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
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;


public class ProductsControllerTest {

	@InjectMocks
	private ProductsController controller;

	@Mock
	private ProductsService service;
	private Product existingProduct;
    private Product nonExistingProduct;

	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
        this.nonExistingProduct = new Product(Category.COMIDA, "IDontExist");
        this.existingProduct = new Product(Category.ROPA, "nuevo");
		when(this.service.getProductByName("jeans"))
				.thenReturn(new Product(Category.ROPA, "jeans"));
        when(this.service.getProductByName("nuevo"))
                .thenReturn(new Product(Category.ROPA, "nuevo"));
		when(this.service.deleteProduct(nonExistingProduct)).thenThrow(new Exception());
		doThrow(new Exception()).when(this.service).updateProduct(nonExistingProduct, Category.ROPA, "nuevo");
	}

	@Test
	public void shouldReturnProductWhenExits(){
		Product product =
				this.controller.getProduct("jeans");
		assertNotNull(product);
		assertEquals(product.getCategory(), Category.ROPA);
		assertEquals(product.getName(),"jeans");
	}

	@Test (expected = Exception.class)
	public void nonExistingProductThrowsEXception() throws Exception{
        this.controller.deleteProduct(nonExistingProduct);
    }


    @Test
	public void shouldFinishOkWhenCreatedOk() throws Exception {
		this.controller.createProduct(new Product(Category.HERRAMIENTAS, "clavo"));
	}
    @Test (expected = Exception.class)
	public void updatingNonExistingProductThrowsException() throws Exception{
		this.controller.updateProduct(nonExistingProduct, Category.ROPA, "nuevo");
	}

	@Test
    public void productUpdatedCorrectly() throws Exception{
	    this.controller.updateProduct(existingProduct, Category.ROPA, "nuevo");
	    Product product = this.controller.getProduct("nuevo");
        assertEquals(Category.ROPA, product.getCategory());
        assertEquals("nuevo", product.getName());
    }
}
