package by.epamtr.totalizator.bean.listbean;

import java.io.Serializable;
import java.util.Map;

public class JSPMapBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Map<String,String> map;
	
	public JSPMapBean() {
	}
	
	public JSPMapBean(Map<String,String> map) {
		this.map = map;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	
	public void put(String key, String value){
		map.put(key, value);
	}
	public String get(String key){
		return map.get(key);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}
