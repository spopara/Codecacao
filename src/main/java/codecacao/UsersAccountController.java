package codecacao;

import java.util.HashSet;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import resources.UserDto;
import rest_response.Response;
import rest_response.Views;

/**
 * This class handles REST calls on the user collection.
 * 
 * @author Srdan Popara
 *
 */
@RestController
public class UsersAccountController extends UsersControllerAbstract {

	@RequestMapping(method = RequestMethod.OPTIONS, value = "/api/v1/users/{id}")
	public ResponseEntity<String> getUserAccountOptions(HttpServletResponse response) {
		final HttpHeaders httpHeaders = new HttpHeaders();
		HashSet<HttpMethod> allowedMethods = new HashSet<HttpMethod>();
		allowedMethods.add(HttpMethod.OPTIONS);
		allowedMethods.add(HttpMethod.GET);
		allowedMethods.add(HttpMethod.DELETE);
		httpHeaders.setAllow(allowedMethods);
		return new ResponseEntity<String>(httpHeaders, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/api/v1/users/{id}", consumes = "application/json")
	public ResponseEntity<String> getUserAccount(@PathVariable("id") long id) throws JsonProcessingException {
		UserDto userDto = UsersUtil.getUserById(id, users);
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<String>(UsersUtil.filterResponseByView(new Response(userDto), Views.Extended.class),
				httpHeaders, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/api/v1/users/{id}")
	public ResponseEntity<String> deleteUserAccount(@PathVariable("id") long id) {
		UsersUtil.removeUserById(id, users);
		final HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<String>(httpHeaders, HttpStatus.NO_CONTENT);
	}

}