package sys_1st_lab;

import java.util.ArrayList;

public class MyHashSet {
	ArrayList<MyLinkedList> basket = new ArrayList<MyLinkedList>();
	int n=4;
	MyHashSet(){
		for(int i=0;i<=n;i++){
			basket.add(new MyLinkedList());
		}
	}
	public void add(Variable var){
		int hash = getHashCode(var);
		int number = (hash % n)+1;
		basket.get(number).add(var);
	}
	
	public void remove(Variable var){
		int hash = getHashCode(var);
		int number = (hash % n)+1;
		basket.get(number).remove(basket.get(number).contains(var));
	}
	
	public boolean isContains(Variable var){
		int hash = getHashCode(var);
		int number = hash % n+1;
		return basket.get(number).isContains(var);
	}
	
	public int contains(Variable var){
		int hash = getHashCode(var);
		int number = (hash % n)+1;
		return basket.get(number).contains(var);
	}
	
	public int getHashCode(Variable var) {
		int a = var.name.length()*1000;
		int b = var.value.hashCode();
		int c = var.type.length()*100;
		return a+b+c;		
		
	}
	

}
