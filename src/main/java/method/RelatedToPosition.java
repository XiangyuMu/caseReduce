package method;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.ModifierVisitor;
import com.github.javaparser.ast.visitor.Visitable;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import AnalysisProgress.functionInfo;

public class RelatedToPosition {
	
	
	public static void main(String[] args) throws Exception {
		String FILE_PATH = "src/main/java/searchOnInternet/";
		String Filename = "";
		System.out.println("�������ļ����ƣ�");
		Scanner scan = new Scanner(System.in);
		Filename = scan.next();
		CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH+Filename+".java"));
		
		List<String> ListValue = new ArrayList<String>();
		VoidVisitor<List<String>> md = new InputList();
		ListValue.add("list");
		md.visit(cu, ListValue);
		VoidVisitor<List<String>> md_1 = new InputList_1();
		md_1.visit(cu, ListValue);
		VoidVisitor<List<String>> md_2 = new InputList_2();
		md_2.visit(cu, ListValue);
		VoidVisitor<List<String>> md_3 = new InputList_3();
		md_3.visit(cu, ListValue);
		boolean flag = false;
		int num_1 ,num_2;
		while(!flag) {
			num_1 = ListValue.size();
			md.visit(cu, ListValue);
			md_1.visit(cu, ListValue);
			md_2.visit(cu, ListValue);
			md_3.visit(cu, ListValue);
			num_2 = ListValue.size();
			if(num_1==num_2) {
				flag=true;
			}
		}
		
		System.out.println(ListValue);
		
		ModifierVisitor<List<String>> checkProgramVisitor = new checkProgram();
		checkProgramVisitor.visit(cu, ListValue);
		
	}
	
	
	private static class InputList extends VoidVisitorAdapter<List<String>>{       //�ԵȺ��ж�list���
		@Override
		public void visit(AssignExpr ae,List<String> collector) {       
			List<String> list_key = new ArrayList<String>();
			List<String> list_value = new ArrayList<String>();
			super.visit(ae, collector);
			list_key.add(ae.getValue().toString());
			list_value.add(ae.getTarget().toString());
			for(int i=0;i<list_key.size();i++) {
				for(int j=0;j<collector.size();j++) {
					if(list_value.get(i).contains(collector.get(j))&&!collector.contains(list_key.get(i))) {
						collector.add(list_key.get(i));
					}
				}
			}
		}
	}
	
	private static class InputList_1 extends VoidVisitorAdapter<List<String>>{        //Ҳ���ԵȺ��ж�list��أ����жϵ��Ǳ�������ʱ��
		@Override
		public void visit(VariableDeclarator vd,List<String> collector) {
			List<String> list_1 = new ArrayList<String>();
			super.visit(vd, list_1);
//			System.out.println("ae:"+vd.toString());
			list_1.add(vd.toString());
			for(int i = 0;i<list_1.size();i++) {
				if(list_1.get(i).indexOf("=")!=-1) {
					String[] list = list_1.get(i).split("=");
					if(list.length==2) {
						for(int j = 0;j<collector.size();j++) {
							if(list[1].contains(collector.get(j))&&!collector.contains(list[0])) {
								collector.add(list[0]);
							}
						}
					}
				}
			}
		}
	}
	
	private static class InputList_2 extends VoidVisitorAdapter<List<String>>{      //��foreach���ж�list���
		@Override
		public void visit(ForEachStmt fes, List<String> collector) {
			// TODO Auto-generated method stub
			List<String> list_key = new ArrayList<String>();
			List<String> list_value = new ArrayList<String>();
			super.visit(fes, collector);
			list_key.add(fes.getVariable().getVariables().get(0).toString());
			list_value.add(fes.getIterable().toString());
			for(int i = 0;i<list_key.size();i++) {
				for(int j=0;j<collector.size();j++) {
					if(list_value.get(i).contains(collector.get(j))&&!collector.contains(list_key.get(i))) {
						collector.add(list_key.get(i));
					}
				}
			}
			
//			System.out.println("fes.getV"+fes.getIterable());
		}
	}
	
	private static class InputList_3 extends VoidVisitorAdapter<List<String>>{
		@Override
		public void visit(MethodCallExpr mce, List<String> collector) {
			// TODO Auto-generated method stub
			List<String> list_key = new ArrayList<String>();
			List<String> list_value = new ArrayList<String>();
			super.visit(mce,collector);
//			System.out.println("methodname:"+mce+" methodarg:"+mce.getArguments());   //�˴������⣬��Ҫ�ģ�
			list_key.add(mce.toString());
			list_value.add(mce.getArguments().toString());
			for(int i = 0;i<list_key.size();i++) {
				for(int j=0;j<collector.size();j++) {
					if(list_value.get(i).contains(collector.get(j))&&!collector.contains(list_key.get(i).split("\\.")[0])) {
						int num = list_key.get(i).indexOf(list_value.get(i));
//						System.out.println("list_key: "+list_key.get(i));
						String[] key = list_key.get(i).split("\\.");
//						System.out.println("key: "+key.length);
						collector.add(key[0]);
					}
				}
			}
			
		}
	}
	
	private static class checkProgram extends ModifierVisitor<List<String>>{
		@Override
		public ClassOrInterfaceDeclaration visit(ClassOrInterfaceDeclaration co, List<String> collector) {
			// TODO Auto-generated method stub
			super.visit(co, collector);
			System.out.println("collector:"+collector);
			List<functionInfo> lf = new ArrayList<functionInfo>();
			List<List <functionInfo>> llf = new ArrayList<List<functionInfo>>();
			checkType(co,collector,llf);
			writeInProgram(co,llf);
			return co;
		}
	}
	
	private static ClassOrInterfaceDeclaration checkType(ClassOrInterfaceDeclaration co,List<String> collector,List<List <functionInfo>> llf ) {
		MethodDeclaration reduceMd  = new MethodDeclaration();
		reduceMd = co.getMethodsByName("reduce").get(0);
		BlockStmt bs = reduceMd.getBody().get();
		List<Statement> ls = bs.getStatements();
		List<ForStmt> fl = new ArrayList<ForStmt>();
		List<ForEachStmt> fel = new ArrayList<ForEachStmt>();
		List<WhileStmt> wl = new ArrayList<WhileStmt>();
		List<Parameter>  lp = reduceMd.getParameters();
		Expression par ;
		for(int k = 0;k<lp.size();k++) {      //�ҵ���һ��ѭ������ʽΪlist�ı�������
			if(lp.get(k).getTypeAsString().indexOf("ElemwntList")!=-1) {
				//par = lp.get(k).getNameAsString();
				par = lp.get(k).getNameAsExpression();
//				System.out.println(par);
			}else {
				System.out.println("����reduce����û�����룡");
			}
		}
		
		//System.out.println(ls);
		
		for(int i = 0;i<ls.size();i++) {           //�ҵ�reduce�����е�while��for��foreachѭ��������wl��fl��fel�С�
			if(ls.get(i).isForStmt()) {
				fl.add(ls.get(i).asForStmt());
			}else if(ls.get(i).isForEachStmt()) {
				fel.add(ls.get(i).asForEachStmt());
			}else if(ls.get(i).isWhileStmt()) {
				wl.add(ls.get(i).asWhileStmt());
			}
		}
		List<functionInfo> lf;
		if(fl.size()!=0) {
			for(int flNum = 0;flNum<fl.size();flNum++) {
				List<Statement> lstmt;
				System.out.println("for");
				lstmt = fl.get(flNum).getBody().asBlockStmt().getStatements();
				lf = extractInfo(lstmt,collector);
//				if(lf.size()!=0) {
//					llf.add(lf);
//				}
				llf.add(lf);
				
			}
		}
		if(fel.size()!=0) {
			for(int felNum = 0;felNum<fel.size();felNum++) {
				List<Statement> lstmt;
				System.out.println("foreach");
				//System.out.println(fel.get(felNum).getBody().asBlockStmt().getStatements());
				lstmt = fel.get(felNum).getBody().asBlockStmt().getStatements();
				lf = extractInfo(lstmt,collector);
//				if(lf.size()!=0) {
//					llf.add(lf);
//				}
				llf.add(lf);
			}
		}
		if(wl.size()!=0) {
			for(int wlNum = 0;wlNum<wl.size();wlNum++) {
				List<Statement> lstmt;
				System.out.println("while");
				lstmt = wl.get(wlNum).getBody().asBlockStmt().getStatements();
				lf = extractInfo(lstmt,collector);
//				if(lf.size()!=0) {
//					llf.add(lf);
//				}
			
				llf.add(lf);
			}
		}
//		System.out.println(reduceMd);
		System.out.println("llf: "+llf);
		return co;
	}
	
	
	private static List<functionInfo> extractInfo(List<Statement> ls,List<String> collector) {
		List<functionInfo> lf = new ArrayList<functionInfo>();
		IfStmt is;
		for(int i = 0;i<ls.size();i++) {
			if(ls.get(i).isIfStmt()) {
				is = ls.get(i).asIfStmt();
				Expression ifcondition ;
				ifcondition = is.getCondition();
				boolean isrelatedtoValue = false;
				for(int k = 0;k<collector.size();k++) {    //ȷ���ж��������Ƿ���������������ֵ,��ȷ���Ƿ���ֵ�й�
					if(ifcondition.toString().indexOf(collector.get(k))!=-1) {
						//System.out.println("���е��ˣ�");
						//System.out.println("ifcondition: "+ifcondition);
						functionInfo fi = new functionInfo();
						fi.setIsRelationToValue(true);
						fi.getValueList().add(collector.get(k));
						lf.add(fi);
						isrelatedtoValue = true;
						//System.out.println(fi);
					}
				}
				if(!isrelatedtoValue) {
					System.out.println("��ֵ�޹�");
					functionInfo fi = new functionInfo();
					fi.setIsRelationToPosition(true);
					fi.getPositionList().add("position_num");
					lf.add(fi);
				}
				System.out.println("��һ��lf:"+lf.toString());
//				
//				if(ifcondition.toString().indexOf("list")!=-1) {
//					functionInfo fi = new functionInfo();
//					fi.setIsRelationToValue(true);
//					fi.getValueList().add("list");
//				}
			}
			System.out.println("�ڶ���lf:"+lf.toString());
		}
		System.out.println("������lf:"+lf.toString());
		return lf;
	}
	
	private static ClassOrInterfaceDeclaration writeInProgram(ClassOrInterfaceDeclaration co,List<List <functionInfo>> llf) {
		MethodDeclaration reduceMd  = new MethodDeclaration();
		reduceMd = co.getMethodsByName("reduce").get(0);
		BlockStmt bs = reduceMd.getBody().get();
		List<Statement> ls = bs.getStatements();
		List<ForStmt> fl = new ArrayList<ForStmt>();
		List<ForEachStmt> fel = new ArrayList<ForEachStmt>();
		List<WhileStmt> wl = new ArrayList<WhileStmt>();
		List<Parameter>  lp = reduceMd.getParameters();
		for(int i = 0;i<llf.size();i++) {
			List<functionInfo> lf = llf.get(i);
			for(int k = 0;k<lf.size();k++) {
				if(lf.get(k).getIsRelationToPosition()==true) {
					
				}else {
					
				}
			}
		}
		
		
		return co;
		
	}
	
	
	private void addInfoRelatedToPosition(functionInfo f) {
		
	}
}
