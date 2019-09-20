package searchOnInternet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.jlu.redcueExample.Element;
import com.jlu.redcueExample.ElemwntList;
//输入<String,String>(key,value)       (value的格式是“Strng,Double”)
//输出为<key,value>对
//可交换（set无顺序）（确定）
public class Example11 {

	public List<TwoTuple> getOutput() {
		return output;
	}
	public void setOutput(List<TwoTuple> output) {
		this.output = output;
	}
	
	
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
            	System.out.println(str[1]);
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
