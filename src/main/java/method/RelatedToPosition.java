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
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.ModifierVisitor;
import com.github.javaparser.ast.visitor.Visitable;

public class RelatedToPosition {
	
	
	public static void main(String[] args) throws Exception {
		String FILE_PATH = "src/main/java/searchOnInternet/";
		String Filename = "";
		System.out.println("�������ļ����ƣ�");
		Scanner scan = new Scanner(System.in);
		Filename = scan.next();
		CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH+Filename+".java"));
		ModifierVisitor<?> checkProgramVisitor = new checkProgram();
		checkProgramVisitor.visit(cu, null);
		
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

		List<Parameter>  lp = reduceMd.getParameters();
		Expression par ;
		for(int k = 0;k<lp.size();k++) {      //�ҵ���һ��ѭ������ʽΪlist�ı�������
			if(lp.get(k).getTypeAsString().indexOf("ElemwntList")!=-1) {
				//par = lp.get(k).getNameAsString();
				par = lp.get(k).getNameAsExpression();
			}
		}
//		System.out.println(reduceMd);
		return co;
	}
}
