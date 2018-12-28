package com.upm.es.grupof.database;

import com.upm.es.grupof.productos.entities.Category;
import com.upm.es.grupof.productos.entities.Product;
import com.upm.es.grupof.productos.repository.ProductsRepository;
import com.upm.es.grupof.users.entities.User;
import com.upm.es.grupof.users.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class DataBaseLoaderTest {

	@InjectMocks
	private DataBaseLoader dataBaseLoader;
	@Mock
	private ProductsRepository repository;
	@Mock
	private UserRepository userRepository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(this.repository.findByName("Jeans"))
				.thenReturn(this.buildProductList());
		when(this.repository.findByName("ggg"))
				.thenReturn(null);
		when(this.userRepository.findByMail("atorresato@gmail.com"))
				.thenReturn(this.buildCorrectUser());
		doNothing().when(this.userRepository).delete(this.buildCorrectUser());

	}

	@Test
	public void shouldReturnProductWhenExits() throws Exception {
		Product product = this.dataBaseLoader.getProductByName("Jeans");
		assertEquals(product.getCategory(),Category.ROPA);
		assertEquals(product.getName(),"Jeans");
	}

	@Test(expected = Exception.class)
	public void shouldThrowExceptionProductWhenNotExits() throws Exception {
		this.dataBaseLoader.getProductByName("ggg");
	}

	@Test (expected = Exception.class)
	public void deletingNonExistingProductThrowsException() throws Exception{
		Product product = new Product(Category.ROPA,"ggg");
		this.dataBaseLoader.deleteProduct(product);
	}

	@Test
	public void shouldCreateNonExistingProduct(){
		this.dataBaseLoader.createProd(new Product(Category.HERRAMIENTAS, "destornillador"));}

	@Test
	public void productCorrectlyDeletedDoesntThrowException() throws  Exception{
		Product existingProduct = new Product(Category.ROPA, "Jeans");
		this.dataBaseLoader.deleteProduct(existingProduct);
	}

	@Test (expected = Exception.class)
	public void updatingNonExistingProductsThrowsException() throws Exception{
		Product existingProduct = new Product(Category.ROPA, "ggg");
		this.dataBaseLoader.updateProduct(existingProduct, Category.ROPA, "nuevo");
	}

	@Test (expected = Exception.class)
	public void updatingExistingProductWithDifferentCategoryThrowsException() throws Exception{
		Product existingProduct = new Product(Category.COMIDA, "Jeans");
		this.dataBaseLoader.updateProduct(existingProduct, Category.ROPA, "nuevo");
	}

	@Test
	public void updatingExistingProductWorksCorrectly() throws Exception{
		Product existingProduct = new Product(Category.ROPA, "Jeans");
		this.dataBaseLoader.updateProduct(existingProduct, Category.ROPA, "nuevo");
	}

	@Test
	public void shouldReturnCorrectUser(){
		this.dataBaseLoader.getUserByMail("atorresato@gmail.com");
	}

	@Test
	public void shouldDeleteUser(){
		this.dataBaseLoader.deleteUser(this.buildCorrectUser());
	}


	private List<Product> buildProductList(){
		Product product = new Product(Category.ROPA,"Jeans");
		List<Product> productList = new ArrayList<>();
		productList.add(product);
		return productList;
	}

	private User buildCorrectUser(){
		GrantedAuthority[] userRoles = { new SimpleGrantedAuthority("ROLE_USER") };
		return new User("Alex",
				"Torres",
				"a@a.com",
				"test", Arrays.asList(userRoles));
	}
}
