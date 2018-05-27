package sys_1st_lab;

import java.util.ArrayList;
import java.util.LinkedList;
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
		//Lexems.showList(Lexems.makeTokenList(str));
		Parser.parce(str);
		
		//Stack test
		StackString st = new StackString(Lexems.makeTokenList(str));
		st.execute();
		st.countSt();
		//st.showTable();
		//st.showPoliz();
		
		
		//LL Test
		/*MyLinkedList list = new MyLinkedList();
		Scanner in = new Scanner(System.in);
		int ho;
		if (in.hasNextInt()) {
			ho = in.nextInt();
		} else ho=0;
		for(int i=0;i<ho;i++){
			if (in.hasNextInt()) {
				list.add(new Variable("a",in.nextInt(),"Integer"));
			}
		}
		//Variable ve = (Variable) list.get(4);
		//System.out.println(list.sizeOf());
		//System.out.println(ve.value);
		list.print();*/
		
		
		
		//HS Test
		/*MyHashSet set = new MyHashSet();
		Variable var = new Variable("a",0,"Integer");
		Variable var1 = new Variable("c",0,"Integer");
		set.add(var1);
		set.add(new Variable("b",0,"Integer"));
		set.add(var);
		System.out.println(set.isContains(var));*/
		
		/*ArrayList<MyLinkedList> basket = new ArrayList<MyLinkedList>();
		basket.add(new MyLinkedList());
		basket.get(0).add(1);
		basket.get(0).add(12);
		basket.get(0).add(13);
		basket.get(0).add(14);
		System.out.println(basket.get(0).isContains(14));*/
	}

}
