package searchOnInternet;

import java.util.ArrayList;
import java.util.List;

import com.jlu.redcueExample.Element;
import com.jlu.redcueExample.ElemwntList;
//https://github.com/josonle/MapReduce-Demo/blob/master/src/main/java/mutualFriend/DecomposeFriendsReducer.java

//输入<String,String>(key,value)
//输出值value的累加String
//不可交换
public class Example33 {
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v ="";
	float gradesSum;
    public void reduce(ElemwntList list)  {

    	String key = (String)list.getList().get(0).getList().get(0);

    	String friendList = "";
		for (Element value : list.getList()) {
			friendList += value.getList().get(1).toString()+",";
		}
		// 输出个人所有好友，A	I,K,C,B,G,F,H,O,D
		output.add(new TwoTuple(key, friendList.substring(0,friendList.length()-1)));
    }

}
