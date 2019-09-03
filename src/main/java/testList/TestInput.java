package testList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.jlu.redcueExample.Element;
import com.jlu.redcueExample.ElemwntList;
//read the testcase file into the Elemwnlist.
public class TestInput {
	public ElemwntList ellist = new ElemwntList();
	public String path;
	public ElemwntList getEl() {
		return ellist;
	}
	public void setEl(ElemwntList el) {
		this.ellist = el;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public ElemwntList createTestCase_Single(int CaseNum,String type1,String type2) {
		String filepath = path+Integer.toString(CaseNum)+".txt";
		File file = new File(filepath);  
		BufferedReader  reader = null;  
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
		//	int count = 0;
			while ((tempString = reader.readLine()) != null) {
				String str[] = tempString.split("# ");
				System.out.println("str "+str.length);
				Element el = new Element();
				for(int i = 0;i<str.length;i++) {
					el.getList().add(str[i]);
				}
				ellist.getList().add(el);
//				count = count + 1;
//				System.out.println(count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ellist;
	}
	
	public List<ElemwntList> createTestCase_multi(String filepath,String type1,String type2) {
		File file = new File(filepath);  
		BufferedReader  reader = null; 
		List<ElemwntList> lel = new ArrayList<ElemwntList>();
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int list_num = 0;
			while ((tempString = reader.readLine()) != null) {
				if(tempString.startsWith("$")) {
					lel.add(new ElemwntList());
					list_num = list_num + 1;
				}else {
					String str[] = tempString.split("# ");
//					System.out.println("str "+str.length);
					Element el = new Element();
					for(int i = 0;i<str.length;i++) {
						el.getList().add(str[i]);
					}
					lel.get(list_num-1).getList().add(el);
				}
				
//				while(!tempString.startsWith("$")) {
//					String str[] = tempString.split("# ");
//					System.out.println("str "+str.length);
//					Element el = new Element();
//					for(int i = 0;i<str.length;i++) {
//						el.getList().add(str[i]);
//					}
//					ellist.getList().add(el);
//				}
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lel;
	}
	
	public static void main(String[] args) {
		List<ElemwntList> lel = new TestInput().createTestCase_multi("case5mix.txt", "String", "String");
		System.out.println("����Ϊ��"+lel.size());
		System.out.println("��������鿴�����");
		Scanner scan = new Scanner(System.in);
		int i = scan.nextInt();
		ElemwntList el= lel.get(i);
		for(int k=0;k<el.getList().size();k++) {
			System.out.println(el.getList().get(k).getList().toString());
		}
	}

}
