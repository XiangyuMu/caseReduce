package searchOnInternet;

import java.util.ArrayList;
import java.util.List;
import com.jlu.redcueExample.Element;
import com.jlu.redcueExample.ElemwntList;
//https://github.com/josonle/MapReduce-Demo/blob/master/src/main/java/InputOutputFormatTest/MultiInOutput.java

//����<String,int>(key,value)
//���ֵΪ�ۼӣ��ٸ���keyֵ���зֱ����
//�ɽ���
public class Example28 {
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v ="";
	
    public void reduce(ElemwntList list)  {

    	String key = (String)list.getList().get(0).getList().get(0);

    	int sum = 0;
		for (Element value : list.getList()) {
			sum += (Integer)value.getList().get(1);
		}
		// ʹ��MultiOutputs�������Context�������
		// 1. �������ͬ�ļ�����ʽ���ļ�����
		if (key.toString().startsWith("2015"))
			output.add(new TwoTuple( key, "f2015 "+sum));
		else if (key.toString().startsWith("2016"))
			output.add(new TwoTuple( key, "f2016 "+sum));
		else
			output.add(new TwoTuple( key, "f2017 "+sum));
    }

}
