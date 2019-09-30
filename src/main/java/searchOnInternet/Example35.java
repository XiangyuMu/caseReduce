package searchOnInternet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.jlu.redcueExample.Element;
import com.jlu.redcueExample.ElemwntList;
//https://github.com/josonle/MapReduce-Demo/blob/master/src/main/java/ssdut/training/mapreduce/peoplerank/PeopleRank2.java

//����<String,String>(key,value)
//���ֵΪ���Ӽ���
//�ݲ����������ʿɽ���
public class Example35 {
	
	public List<TwoTuple> getOutput() {
		return output;
	}
	public void setOutput(List<TwoTuple> output) {
		this.output = output;
	}
	
	public static enum Mycounter {
		my
	}
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	float gradesSum;
    public void reduce(ElemwntList list) throws IOException  {

    	String key = (String)list.getList().get(0).getList().get(0);

    	double sum = 0.0;
		People sourcePeople = null;
		for (Element v : list.getList()) {
			People people = People.fromMR(v.getList().get(1).toString());
			if (people.containsAttentionPeoples()) {		//���й�ע�˵���Դ�ṹ
				sourcePeople = people;
			} else {
				sum += people.getPeopleRank();				//�������ٽ��ڵ��PRֵ�ۼ�
			}
		}
		double newPR = (1 - 0.85) / 4 + (0.85 * sum);		//�����µ�PRֵ = (1-d)/N + d* sum��ÿ������ע��PRֵ/ÿ����ע����������  (����ϵ��ȡ0.85)
		//double d = newPR - sourcePeople.getPeopleRank();	//�����¾�PR��ֵ
		//int j = Math.abs( (int)(d*100.0) );					//ȡ������ֵ�Ŵ�100����ľ���ֵ
		//context.getCounter(Mycounter.my).increment(j);		//���������
		System.out.println(newPR);
		sourcePeople.setPeopleRank(newPR);					//����PRֵ			
		output.add(new TwoTuple(key, sourcePeople.toString()));	//�����ʽ��"userid  ��PRֵ  userlist"
    }
    public static class People {
    	private double peopleRank = 1.0;		//�洢 PRֵ����ֵĬ��1.0
    	private String[] attentionPeoples;		//��ע����
    	public static final char fieldSeparator = '\t';	//�ദʹ�÷ָ���\t������Ϊ����

    	public double getPeopleRank() {
    		return peopleRank;
    	}

    	public People setPeopleRank(double pageRank) {
    		System.out.println(pageRank);
    		this.peopleRank = pageRank;
    		return this;
    	}

    	public String[] getAttentionPeoples() {
    		return attentionPeoples;
    	}

    	public People setAttentionPeoples(String[] attentionPeoples) {
    		this.attentionPeoples = attentionPeoples;
    		return this;
    	}

    	//�ж��Ƿ������ע�û�
    	public boolean containsAttentionPeoples() {
    		return attentionPeoples != null && attentionPeoples.length > 0;
    	}

    	@Override
    	//People����ת���ַ���
    	public String toString() {
    		StringBuilder sb = new StringBuilder();
    		sb.append(peopleRank);
    		if (attentionPeoples != null) {
    			sb.append(fieldSeparator).append(StringUtils.join(attentionPeoples, fieldSeparator));
    		}
    		return sb.toString();	//����String��ʽ��"PeopleRandֵ	u1	u2..."
    	}
    	
    	//�ַ���ת��People����
    	public static People fromMR(String str) throws IOException {	//����String��ʽ��"PeopleRandֵ	u1	u2..."
    		People people = new People();
    		String[] strs = StringUtils.splitPreserveAllTokens(str, fieldSeparator);	//���ַ������ָ����ָ���ַ�������
    		people.setPeopleRank(Double.valueOf(strs[0]));	//�����һ��Ԫ��
    		if (strs.length > 1) {// ���ù�ע���ˣ���strs�±�Ϊ1��λ�ÿ�ʼ����Ϊ����������"1.0 b c d"���ַ�����
    			people.setAttentionPeoples(Arrays.copyOfRange(strs, 1, strs.length));	//��������Ԫ��
    		}
    		return people;	//����People����
    	}
    }
}
