package codecacao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class reads configuration settings from the properties class.
 * 
 * @author Srdan Popara
 *
 */
public enum Configuration {
	INSTANCE;

	private String dbHost;
	private String dbUser;
	private String dbPassword;
	private int dbPort;
	private int port;

	private static final String configFileName = "config.properties";
	public static final int DEFAULT_DB_PORT = 2017;
	public static final int DEFAULT_APPLICATION_PORT = 8080;

	private Configuration() {
		readConfiguration();
	};

	private void readConfiguration() {
		Properties prop = new Properties();
		InputStream input = null;

		try {
			prop.load(getClass().getClassLoader().getResourceAsStream(configFileName));
			dbHost = prop.getProperty("dbHost");
			dbUser = prop.getProperty("dbUser");
			dbPassword = prop.getProperty("dbPassword");
			dbPort = getIntegerValue(prop, "dbPort", DEFAULT_DB_PORT);
			port = getIntegerValue(prop, "port", DEFAULT_APPLICATION_PORT);

			System.out.println("********** CONFIGURATION **********");
			System.out.println("DB host: " + dbHost);
			System.out.println("DB user: " + dbUser);
			System.out.println("DB password: " + dbPassword);
			System.out.println("DB port: " + dbPort);
			System.out.println("Server port: " + port);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Gets the integer value from the properties file or returns the default
	 * value.
	 * 
	 * @param prop
	 *            - the properties file.
	 * @param key
	 *            - the property key.
	 * @param defaultValue
	 *            - the default value for the given property.
	 * @return the integer value of the property or the default value if the
	 *         property couldn't be found.
	 */
	private int getIntegerValue(Properties prop, String key, int defaultValue) {
		String tempPort = prop.getProperty(key);
		if (tempPort != null) {
			try {
				return Integer.parseInt(tempPort);
			} catch (NumberFormatException e) {
				return defaultValue;
			}
		} else {
			return defaultValue;
		}
	}

	public String getDbHost() {
		return dbHost;
	}

	public String getDbUser() {
		return dbUser;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public int getDbPort() {
		return dbPort;
	}

	public int getPort() {
		return port;
	}
}
