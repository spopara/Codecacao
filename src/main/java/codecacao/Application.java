package codecacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application entry point.
 * 
 * @author Srdan Popara
 *
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		// set application port
		System.getProperties().put("server.port", Configuration.INSTANCE.getPort());

		// populate with dummy data
		UsersUtil.populateWithDummyData(UsersControllerAbstract.users);

		// run application
		SpringApplication.run(Application.class, args);

	}

}