package searchOnInternet;

import java.util.ArrayList;
import java.util.List;
import com.jlu.redcueExample.Element;
import com.jlu.redcueExample.ElemwntList;
//����<String,String>(key,value)
//��value��ΪString�����ۼ�
//���ɽ���(ȷ��)
public class Example10 {

	public List<TwoTuple> getOutput() {
		return output;
	}
	public void setOutput(List<TwoTuple> output) {
		this.output = output;
	}
	
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v = "";
	
	String userId = "";
	String itermScore = "";
	public void reduce(ElemwntList list) {
		String key = (String)list.getList().get(0).getList().get(0);

        //map���������keyΪ1,valueΪ101:5.0
        String itermPers = "";
        for (Element value : list.getList()) {
            itermPers += "," + value.getList().get(1).toString();
        }
        v=(itermPers.replaceFirst(",", ""));
        output.add(new TwoTuple(key, v));
    
		    
	}


}
