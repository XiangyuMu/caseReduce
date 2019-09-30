package searchOnInternet;

import java.util.ArrayList;
import java.util.List;

import com.jlu.redcueExample.Element;
import com.jlu.redcueExample.ElemwntList;
//https://github.com/josonle/MapReduce-Demo/blob/master/src/main/java/gradesAverage/GradesAverage.java

//����<String,int>(key,value)
//���ֵΪƽ��ֵ
//�ɽ�����ȷ����
public class Example30 {
	
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

    	int sum = 0;
		int grades = 0;
		for (Element val : list.getList()) {
			sum += 1;   
			grades += Integer.parseInt((String) val.getList().get(1));
		}
		System.out.println("Reduce----student is:"+key.toString()+",grades is:"+grades+",sum is:"+sum);
		gradesSum=(float)grades/sum;
		output.add(new TwoTuple(key, Float.toString(gradesSum)));
    }

}
