package by.epamtr.totalizator.dao.connectionpool;

import java.util.ResourceBundle;

/**
 * This class is designed to access the bundle file with information
 * required for connecting to the data storage.
 * 
 * @author Andrey Kryshtapovich
 *
 */
public class DBResourseManager {

	private final static DBResourseManager instance = new DBResourseManager();
	private ResourceBundle bundle = ResourceBundle.getBundle("bd");

	public static DBResourseManager getInstance() {
		return instance;

	}

	public String getValue(String key) {
		return bundle.getString(key);
	}
}
