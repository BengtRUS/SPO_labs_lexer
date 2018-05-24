package sys_1st_lab;

import java.util.ArrayList;
import java.util.Stack;

public class StackMachine {
	public int cout(Stack<Token> stack, ArrayList<Variable> table){
		
		return 0;
	}
	public int sum(int s1, int s2){
		return(s1+s2);
	}
	public int dif(int s1, int s2){
		return(s1-s2);
	}
	public int mul(int s1, int s2){
		return(s1*s2);
	}
	public int div(int s1, int s2){
		return(s1/s2);
	}
	public boolean greater(int s1, int s2){
		if(s1>s2) return true; else return false;
	}

}
