package searchOnInternet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.jlu.redcueExample.Element;
import com.jlu.redcueExample.ElemwntList;
//����<String,String>(key,value)        (value�и�ʽΪint-int�����е�һ��intΪ0��1����ֻ��һ��Ϊ0)
//��int-int����ǰһ��intΪ0����ڶ���intΪkey���γɶ����ֵ�ԡ�
//���ɽ���
public class Example12 {

	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v = "";
	public void reduce(ElemwntList list) {
		String key = (String)list.getList().get(0).getList().get(0);

        String customerName = "";
        Set<Integer> orders = new HashSet<Integer>();
        //��ȡcustomer��order�����Ϣ
        //�������ʹ�ø�Ϊ���ķ�ʽ,��mapʱʹ�ö����͵�value������,�����ص�����map��reduce�м����ӵ�key����
        for (Element value : list.getList()) {
            if (value.getList().get(1).toString().startsWith("0")) {
                customerName = value.toString().split("-")[1];
            }
            if (value.getList().get(1).toString().startsWith("1")) {
                orders.add(Integer.parseInt(value.toString().split("-")[1]));
            }
        }
        key = (customerName);
        //left join �����CustomerΪ����
        for (Integer order : orders) {
            v=(order).toString();
            output.add(new TwoTuple(key, v));
        }
    
    
    
		    
	}


}
