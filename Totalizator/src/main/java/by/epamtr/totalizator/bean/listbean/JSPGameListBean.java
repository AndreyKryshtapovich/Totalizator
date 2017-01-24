package by.epamtr.totalizator.bean.listbean;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import by.epamtr.totalizator.bean.entity.GameCupoun;

/**
 * This is a java bean class designed to transfer a List of
 * {@link by.epamtr.totalizator.bean.entity.GameCupoun} objects to the jsp page.
 * 
 * @author Andrey Kryshtapovich
 *
 */
public class JSPGameListBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Iterator<GameCupoun> it;
	private List<GameCupoun> list;

	public JSPGameListBean() {
	}

	public JSPGameListBean(List<GameCupoun> list) {
		this.list = list;
	}

	public int getSize() {
		it = list.iterator();
		return list.size();
	}

	public GameCupoun getElement() {
		return (GameCupoun) it.next();
	}

}
