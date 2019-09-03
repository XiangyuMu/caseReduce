package searchOnInternet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.jlu.redcueExample.Element;
import com.jlu.redcueExample.ElemwntList;
//����<String,String>(key,value)       (value�ĸ�ʽ�ǡ�Strng,Double��)
//���Ϊ<key,value>��
//���ɽ�������ֵ��˳���䣩
public class Example11 {

	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v = "";
	
	public void reduce(ElemwntList list) {
		String key = (String)list.getList().get(0).getList().get(0);



        Map<String, Double> result = new HashMap<String, Double>();
        for (Element value : list.getList()) {
            String[] str = value.getList().get(1).toString().split(",");
            if (result.containsKey(str[0])) {
                result.put(str[0], result.get(str[0]) + Double.parseDouble(str[1]));
            } else {
                result.put(str[0], Double.parseDouble(str[1]));
            }
        }

        for (String itemID : result.keySet()) {
            double score = result.get(itemID);
            v=(itemID + "," + score);
            output.add(new TwoTuple(key, v));
        }
    
    
		    
	}


}
