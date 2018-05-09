package sys_1st_lab;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
public class VarTable{
	
	Map<Integer,ArrayList<Variable>> table = new HashMap<Integer,ArrayList<Variable>>();
	
	public void put(Variable var){
		if(table.containsKey(var.visibility)){
			int index = check(var);
			if(index==-1){
					table.get(var.visibility).add(var);
			} else{
				table.get(var.visibility).set(index, var);
			}
		} else {
			table.put(var.visibility, new ArrayList<Variable>());
			table.get(var.visibility).add(var);
		}
	}
	
	public Variable get(String name, int vis){
		Variable var = new Variable(name, 0, vis);
		int index = check(var);
		if(index!=-1){
			return (table.get(checkVis(var)).get(index));
		} else{
			throw new NoSuchElementException();
		}
		
		
	}
	private int check(Variable var){
		for(int i=var.visibility; i>=0; i--){
			for(int j=0; j<table.get(i).size(); j++){
				if(table.get(i).get(j).n.equals(var.n)){
					return j;
				}
			}
		}
		return -1;
		
	}
	private int checkVis(Variable var){
			for(int i=var.visibility; i>=0; i--){
				for(int j=0; j<table.get(i).size(); j++){
					if(table.get(i).get(j).n.equals(var.n)){
						return i;
					}
				}
			}
			return -1;
		
	}
	public void put(String n, int v, int vis){
		put(new Variable(n,v,vis));
	}
	public int getVal(String n, int vis){
		return get(n,vis).value;
	}
	
	
}
