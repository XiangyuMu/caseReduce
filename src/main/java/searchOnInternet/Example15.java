package searchOnInternet;

import java.util.ArrayList;
import java.util.List;
import com.jlu.redcueExample.Element;
import com.jlu.redcueExample.ElemwntList;
//����<String,String>(key,value)
//����������еĸ��������value�еĵ�һ��Ϊ���������еĵ�һ��value��
//���ɽ�����ȷ�ϣ�
public class Example15 {
	
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
    	String recordKey = key.toString();
        String authorId = recordKey.substring(0);

        // we know that the first element will the the user reputation
        // because keys are sorted before arriving to the reducer
        // so we get it
        
        //System.out.println(list.getList().iterator().next().getList().get(1));
        Integer reputation = Integer.parseInt((String) list.getList().iterator().next().getList().get(1)) ;

        int postsNumber = 0;
        for (Element value : list.getList()) {
            postsNumber++;
        }
        v = (reputation + "\t" + postsNumber);
        output.add(new TwoTuple(authorId,v ));
    }
}
