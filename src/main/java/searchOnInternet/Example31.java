package searchOnInternet;

import java.util.ArrayList;
import java.util.List;

import com.jlu.redcueExample.Element;
import com.jlu.redcueExample.ElemwntList;
//https://github.com/josonle/MapReduce-Demo/blob/master/src/main/java/mapreduceProgram/DateSortAsc.java

//����<String,String>(key,value)
//���ֵΪkey��value�Ļ���
//���ɽ�����ȷ����
public class Example31 {
	
	public List<TwoTuple> getOutput() {
		return output;
	}
	public void setOutput(List<TwoTuple> output) {
		this.output = output;
	}
	
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v ="";
	float gradesSum;
    public void reduce(ElemwntList list)  {

    	String key = (String)list.getList().get(0).getList().get(0);

    	for (Element value : list.getList()) {
			// ������ٴεߵ�k-v����������Ϊkey
			System.out.println(value.getList().get(1).toString()+":"+key);
			output.add(new TwoTuple(value.getList().get(1).toString(), key));
		}
    }

}
