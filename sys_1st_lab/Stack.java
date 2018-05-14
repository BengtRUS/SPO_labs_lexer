package sys_1st_lab;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Stack {
	ArrayList<Variable> table = new ArrayList<Variable>();
	private Queue<Token> tokens;
	private static Queue<Token> stack;
	private static Queue<Token> deadlock;
	private static Token currentToken;
	Stack(Queue<Token> tokens){
		this.tokens = tokens;
	}
	public void execute(){
		addIn();
	}
	void addIn(){
		if(!tokens.isEmpty()){
			currentToken = tokens.poll();
			if(currentToken.s.equals("DIGIT")){
				stack.add(currentToken);
			} else if(currentToken.s.equals("VAR")){
				boolean found =false;
				for(int i=0; i<table.size();i++){
					if(table.get(i).name.equals(currentToken.value)){
						found=true;
						break;
					}
				}
				if(found){
					stack.add(currentToken);
				} else {
					table.add(new Variable(currentToken.value, 0, "Integer"));
					stack.add(currentToken);
				}
			} else if(!currentToken.s.equals("Space")){
				if(!deadlock.isEmpty()){ 
					while(checkPriority(currentToken,deadlock.peek())){
						stack.add(deadlock.poll());
					}					
				}
				deadlock.add(currentToken);
			}
			
		}
		
	}
	boolean checkPriority(Token last, Token prev){
		if(last.s.equals("BR_C")&&prev.s.equals("BR_O")){
			deadlock.remove();
			deadlock.remove();
		} else if(last.s.equals("BR_C")){
			return true;
		}
		if(last.s.equals("END_KW")&&prev.s.equals("DO_KW")){
			deadlock.remove();
			deadlock.remove();
		} else if(last.s.equals("END_KW")){
			return true;
		}
		return false;
		
	}
}
