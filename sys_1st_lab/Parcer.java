package sys_1st_lab;

import java.util.Queue;

public class Parcer {
	
	private static Token currentToken;
	private static Queue<Token> tokens;
		
		
	public static void parce(String in){
		tokens = Lexems.makeTokenList(in);
		lang();
		System.out.println("OK");
	}
	static void match(){
			currentToken = tokens.element();
	}
	static void remove(){
		tokens.poll();
	}
	static void lang(){
		expr();
	}
	static void expr(){
		VAR();
		ASSIGN_OP();
		value();
	}
	static void VAR(){
		match();
		if(!(currentToken.s.equals("VAR"))){
			throw new IllegalArgumentException("ќжидалось var, получено " + currentToken.s);
		}
		remove();
		
	}
	private static void ASSIGN_OP() {
		match();
		if(!(currentToken.s.equals("ASSIGN_OP"))){
			throw new IllegalArgumentException("ќжидалось assign_op, получено " + currentToken.s);
		}
		remove();
		
	}
	private static void value() {
		try {
			DIGIT();
		}
		catch(IllegalArgumentException e){
			VAR();
		}

	}
	private static void DIGIT() {
		match();
		if(!(currentToken.s.equals("DIGIT"))){
			throw new IllegalArgumentException("ќжидалось digit, получено " + currentToken.s);
		}
		remove();
		
	}
	

}
