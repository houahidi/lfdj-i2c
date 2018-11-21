package fr.uni.institute.library.tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
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
	Connection conn;
	
	@Before
	public void setUp() throws Exception {
		
		System.out.println("Initialisation des ressources");
		
		
		String url = "jdbc:sqlite:library-test.db";
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection(url);
		
		
		 // SQL statement for creating a new table
        
        	
        //String sql = "INSERT INTO t_category VALUES (2, NULL, 'Informatique')";
        
        //String sql = "drop table t_category";
        
        try {
        	String sql = "CREATE TABLE t_category (\n" + 
            		"  k_puid integer PRIMARY KEY AUTOINCREMENT ,\n" + 
            		"  fk_puid_parent integer default NULL,\n" + 
            		"  name varchar(255) NOT NULL\n" + 
            		")";
                Statement stmt = conn.createStatement();
            // create a new table
            stmt.execute(sql);
            sql = "INSERT INTO t_category VALUES (1, NULL, 'Informatique')";
            	stmt.execute(sql);
            	sql = "INSERT INTO t_category VALUES (2, NULL, 'Sciences')";
            	stmt.execute(sql);
            conn.commit();
            System.out.println("success");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
		
		
		
		categorieDao = new CategoryDaoJdbc(conn);
		nombre = 2;
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("liberation des ressources");
		try {
			String sql = "drop table t_category";
			Statement stmt = conn.createStatement();
            	stmt.execute(sql);
            conn.commit();
            System.out.println("success");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
