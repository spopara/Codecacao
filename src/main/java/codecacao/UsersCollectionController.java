package codecacao;

import java.net.URI;
import java.util.HashSet;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import resources.UserDto;
import rest_response.Response;
import rest_response.UsersResponse;
import rest_response.Views;

/**
 * This class handles REST calls on the user account.
 * 
 * @author Srdan Popara
 *
 */
@RestController
public class UsersCollectionController extends UsersControllerAbstract {

	@RequestMapping(method = RequestMethod.OPTIONS, value = "/api/v1/users")
	public ResponseEntity<String> getUserCollectionOptions(HttpServletResponse response) {
		final HttpHeaders httpHeaders = new HttpHeaders();
		HashSet<HttpMethod> allowedMethods = new HashSet<HttpMethod>();
		allowedMethods.add(HttpMethod.OPTIONS);
		allowedMethods.add(HttpMethod.GET);
		allowedMethods.add(HttpMethod.POST);
		httpHeaders.setAllow(allowedMethods);
		return new ResponseEntity<String>(httpHeaders, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/api/v1/users", consumes = "application/json")
	public ResponseEntity<String> getUserCollection(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int pageSize,
			@RequestParam(value = "sort", defaultValue = "type,asc") String sort, HttpServletResponse response) {
		try {
			UsersUtil.sortUsers(UsersUtil.extractOrderEnum(sort), users, collator);
			UsersResponse usersResponse = new UsersResponse(users.size(),
					UsersUtil.calculatePageCount(users.size(), pageSize),
					UsersUtil.getUsersForPage(page, pageSize, users));
			final HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);

			return new ResponseEntity<String>(
					UsersUtil.filterResponseByView(new Response(usersResponse), Views.Summary.class), httpHeaders,
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return UsersUtil.createBadRequestResponse();
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/api/v1/users", consumes = "application/json")
	public ResponseEntity<String> registerNewUser(@RequestBody UserDto userDto, HttpServletResponse response) {
		try {
			users.add(userDto);
			final HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			httpHeaders.setLocation(new URI("https://fleetch.com/api/v1/users/" + userDto.getId()));
			return new ResponseEntity<String>(httpHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return UsersUtil.createBadRequestResponse();
		}
	}

}