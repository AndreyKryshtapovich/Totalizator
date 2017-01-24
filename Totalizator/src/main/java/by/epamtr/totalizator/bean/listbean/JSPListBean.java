package by.epamtr.totalizator.bean.listbean;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import by.epamtr.totalizator.bean.entity.Event;

/**
 * This is a java bean class designed to transfer a List of
 * {@link by.epamtr.totalizator.bean.entity.Event} objects to the jsp page.
 * 
 * @author Andrey Kryshtapovich
 *
 */
public class JSPListBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Iterator<Event> it;
	private List<Event> list;

	public JSPListBean() {
	}

	public JSPListBean(List<Event> list) {
		this.list = list;
	}

	public int getSize() {
		it = list.iterator();
		return list.size();
	}

	public Event getElement() {
		return (Event) it.next();
	}

	public Iterator<Event> getIt() {
		return it;
	}

	public void setIt(Iterator<Event> it) {
		this.it = it;
	}

	public List<Event> getList() {
		return list;
	}

	public void setList(List<Event> list) {
		this.list = list;
	}

}
