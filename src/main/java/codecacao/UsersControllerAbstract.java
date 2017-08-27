package codecacao;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import resources.UserDto;

/**
 * This is an abstract class for the UserController classes.
 * 
 * @author Srdan Popara
 */
public class UsersControllerAbstract {
	/**
	 * The list of all users.
	 */
	static List<UserDto> users = Collections.synchronizedList(new ArrayList<UserDto>());
	/**
	 * The collator object used to for user sorting.
	 */
	static Collator collator;

	public UsersControllerAbstract() {
		collator = Collator.getInstance(Locale.forLanguageTag("hr_HR"));
		collator.setStrength(Collator.PRIMARY);
	}

}