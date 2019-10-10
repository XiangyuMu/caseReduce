package AnalysisProgress;

import java.util.ArrayList;
import java.util.List;

public class functionInfo {
	private Boolean isRelationToPosition;
	private Boolean isRelationToValue;
	
	private List<Integer> positionList = new ArrayList<Integer>();
	private List<Object> valueList = new ArrayList<Object>();
	public Boolean getIsRelationToPosition() {
		return isRelationToPosition;
	}
	public void setIsRelationToPosition(Boolean isRelationToPosition) {
		this.isRelationToPosition = isRelationToPosition;
	}
	public Boolean getIsRelationToValue() {
		return isRelationToValue;
	}
	public void setIsRelationToValue(Boolean isRelationToValue) {
		this.isRelationToValue = isRelationToValue;
	}
	public List<Integer> getPositionList() {
		return positionList;
	}
	public void setPositionList(List<Integer> positionList) {
		this.positionList = positionList;
	}
	public List<Object> getValueList() {
		return valueList;
	}
	public void setValueList(List<Object> valueList) {
		this.valueList = valueList;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = "";
		if(isRelationToPosition&&!isRelationToValue) {
			str = "��λ���йأ���ֵ�޹�!   �Ҵ�ʱֵΪ: "+positionList.toString();
		}else if(!isRelationToPosition&&isRelationToValue) {
			str = "��λ���޹أ���ֵ�й�!   �Ҵ�ʱֵΪ: "+valueList.toString();
		}else if(isRelationToPosition&&isRelationToValue) {
			str = "��ֵ��λ�ö��йأ��Ҵ�ʱλ��Ϊ��"+positionList.toString()+" ֵΪ: "+valueList.toString();
		}else {
			str = "��λ�ú�ֵ���޹أ�";
		}
		return str;
	}
	

}
