package searchOnInternet;

import java.util.ArrayList;
import java.util.List;

import com.jlu.redcueExample.Element;
import com.jlu.redcueExample.ElemwntList;
//https://github.com/josonle/MapReduce-Demo/blob/master/src/main/java/mapreduceProgram/DateSortAsc.java

//输入<String,String>(key,value)
//输出值为key与value的互换
//不可交换
public class Example31 {
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v ="";
	float gradesSum;
    public void reduce(ElemwntList list)  {

    	String key = (String)list.getList().get(0).getList().get(0);

    	for (Element value : list.getList()) {
			// 排序后再次颠倒k-v，将日期作为key
			System.out.println(value.getList().get(1).toString()+":"+key);
			output.add(new TwoTuple(value.getList().get(1).toString(), key));
		}
    }

}
