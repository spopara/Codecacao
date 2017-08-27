package codecacao;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import resources.AddressDto;
import resources.AvatarDto;
import resources.CompanyDto;
import resources.RoleDto;
import resources.UserDto;
import rest_response.Response;

/**
 * Utility class for user account and collection manipulation.
 * 
 * @author Srdan Popara
 *
 */
public class UsersUtil {

	enum Order {
		ASC, DESC;
	}

	/**
	 * Calculates the number of pages needed to accommodate all records.
	 * 
	 * @param recordsCount
	 *            - the number of records.
	 * @param recordsPerPage
	 *            - the number of records that fit on a single page.
	 * @return number of pages.
	 */
	public static int calculatePageCount(int recordsCount, int recordsPerPage) {
		return (int) Math.ceil((double) recordsCount / (double) recordsPerPage);
	}

	/**
	 * Filters JSON attributes from the response based on the given view class.
	 * 
	 * @param response
	 *            - the response object to be filtered.
	 * @param viewClass
	 *            - the view to apply as a filter.
	 * @return a JSON string representation of the filtered response.
	 * @throws JsonProcessingException
	 */
	public static String filterResponseByView(Response response, @SuppressWarnings("rawtypes") Class viewClass)
			throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
		return mapper.writerWithView(viewClass).writeValueAsString(response);
	}

	/**
	 * Sorts users by the given order.
	 * 
	 * @param order
	 *            - possible values are ASC (ascending) or DESC (descending).
	 * @param users
	 *            - list of users to be sorted.
	 * @param collator
	 *            - to perform perform locale-sensitive string comparison.
	 */
	public static void sortUsers(Order order, List<UserDto> users, Collator collator) {
		switch (order) {
		case ASC:
			users.sort(new Comparator<UserDto>() {

				@Override
				public int compare(UserDto o1, UserDto o2) {
					return collator.compare(o1.getLastName(), o2.getLastName());
				}
			});
			break;
		case DESC:
			users.sort(new Comparator<UserDto>() {

				@Override
				public int compare(UserDto o1, UserDto o2) {
					return collator.compare(o2.getLastName(), o1.getLastName());
				}
			});
			break;
		}
	}

	/**
	 * Gets users for the given page index.
	 * 
	 * @param pageIndex
	 *            - index of the page for which to retrieve users.
	 * @param pageSize
	 *            - number of users to fit on a page.
	 * @param records
	 *            - list of all users.
	 * @return list of users for the given page.
	 */
	public static List<UserDto> getUsersForPage(int pageIndex, int pageSize, List<UserDto> records) {
		List<UserDto> results = new ArrayList<UserDto>();
		int i;
		if (pageIndex == 1) {
			i = 0;
		} else {
			i = ((pageIndex - 1) * pageSize) - 1;
		}
		for (; i < records.size() && results.size() < pageSize; i++) {
			results.add(records.get(i));
		}
		return results;
	}

	/**
	 * Creates a BAD REQUEST HTTP response.
	 * 
	 * @return BAD REQUEST HTTP response.
	 */
	public static ResponseEntity<String> createBadRequestResponse() {
		final HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<String>(httpHeaders, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Extract the enumeration that represents the given sorting order.
	 * 
	 * @param sortString
	 *            - string representing the sorting order. Valid values are
	 *            "type,asc" or "type,desc".
	 * @return order enumeration.
	 * @throws IllegalArgumentException
	 */
	public static Order extractOrderEnum(String sortString) throws IllegalArgumentException {
		String[] split = sortString.split(",");
		if (split.length != 2) {
			throw new IllegalArgumentException("Wrong sorting value received");
		}

		return Order.valueOf(split[1].toUpperCase());
	}

	/**
	 * Gets the user DTO by the given id.
	 * 
	 * @param id
	 *            - id of the user.
	 * @param users
	 *            - list of all users.
	 * @return DTO of the user or null if not found.
	 */
	public static UserDto getUserById(long id, List<UserDto> users) {
		for (UserDto userDto : users) {
			if (id == userDto.getId()) {
				return userDto;
			}
		}
		return null;
	}

	/**
	 * Removes the user by the given id.
	 * 
	 * @param id
	 *            - id of the user.
	 * @param users
	 *            - list of all users.
	 */
	public static void removeUserById(long id, List<UserDto> users) {
		for (UserDto userDto : users) {
			if (id == userDto.getId()) {
				users.remove(userDto);
				break;
			}
		}
	}

	/**
	 * Populates the user list with dummy data.
	 */
	public static void populateWithDummyData(List<UserDto> users) {
		List<CompanyDto> companies = new ArrayList<CompanyDto>();
		companies.add(new CompanyDto(1, "Codecacao", 1));
		companies.add(new CompanyDto(2, "Ericsson", 2));
		companies.add(new CompanyDto(3, "Google", 3));
		List<RoleDto> roles = new ArrayList<RoleDto>();
		roles.add(new RoleDto(1));
		roles.add(new RoleDto(2));
		roles.add(new RoleDto(3));
		AvatarDto avatarDto = new AvatarDto(1,
				"http://static.tvtropes.org/pmwiki/pub/images/Hello_Kitty_Pink_2981.jpg");
		AddressDto billingAddress = new AddressDto(1);
		users.add(new UserDto("Mr.", "Marko", "Marulić", "marko@marulic.com", "+385", "9823849032849", "passs4324",
				true, roles, avatarDto, companies, billingAddress));
		users.add(new UserDto("Mrs.", "Ivana", "Čošić", "marko@marulic.com", "+385", "9823849032849", "passs4324", true,
				roles, avatarDto, companies, billingAddress));
		users.add(new UserDto("Mr.", "Krešo", "Babaja", "marko@marulic.com", "+385", "9823849032849", "passs4324", true,
				roles, avatarDto, companies, billingAddress));
		users.add(new UserDto("Mrs.", "Maraike", "Lukin", "marko@marulic.com", "+385", "9823849032849", "passs4324",
				true, roles, avatarDto, companies, billingAddress));
		users.add(new UserDto("Mr.", "Stipe", "Silov", "marko@marulic.com", "+385", "9823849032849", "passs4324", true,
				roles, avatarDto, companies, billingAddress));
		users.add(new UserDto("Mrs.", "Ana", "Braica", "marko@marulic.com", "+385", "9823849032849", "passs4324", true,
				roles, avatarDto, companies, billingAddress));
		users.add(new UserDto("Mr.", "Toni", "Tomasović", "marko@marulic.com", "+385", "9823849032849", "passs4324",
				true, roles, avatarDto, companies, billingAddress));
		users.add(new UserDto("Mrs.", "Luce", "Mrmica", "marko@marulic.com", "+385", "9823849032849", "passs4324", true,
				roles, avatarDto, companies, billingAddress));
		users.add(new UserDto("Mr.", "Boris", "Pazdin", "marko@marulic.com", "+385", "9823849032849", "passs4324", true,
				roles, avatarDto, companies, billingAddress));
		users.add(new UserDto("Mrs.", "Dijana", "Krelin", "marko@marulic.com", "+385", "9823849032849", "passs4324",
				true, roles, avatarDto, companies, billingAddress));
	}
}
