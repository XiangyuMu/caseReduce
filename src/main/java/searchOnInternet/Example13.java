package searchOnInternet;

import java.util.ArrayList;
import java.util.List;
import com.jlu.redcueExample.Element;
import com.jlu.redcueExample.ElemwntList;
//����<int,int>(key,value)
//���Ϊ��Сֵ
//�ɽ���
public class Example13 {

	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	
	
	
	public List<TwoTuple> getOutput() {
		return output;
	}
	public void setOutput(List<TwoTuple> output) {
		this.output = output;
	}
	int v =0;
	public void reduce(ElemwntList list) {
		String key = (String)list.getList().get(0).getList().get(0);

        int max = 0;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        int count = 0;
        for (Element value : list.getList()) {
        	//System.out.println(value.getList().get(1));
            if (max < Integer.parseInt((String) value.getList().get(1))) {
                max = Integer.parseInt((String) value.getList().get(1));
            }
            if (min > Integer.parseInt((String) value.getList().get(1))) {
                min = Integer.parseInt((String) value.getList().get(1));
            }
            sum += Integer.parseInt((String) value.getList().get(1));
            count++;
        }
        v=sum / count;
        v=max;
        v=min;
        output.add(new TwoTuple(key, v+""));
    
		    
	}


}
