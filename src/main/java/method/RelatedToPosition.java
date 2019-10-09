package method;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.Expression;
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
		System.out.println("请输入文件名称：");
		Scanner scan = new Scanner(System.in);
		Filename = scan.next();
		CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH+Filename+".java"));
		
		List<String> ListValue = new ArrayList<String>();
		List<String> ListValue_1 = new ArrayList<String>();
		VoidVisitor<List<String>> md = new InputList();
		md.visit(cu, ListValue);
		VoidVisitor<List<String>> md_1 = new InputList_1();
		md_1.visit(cu, ListValue_1);
		System.out.println(ListValue);
		
		ModifierVisitor<?> checkProgramVisitor = new checkProgram();
		checkProgramVisitor.visit(cu, null);
		
	}
	
	
	private static class InputList extends VoidVisitorAdapter<List<String>>{
		@Override
		public void visit(AssignExpr ae,List<String> collector) {
			super.visit(ae, collector);
			collector.add(ae.getValue().toString());
		}
	}
	
	private static class InputList_1 extends VoidVisitorAdapter<List<String>>{
		@Override
		public void visit(VariableDeclarator ae,List<String> collector) {
			List<String> list_1 = new ArrayList<String>();
			super.visit(ae, list_1);
			list_1.add(ae.toString());
			for(int i = 0;i<list_1.size();i++) {
				if(list_1.get(i).indexOf("=")!=-1) {
					String[] list = list_1.get(i).split("=");
					if(list.length==2) {
						for(String l:collector) {
							if(list[1].contains(l)) {
								collector.add(list[0]);
							}
						}
					}
				}
			}
		}
	}
	
	private static class checkProgram extends ModifierVisitor<Void>{
		@Override
		public ClassOrInterfaceDeclaration visit(ClassOrInterfaceDeclaration co, Void arg) {
			// TODO Auto-generated method stub
			super.visit(co, arg);
			checkType(co);
			return co;
		}
	}
	
	private static ClassOrInterfaceDeclaration checkType(ClassOrInterfaceDeclaration co) {
		MethodDeclaration reduceMd  = new MethodDeclaration();
		reduceMd = co.getMethodsByName("reduce").get(0);
		BlockStmt bs = reduceMd.getBody().get();
		List<Statement> ls = bs.getStatements();
		List<ForStmt> fl = new ArrayList<ForStmt>();
		List<ForEachStmt> fel = new ArrayList<ForEachStmt>();
		List<WhileStmt> wl = new ArrayList<WhileStmt>();
		List<Parameter>  lp = reduceMd.getParameters();
		Expression par ;
		for(int k = 0;k<lp.size();k++) {      //找到第一个循环的形式为list的变量名称
			if(lp.get(k).getTypeAsString().indexOf("ElemwntList")!=-1) {
				//par = lp.get(k).getNameAsString();
				par = lp.get(k).getNameAsExpression();
				System.out.println(par);
			}else {
				System.out.println("错误！reduce函数没有输入！");
			}
		}
		
		System.out.println(ls);
		
		for(int i = 0;i<ls.size();i++) {           //找到reduce函数中的while、for和foreach循环，放入wl、fl和fel中。
			if(ls.get(i).isForStmt()) {
				fl.add(ls.get(i).asForStmt());
			}else if(ls.get(i).isForEachStmt()) {
				fel.add(ls.get(i).asForEachStmt());
			}else if(ls.get(i).isWhileStmt()) {
				wl.add(ls.get(i).asWhileStmt());
			}
		}
		
		if(fl.size()!=0) {
			for(int flNum = 0;flNum<fl.size();flNum++) {
				List<Statement> lstmt;
				System.out.println("for");
				lstmt = fl.get(flNum).getBody().asBlockStmt().getStatements();
				extractInfo(lstmt);
				
			}
		}
		if(fel.size()!=0) {
			for(int felNum = 0;felNum<fel.size();felNum++) {
				List<Statement> lstmt;
				System.out.println("foreach");
				//System.out.println(fel.get(felNum).getBody().asBlockStmt().getStatements());
				lstmt = fel.get(felNum).getBody().asBlockStmt().getStatements();
				extractInfo(lstmt);
			}
		}
		if(wl.size()!=0) {
			for(int wlNum = 0;wlNum<wl.size();wlNum++) {

				System.out.println("while");
				System.out.println(wl.get(wlNum).getBody());
			
				
			}
		}
//		System.out.println(reduceMd);
		return co;
	}
	
	
	private static List<functionInfo> extractInfo(List<Statement> ls) {
		List<functionInfo> lf = new ArrayList<functionInfo>();
		IfStmt is;
		for(int i = 0;i<ls.size();i++) {
			if(ls.get(i).isIfStmt()) {
				is = ls.get(i).asIfStmt();
				Expression ifcondition ;
				ifcondition = is.getCondition();
				if(ifcondition.toString().indexOf("list")!=-1) {
					functionInfo fi = new functionInfo();
					fi.setIsRelationToValue(true);
					fi.getValueList().add("list");
				}
			}
		}
		return lf;
	}
	
}
