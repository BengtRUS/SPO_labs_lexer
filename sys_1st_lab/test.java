package sys_1st_lab;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {		
		//Vartable test
		/*VarTable table = new VarTable();
		table.put(new Variable("a",2,0));
		table.put("b",1,0);
		table.put("be",1,1);
		table.put("c",3,2);
		table.put("d",table.getVal("b", 2)+table.getVal("a", 2),3);
		System.out.println(table.get("d", 3).value);*/
		
		
		//Parcer+Lexer test
		Scanner in = new Scanner(System.in);
		System.out.println("Type a string >>");
		String str = "";
		// str="all=255+675-15000*8/256";
		if (in.hasNextLine()) {
			str = in.nextLine();
		}
		Lexems.showList(Lexems.makeTokenList(str));
		Parser.parce(str);
	}

}
