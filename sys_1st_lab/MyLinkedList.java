package sys_1st_lab;

public class MyLinkedList {
	private int count;
	private Wrapper first;
	private Wrapper last;
	MyLinkedList(){
		count = 0;
	}
	public void add(Object var){

			if(count==0){
				Wrapper w = new Wrapper(var, null);
				first = w;
				last = w;
				w= new Wrapper(var, w);
			} else{
				Wrapper w = new Wrapper(var, last);
				last = w;
			}
			count++;
	}
	public Object get(int index){
		if(index>count){
			throw new ArrayIndexOutOfBoundsException();
		}
		if(index==0||(count/index)>=2){
			int i=0;
			Wrapper s=first;
			do{
				i++;
				s=s.next;
				
			}while(i<index);
			return s.var;
		} else {
			int i=count;
			Wrapper s=last;
			do{
				i--;
				s=s.prev;
			}while(i>index);
			return s.var;
		}		
	}
	public void remove(int index){
		if(index>count){
			throw new ArrayIndexOutOfBoundsException();
		}
		if(index==0||(count/index)>=2){
			int i=0;
			Wrapper s=first;
			do{
				i++;
				s=s.next;
				
			}while(i<index);
			if((first==last)||(index==0)){
				first=null;
				last=null;
			}  else{
				s.prev.next=s.next;
				s.next.prev=s.prev;
			}
			
		} else {
			int i=count;
			Wrapper s=last;
			do{
				i--;
				s=s.prev;
			}while(i>index);
			s.prev.next=s.next;
			s.next.prev=s.prev;
		}	
		count--;
	}
	public boolean isContains(Object var){
		Wrapper s=first;
		int i=0;
		if(first==last){
			if(s==(null)){
				return false;
			}
		}
		do{
			if(s.var.equals(var)){
				return true;
			}
			s=s.next;
			i++;
		}while(i<count);
		return false;		
	}
	public int contains(Object var){
		Wrapper s=first;
		int i=0;
		if(first==last){
			if(s==(null)){
				return -1;
			}
		}
		do{
			if(s.var.equals(var)){
				return i;
			}
			s=s.next;
			i++;
		}while(i<count);
		return -1;		
	}
	public void print(){
		Wrapper s=first;
		int i=0;
			do{
				Variable v = (Variable) s.var;
				System.out.println(v.type+" "+v.name+"="+v.value);
				s=s.next;
				i++;
			}while(i<count);
	}
	public int sizeOf(){
		return count;
	}
}
