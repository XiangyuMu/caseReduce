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
			str = "与位置有关，与值无关!   且此时值为: "+positionList.toString();
		}else if(!isRelationToPosition&&isRelationToValue) {
			str = "与位置无关，与值有关!   且此时值为: "+valueList.toString();
		}else if(isRelationToPosition&&isRelationToValue) {
			str = "与值和位置都有关！且此时位置为："+positionList.toString()+" 值为: "+valueList.toString();
		}else {
			str = "与位置和值都无关！";
		}
		return str;
	}
	

}
