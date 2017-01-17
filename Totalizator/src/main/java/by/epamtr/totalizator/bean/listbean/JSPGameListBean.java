package by.epamtr.totalizator.bean.listbean;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import by.epamtr.totalizator.bean.entity.GameCupoun;

public class JSPGameListBean  implements Serializable{
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
