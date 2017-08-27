package codecacao;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
@WebMvcTest(UsersCollectionController.class)
@AutoConfigureMockMvc
public class UsersCollectionControllerTest {
	@Autowired
	private MockMvc mvc;

	/**
	 * Tests that allowed methods (OPTIONS,GET,POST) for the user collection are
	 * returned correctly.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUserCollectionOptions() throws Exception {
		MvcResult mvcResult = this.mvc.perform(options("/api/v1/users")).andReturn();
		Assert.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		String allowMethods = mvcResult.getResponse().getHeader("allow");
		Assert.assertTrue(allowMethods.contains("OPTIONS"));
		Assert.assertTrue(allowMethods.contains("GET"));
		Assert.assertTrue(allowMethods.contains("POST"));
	}

	/**
	 * Tests that user collection retrieval returns OK.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetUserCollection() throws Exception {
		this.mvc.perform(get("/api/v1/users?page=5&size=2&sort=type,asc").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	/**
	 * Tests that user collection retrieval with a bad URL returns BAD REQUEST.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetUserCollectionNegative() throws Exception {
		this.mvc.perform(get("/api/v1/users?page=5&size=2&sort=dummy").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	/**
	 * Tests that new user registration returns OK.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRegisterNewUser() throws Exception {
		String jsonString = "{\"title\":\"Mr.\",\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john.doe@i-ways.hr\",\"phoneCode\":\"+385\",\"phoneNumber\":\"003859126565484\",\"password\":\"temp213test\",\"tos\":true,\"role\":{\"id\":8}}";
		this.mvc.perform(post("/api/v1/users").content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
}
