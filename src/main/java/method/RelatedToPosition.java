package method;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.filechooser.FileNameExtensionFilter;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class RelatedToPosition {
	
	
	public static void main(String[] args) throws Exception {
		String FILE_PATH = "src/main/java/searchOnInternet/";
		String Filename = "";
		Scanner scan = new Scanner(System.in);
		Filename = scan.next();
		CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH+Filename));
	}
}
