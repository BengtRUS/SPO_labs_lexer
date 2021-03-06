package sys_1st_lab;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.*;
public class Lexems {
enum LexemType {
	WHILE_KW("WHILE_KW", Pattern.compile("^while $")),//
	DO_KW("DO_KW", Pattern.compile("^do $")),//
	END_KW("END_KW", Pattern.compile("^end.$")),//
	VAR( "VAR", Pattern.compile("^[a-z]+[a-z0-9]*$")),//
	DIGIT( "DIGIT", Pattern.compile("^0|[1-9][0-9]*$")),//
	OP( "OP", Pattern.compile("^[+]|[-]|[\\*]|[/]$")),	//<<<<<<<<
	COMP("COMP", Pattern.compile("^(>)|(<)|(==)|(!=)$")),//<<<<<<<<<<
	ASSIGN_OP( "ASSIGN_OP", Pattern.compile("^=$")),//<<<<<<<<<<<
	BR_O("BR_O", Pattern.compile("^\\($")),//
	BR_C("BR_C", Pattern.compile("^\\)$")),//
	SPACE("Space", Pattern.compile("^ *$")),//
	PRINT("PRINT", Pattern.compile("^print $"));
	
	String type;
	Pattern pattern;
	LexemType(String t, Pattern p) {

		type = t;
		pattern = p;

	}

	}
	public static Queue<Token> makeTokenList(String in){
		
		Queue<Token> tok = new LinkedList<Token>();
		
		//String in = "all=555+7";

		String temp_st = new String();

		String type;

		while(in.length()>0) {

			temp_st=temp_st+(in.charAt(0));

			type = parce(temp_st);
			//System.out.println(type);
			in = removeCharAt(in,0);
			if((type=="Space")&&(in.length()>0)&&(type!=parce(temp_st+(in.charAt(0))))) {
				temp_st="";
				tok.offer(new Token(type,temp_st));
			}
			else if((in.length()>0)&&(isPriority(parce(temp_st+(in.charAt(0)))))){
				tok.offer(new Token(parce(temp_st+(in.charAt(0))),temp_st+(in.charAt(0))));
				temp_st="";
				in = removeCharAt(in,0);
			}
			else if((in.length()>0)&&(type!=parce(temp_st+(in.charAt(0))))){
				tok.offer(new Token(type,temp_st));
				temp_st="";
			} else if(in.length()==0){
				type = parce(temp_st);
				tok.offer(new Token(type,temp_st));
			}

		}
		return tok;

	}

	public static String parce(String s) {

		String res = "1";

		for(LexemType lexem : LexemType.values()) {

			Pattern pat = lexem.pattern;

			Matcher m = pat.matcher(s);

			m.find();

			if(m.matches()) {

				res = lexem.type;

				break;

			}

		}
		//if(res=="1") throw new IllegalArgumentException("������ �� ������������� ����������");
		return res;

	}
	static boolean isPriority(String s1){
		switch(s1){
			case "WHILE_KW":
				return true;
			case "PRINT":
				return true;
			case "DO_KW":
				return true;
			case "END_KW":
				return true;
			case "COMP":
				return true;
			case "Space":
				return true;
			default:
				return false;
		}
		
		
	}
	 public static String removeCharAt(String s, int pos) {
	      return s.substring(0, pos) + s.substring(pos + 1);
	 }
	 public static void showList(Queue<Token> list){
		 Queue<Token> show = list;
		 String type="";
		 String value="";
		 do{
			 if(show.isEmpty()){
				 break;
			 }
			 type = show.element().s;
			 value = show.element().value;
			 if (type=="Space") continue;
			 System.out.println("Type= "+type+"  "+"Value= "+value);
		 } while (show.poll() != null);
	 }

/*public static boolean test(String testString){

Pattern p = Pattern.compile("[0-9]*");

Matcher m = p.matcher(testString);

return m.matches();

} */

}