package searchOnInternet;


import java.util.ArrayList;
import java.util.List;
import com.jlu.redcueExample.Element;
import com.jlu.redcueExample.ElemwntList;
//����<String,int>(key,value)     (����keyΪ����ID+��:��+����)
//���������Ӧ�ķ������ܷ�����ʹ����String�ļӷ�
//���ɽ���

public class Example09 {

	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v = "";
	
	String userId = "";
	String itermScore = "";
	public void reduce(ElemwntList list) {
		String key = (String)list.getList().get(0).getList().get(0);


		        Double totalScore = 0.0;
		        for (Element value : list.getList()) {
		            totalScore +=(Integer) value.getList().get(1);
		        }
		        String[] strArr = key.toString().split(":");
		        userId=(strArr[0]);
		        itermScore=(strArr[1] + ":" + totalScore);
		        output.add(new TwoTuple(userId, itermScore));
		    
	}


}
