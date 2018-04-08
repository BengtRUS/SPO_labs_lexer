package sys_1st_lab;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.*;
public class Lexems {
enum LexemType {

	VAR( "VAR", Pattern.compile("^[a-z]+[a-z 0-9]*$")),
	DIGIT( "DIGIT", Pattern.compile("^0|[1-9][0-9]*$")),
	OP( "OP", Pattern.compile("^[+]|[-]|[\\*]|[/]$")),	
	ASSIGN_OP( "ASSIGN_OP", Pattern.compile("^=$")),
	SPACE("Space", Pattern.compile("^[ ]*$"));
	
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
			
			in = removeCharAt(in,0);
			if((type=="Space")&&(in.length()>0)&&(type!=parce(temp_st+(in.charAt(0))))) {
				temp_st="";
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
		//if(res=="1") throw new IllegalArgumentException("Строка не соответствует грамматике");
		return res;

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
			 
			 System.out.println("Type= "+type+"  "+"Value= "+value);
		 } while (show.poll() != null);
	 }

/*public static boolean test(String testString){

Pattern p = Pattern.compile("[0-9]*");

Matcher m = p.matcher(testString);

return m.matches();

} */

}