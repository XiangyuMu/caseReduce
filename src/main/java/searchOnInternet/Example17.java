package searchOnInternet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import com.jlu.redcueExample.ElemwntList;
//����<String,String>(key,value)
//���set��value���ļ���
//�ɽ�����setΪ��˳��
public class Example17 {
	
	public List<TwoTuple> getOutput() {
		return output;
	}
	public void setOutput(List<TwoTuple> output) {
		this.output = output;
	}
	
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v ="";
    public void reduce(ElemwntList list)  {

    	String key = (String)list.getList().get(0).getList().get(0);
    	Set<String> attackers = new TreeSet<String>();
    	int i = 0;
        while (i<list.getList().size()) {
          String valStr = list.getList().get(i).getList().get(1).toString();
          i = i+1;
          attackers.add(valStr);
        }
        output.add(new TwoTuple(key, attackers.toString()));
    }
}
