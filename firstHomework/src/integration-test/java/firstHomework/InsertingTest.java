package firstHomework;

import static org.junit.Assert.assertNotEquals;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

@Category(IntegrationTest.class)
public class InsertingTest extends Mockito{
	private DbConfig dbWork = DbConfig.getInstance();
	
	@Test
	public void testInsert() throws ServletException, IOException {
		String name = "test";
		String email = "test@test.com";
		
		assertNotEquals(0, dbWork.insert(name, email));
	}
}

