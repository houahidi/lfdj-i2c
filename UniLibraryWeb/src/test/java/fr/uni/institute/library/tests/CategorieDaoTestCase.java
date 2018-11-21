package fr.uni.institute.library.tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.uni.institute.library.business.inventory.Category;
import fr.uni.institute.library.dao.CategoryDao;
import fr.uni.institute.library.dao.jdbc.CategoryDaoJdbc;

public class CategorieDaoTestCase {

	private CategoryDao categorieDao;
	private int nombre;
	
	@Before
	public void setUp() throws Exception {
		
		System.out.println("Initialisation des ressources");
		
		
		String url = "jdbc:sqlite:library.db";
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection(url);
		categorieDao = new CategoryDaoJdbc(conn);
		nombre = 2;
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("liberation des ressources");
	}

	@Test()
	public void testResearchAllCategories() {
		System.out.println("testResearchAllCategories ");
		try {
			Collection<Category> liste = categorieDao.researchAllCategories();
			assertNotNull(liste);
			assertEquals(nombre, liste.size());
			
		}catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
