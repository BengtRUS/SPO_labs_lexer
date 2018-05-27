package sys_1st_lab;

public class Wrapper {
	public Object var;
	public Wrapper prev;
	public Wrapper next;
	Wrapper(Object var, Wrapper last){
		if(last!=null){
			last.next = this;
			this.prev = last;
			this.var = var;
		} else{
			this.prev = last;
			this.var = var;
		}
		
	}
}
