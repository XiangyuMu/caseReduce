package searchOnInternet;

import java.util.ArrayList;
import java.util.List;
import com.jlu.redcueExample.ElemwntList;
//����<String,String>(key,value)
//���valueΪ�յļ�ֵ��
//���ɽ���
public class Example20 {
	
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

    	int i = 0;
    	while (i<list.getList().size()) {
            String inputDoc = (String) list.getList().get(i).getList().get(1);
            System.out.println(inputDoc);
            boolean keep = inputDoc.isEmpty();
            i++;
            if (!keep) {
                String incrCounter="BehemothReducer";
                continue;
            }
            output.add(new TwoTuple(key, inputDoc));
    	}
    }
}
