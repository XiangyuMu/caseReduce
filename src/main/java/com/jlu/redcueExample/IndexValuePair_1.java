package com.jlu.redcueExample;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class IndexValuePair_1 {
	ElemwntList gl = new ElemwntList();

	
	public void reduce(ElemwntList list) {
		Object x = null;
		Object y = null;
		Element el = new Element();
		Map mp = new HashMap();
		for(int i = 0;i<list.getList().size();i++) {
			el = list.getList().get(i);
			x = el.getList().get(1);
			y = el.getList().get(2);
			mp.put(x, y);
		}
		doSomething(mp);
		
	}
	
	public void doSomething(Map mp) {
		
	}
}
