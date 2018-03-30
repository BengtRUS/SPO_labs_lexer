package sys_1st_lab;

import java.util.Scanner;

public class test {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Type a string >>");
		String str = "";
		//str="all=255+675-15000*8/256";
		if(in.hasNextLine()){
			str = in.nextLine();
		}
		Lexems.showList(Lexems.makeTokenList(str));
	}

}
