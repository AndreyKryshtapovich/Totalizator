package by.epamtr.totalizator.dao.connectionpool;



import java.util.ResourceBundle;
public class DBResourseManager {

	private final static DBResourseManager instance = new DBResourseManager();
	private ResourceBundle bundle = ResourceBundle.getBundle("bd");
	
	public static DBResourseManager getInstance(){
		return instance;
		
	}
	
	public String getValue (String key){
		return bundle.getString(key);
	}
}
