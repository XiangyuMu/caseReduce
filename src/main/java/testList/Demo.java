package testList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jlu.redcueExample.ElemwntList;

import searchOnInternet.Example05;
import searchOnInternet.Example13;
import searchOnInternet.TwoTuple;

public class Demo {
//	public static void main(String[] args) throws IOException {
//		TestInput ti = new TestInput();
//		ti.setPath("case");
//		ElemwntList el = ti.createTestCase_Single(3, "String", "String");
//		//System.out.println(el.toString());
//		createNewCase cnc = new createNewCase();
//		cnc.setElementList(el);
//		cnc.createAllCases();
//		for(int k = 0;k<cnc.getElementListList().size();k++) {
//			System.out.println(cnc.getElementListList().get(k).toString());
//		}
//		System.out.println("个数为："+cnc.getElementListList().size());
//		 createDir cd = new createDir();
//		 cd.createFileDir(new File("case2.txt"));
//		 cd.createRelatedFile(cnc.getElementListList());
//	}
	
	
	public static List<ElemwntList> list1= new ArrayList<ElemwntList>();
	public List<ElemwntList> list2 = new  ArrayList<ElemwntList>();
	public static List<TwoTuple> tt1 ;
	public static List<TwoTuple> tt2 ;
	
	public boolean isEqual(List<TwoTuple> tt1,List<TwoTuple> tt2) {

		boolean flag = true;
		if(tt1.size() != tt2.size()) {
			System.out.println("输出个数不同！");
			flag = false;
			return flag;
		}else {
			for(int i = 0;i<tt1.size();i++) {
				if(!tt2.get(i).equal(tt1.get(i))) {
					flag = false;
					System.out.println("结果不同！");
					return flag;
				}
			}
			return flag;
		}
	
	}
	
	public static void main(String[] args) {
		TestInput ti = new TestInput();
		list1 = ti.createTestCase_multi("case13mix.txt", "String", "String");
		Example13 e5 ;
		e5 = new Example13();
		e5.reduce(list1.get(0));
		tt1 = e5.getOutput();
		System.out.println("第0个："+tt1.toString());
		for(int i = 1;i<list1.size();i++) {
			e5 = new Example13();
			e5.reduce(list1.get(0));
			tt2 = e5.getOutput();
			System.out.println("第"+i+"个："+tt1.toString());
			if(!new Demo().isEqual(tt1, tt2)) {
				System.out.println("不可交换！");
				break;
			}
		}
		System.out.println("可交换！");
	}

}
