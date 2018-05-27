package sys_1st_lab;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackString {
	ArrayList<Variable> table = new ArrayList<Variable>();
	private Queue<Token> tokens;
	private static Stack<Token> stack = new Stack<Token>();
	private static Stack<Token> deadlock = new Stack<Token>();
	private static Token currentToken;
	private static ArrayList<Token> poliz = new ArrayList<Token>();
	StackString(Queue<Token> tokens){
		this.tokens = tokens;
	}
	public Stack<Token> execute(){
		addIn();
		stack = reverse(stack);
		makePoliz();
		//showPoliz();
		/*while(!stack.isEmpty()){
			s+=(stack.pop().value)+" ";
		}*/
		//print(stack);
		
		return stack;
		
	}
	public ArrayList<Variable> getTable(){
		return table;
	}
	void addIn(){
		while(!tokens.isEmpty()){
			currentToken = tokens.poll();
			if(currentToken.s.equals("DIGIT")){
				stack.push(currentToken);
			} else if(currentToken.s.equals("VAR")){
				boolean found =false;
				int a = findInTable(currentToken.value);
				if(a!=-1) found=true;
				if(found){
					stack.push(currentToken);
				} else {
					table.add(new Variable(currentToken.value, null, "Integer"));
					stack.push(currentToken);
				}
			} else 
				if(checkDeadlock(currentToken))	{
					deadlock.push(currentToken);
				
			}
			
			
		}
	while(!deadlock.isEmpty()){
		stack.push(deadlock.pop());
	}
	}
	
	private boolean checkDeadlock(Token last) {
		boolean prior = true;
		if(last.s.equals("BR_C")){
			if(deadlock.isEmpty()) return true;
			while(!deadlock.peek().s.equals("BR_O")){
				stack.push(deadlock.pop());
			}
			deadlock.pop();
			return false;
		} else if(last.s.equals("DO_KW")){
			Token l = new Token("LABEL_DO", "0");
			stack.push(l);
			return true;
		} else if(last.s.equals("END_KW")){
			Token l = new Token("LABEL_END", "0");
			if(deadlock.isEmpty()) return true;
			while(!deadlock.peek().s.equals("DO_KW")){
				stack.push(deadlock.pop());
			}
			deadlock.pop();
			stack.push(l);
			return false;
		} else if((last.s.equals("OP"))||(last.s.equals("COMP"))||(last.s.equals("ASSIGN_OP"))||(last.s.equals("Space"))) {
			while(prior){			
				if(deadlock.isEmpty()) {
					break;
				}
				Token prev = deadlock.peek();
				if(checkPriority(last)<checkPriority(prev)){
					stack.push(deadlock.pop());
				} else prior=false;
			}
		} else if((last.s.equals("WHILE_KW"))||(last.s.equals("FU_ONE"))||(last.s.equals("FU_TWO"))){
			stack.push(last);
			return false;
		}
		return true;	
		
	}
	private int checkPriority(Token last){
		int priority=0;
		switch(last.s){
		case("OP"):{
				if((last.value.equals("*"))||(last.value.equals("/"))){
					priority=4;
				} else {
					priority=3;
				}
			break;
			}
		case("ASSIGN_OP"):{
			priority=2;
			break;
		}
		case("COMP"):{
			priority=1;
			break;
		}
		case("FU_ONE"):{
			priority=0;
			break;
		}
		case("FU_TWO"):{
			priority=0;
			break;
		}
		case("Space"):{
			priority=0;
			break;
		}
		case("PRINT"):{
			priority=0;
			break;
		}
		}
		return priority;
	}
	public void print(Stack<Token> s) {
		Stack<Token> show = s;
		 String type="";
		 String value="";
		 do{
			 if(show.isEmpty()){
				 break;
			 }
			 type = show.peek().s;
			 value = show.pop().value;
			 if (type=="Space") continue;
			 System.out.println("Type= "+type+"  "+"Value= "+value);
		 } while (true);
	}
	 static Stack<Token> reverse(Stack<Token> s) {
		 Stack<Token> st= new Stack<Token>();	
		 while(!s.isEmpty()){
			 st.push(s.pop());
		 }
		 return st;		 
	        
	    }
	 public void showTable(){
		 for(int i=0;i<table.size();i++){
			 System.out.println(table.get(i).type+" "+table.get(i).name+" "+table.get(i).value);
		 }
	 }
	 
	 
	 /////////////////////////////////////////////////////////////////////////////////////////////
	 public int countSt(){
		 Stack<Token> box = new Stack<Token>();
		 while(!stack.isEmpty()){
			 box.push(stack.pop());
			 
			 
			 //assign
			 if(box.peek().s.equals("ASSIGN_OP")){
				 box.pop();
				 int f=0;
				 int number = getDigit(box.peek().value);
				 box.pop();
				 f=findInTable(box.peek().value);
				 String val = box.pop().value;
					 table.set(f, new Variable(val, number, "Integer"));
					 
					 
					 
					 //Operation
			 } else if(box.peek().s.equals("OP")){
				 String vall = box.pop().value;
				 int s1 = getDigit(box.pop().value);
				 int s2 = getDigit(box.pop().value);
				 switch(vall){
					 case("+"):{
						 box.push(new Token("DIGIT", String.valueOf(s1+s2)));
						 break;
					 }
					 case("-"):{
						 box.push(new Token("DIGIT", String.valueOf(s2-s1)));
						 break;
					 }
					 case("*"):{
						 box.push(new Token("DIGIT", String.valueOf(s1*s2)));
						 break;
					 }
					 case("/"):{
						 box.push(new Token("DIGIT", String.valueOf(s2/s1)));
						 break;
					 }
				 }
				 
				 
				 
				 //COMP
			 } else if(box.peek().s.equals("COMP")){
				 String vall = box.pop().value;
				 int s1 = getDigit(box.pop().value);
				 int s2 = getDigit(box.pop().value);
				 stack.pop();
				 switch(vall){
					 case(">"):{
						 stack.push(new Token("LABEL_DO", Boolean.toString(s2>s1)));
						 break;
					 }
					 case("<"):{
						 stack.push(new Token("LABEL_DO", Boolean.toString(s2<s1)));
						 break;
					 }
					 case("=="):{
						 stack.push(new Token("LABEL_DO", Boolean.toString(s1==s2)));
						 break;
					 }
					 case("!="):{
						 stack.push(new Token("LABEL_DO", Boolean.toString(s1!=s2)));
						 break;
					 }
				 }
				 
				 
				 
				 //do
			 } else if(box.peek().s.equals("LABEL_DO")){
				 if(!Boolean.valueOf(box.pop().value)){
					 while(!stack.peek().s.equals("LABEL_END")){
						 stack.pop();
					 }
					 stack.pop();
					 poliz.set(getEndIndex(),new Token("Space", " "));
				 }
				
				 
				 
				 
				 //end				 			 
			 } else if(box.peek().s.equals("LABEL_END")){				 
				 int it = getEndIndex();
				// System.out.println(it);
				 do{
					 //System.out.println(poliz.get(it).s+" "+poliz.get(it).value);
					 stack.push(poliz.get(it));
					 it--;
				 } while(!poliz.get(it).s.equals("WHILE_KW"));
				 
				 
				 
				 //1 func				 
			 } else if(box.peek().s.equals("FU_ONE")){
				 box.push(stack.pop());
				 Token s1 = box.pop();
				 String vall = box.pop().value;

				 switch(vall){
				 case("print "):{
					 System.out.println(s1.value+"="+table.get(findInTable(s1.value)).value);
					 break;
				 }
				 case("llcreate "):{
					 int a = findInTable(s1.value);
					 table.remove(findInTable(s1.value));
					 if(a!=-1){
						 table.add(a, new Variable(s1.value,new MyLinkedList(),"LinkedList"));
					 } else{
						 table.add(new Variable(s1.value,new MyLinkedList(),"LinkedList"));
					 }
					 break;
				 }
				 case("llprint "):{
					 MyLinkedList list = (MyLinkedList) table.get(findInTable(s1.value)).value;
					 list.print();
					 break;
				 }
				 case("hscreate "):{
					 int a = findInTable(s1.value);
					 table.remove(findInTable(s1.value));
					 if(a!=-1){
						 table.add(a, new Variable(s1.value,new MyHashSet(),"HashSet"));
					 } else{
						 table.add(new Variable(s1.value,new MyHashSet(),"HashSet"));
					 }
					 break;
				 }
			 }
				 
				 
				 
				 //2 func				 
			 } else if(box.peek().s.equals("FU_TWO")){
				 box.push(stack.pop());
				 box.push(stack.pop());
				 Token s1 = box.pop();
				 Token s2 = box.pop();
				 String vall = box.pop().value;
				 MyLinkedList list = null;
				 MyHashSet set = null;
				 try{
					 list = (MyLinkedList) table.get(findInTable(s2.value)).value;
				 } catch(ClassCastException e){
					 set = (MyHashSet) table.get(findInTable(s2.value)).value;
				 }
				 switch(vall){
				 case("lladd "):{
					 list.add(table.get(findInTable(s1.value)));
					 break;
				 }
				 case("llget "):{
					 Variable hmm = (Variable) list.get(Integer.parseInt(s1.value));
					 System.out.println(hmm.type+" "+hmm.name+"="+hmm.value);
					 break;
				 }
				 case("llremove "):{
					 list.remove(Integer.parseInt(s1.value));
					 break;
				 }
				 case("llcontains "):{
					 System.out.println(list.isContains(table.get(findInTable(s1.value))));
					 break;
				 }
				 case("hsadd "):{
					 set.add(table.get(findInTable(s1.value)));
					 break;
				 }
				 case("hsremove "):{
					 set.remove(table.get(findInTable(s1.value)));
					 break;
				 }
				 case("hscontains "):{
					 System.out.println(set.isContains(table.get(findInTable(s1.value))));
					 break;
				 }
			 }
			 }
		 }
		 //print(box);
		return 0; 
	 }
	 private int findInTable(String s){
		 for(int i=0; i<table.size();i++){
				if(table.get(i).name.equals(s)){
					return i;
				}
			 }
		 return -1;
	 }
	 private int getDigit(String s){
		 int number=0;
		 try{
			 number = Integer.parseInt(s);
		 } catch(NumberFormatException e1){
			 number = (Integer) table.get(findInTable(s)).value;
		 }
		 return number;
	 }
	 
	 
	 
	 //////////////////////////////////////////////////////
	 private void makePoliz(){
		 Stack<Token> cpStack= (Stack<Token>) stack.clone();
		 int i=0,k=0;
		 while(!cpStack.isEmpty()){
			 if(cpStack.peek().s.equals("WHILE_KW")){
				 k=i;
			 } else if(cpStack.peek().s.equals("LABEL_END")){
				 cpStack.push(new Token(cpStack.pop().s, String.valueOf(k)));
				 poliz.add(cpStack.pop());
				 i++;
				 continue;
			 }
			 i++;
			 poliz.add(cpStack.pop());
		 }
		
	 }
	 public void showPoliz(){
		 for(int i=0;i<poliz.size();i++){
			 System.out.println(poliz.get(i).s+" "+poliz.get(i).value);
		 }
	 }
	 private int getEndIndex(){
		 for(int i=0;i<poliz.size();i++){
			 if(poliz.get(i).s.equals("LABEL_END")) {
				 return i;
			 }
		 }
		return -1;
		 
	 }
}
