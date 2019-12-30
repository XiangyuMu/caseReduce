package AnalysisProgress;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.jlu.redcueExample.ElemwntList;

public class CreateReducedCases {           //根据信息生成测试用例
	
	private List<ElemwntList> list = new ArrayList<ElemwntList>() ;
	
	public static void main(String[] args) {
		int type;
		String filename = "";
		List<List<Integer>> position_list = new ArrayList<List<Integer>>();
		List<List<String>> value_list = new ArrayList<List<String>>();
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入测试用例文件名称：");
		filename = scan.next();
		while(true) {
			System.out.println("请输入程序类型(1表示与位置相关，2表示与值相关):");
			type = scan.nextInt();
			while(type!=1&&type!=2) {
				System.out.println("输入错误，请重新输入！！");
				type = scan.nextInt();
			}
			if(type == 1) {
				List<Integer> position = new ArrayList<Integer>(); 
				int position_num;
				System.out.println("请输入位置信息(输入100结束)：");
				while(true) {
					position_num = scan.nextInt();
					if(position_num==100) {
						System.out.println("此次输入结束！");
						break;
					}else {
						position.add(position_num);
					}
					
				}
				position_list.add(position);
			}else if(type == 2) {
				List<String> value = new ArrayList<String>(); 
				String value_num;
				System.out.println("请输入位置信息(输入done结束)：");
				while(true) {
					value_num = scan.next();
					if(value_num=="done") {
						System.out.println("此次输入结束！");
						break;
					}else {
						value.add(value_num);
					}					
				}
				value_list.add(value);
			}
			System.out.println("是否继续输入？（y/n）");
			if(scan.next()=="n") {
				break;
			}
		}
		
		
		
		
		
	}
	
	

}
