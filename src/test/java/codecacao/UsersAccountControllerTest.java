package codecacao;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

/**
 * This class performs test on the UserAccountController rest service.
 * 
 * @author Srdan Popara
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UsersAccountController.class)
@AutoConfigureMockMvc
public class UsersAccountControllerTest {
	@Autowired
	private MockMvc mvc;

	/**
	 * Tests that allowed methods (OPTIONS,GET,DELETE) for the user account are
	 * returned correctly.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUserAccountOptions() throws Exception {
		MvcResult mvcResult = this.mvc.perform(options("/api/v1/users/22")).andReturn();
		Assert.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		String allowMethods = mvcResult.getResponse().getHeader("allow");
		Assert.assertTrue(allowMethods.contains("OPTIONS"));
		Assert.assertTrue(allowMethods.contains("GET"));
		Assert.assertTrue(allowMethods.contains("DELETE"));
	}

	/**
	 * Test that the user account retrieval returns OK.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetUserAccount() throws Exception {
		this.mvc.perform(get("/api/v1/users/2").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	/**
	 * Test that the user account deletion returns NO CONTENT.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteUserAccount() throws Exception {
		this.mvc.perform(delete("/api/v1/users/2")).andExpect(status().isNoContent());
	}
}
