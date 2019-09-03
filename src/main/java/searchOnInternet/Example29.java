package searchOnInternet;

import java.util.ArrayList;
import java.util.List;

import com.jlu.redcueExample.Element;
import com.jlu.redcueExample.ElemwntList;
//https://github.com/josonle/MapReduce-Demo/blob/master/src/main/java/InvertedIndex/InvertedReducer.java

//����<String,String>(key,value)
//���ֵΪ�ۼӣ�����ʽΪString
//���ɽ���
public class Example29 {
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v ="";
	
    public void reduce(ElemwntList list)  {

    	String key = (String)list.getList().get(0).getList().get(0);

    	String fileList = new String();
		for (Element value : list.getList()) {
			fileList += value.getList().get(1).toString() + "; ";
		}
		
		output.add(new TwoTuple(key, fileList));		//�����ʽ��"word	file1:num1; file2:num2;"
    }

}
