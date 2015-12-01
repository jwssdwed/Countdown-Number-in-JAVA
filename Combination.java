package BFS;

import java.util.ArrayList;

public class Combination {

	static ArrayList<Integer> output = new ArrayList<Integer>();
	static ArrayList<String[]> outputlist = new ArrayList<String[]>();
	
	void clear(){
		output=new ArrayList<Integer>();;
		outputlist=new ArrayList<String[]>();;
	}
	
	
	static ArrayList<String[]> select(int[] a,int k) { 
		int[] result = new int[k]; 
		subselect(a,0, 1, result, k); 
   
		for(int i=0;i<output.size();i=i+k){
			String[] cal = new String[k];
			for(int p=0;p<k;p++){
				cal[p]=output.get(i+p).toString();
			}
			outputlist.add(cal);
		}
		return outputlist;
	}
	
	private static void subselect(int[] a,int head, int index, int[] r, int k) { 
		for (int i = head; i < a.length + index - k; i++) { 
			if (index < k) { 
				r[index - 1] = a[i]; 
				subselect(a,i + 1, index + 1, r, k); 
			} else if (index == k) { 
				r[index - 1] = a[i]; 
				perm(r,0,r.length-1);
				subselect(a,i + 1, index + 1, r, k); 
			} else { 
				return;
			}
		}
	}
	
	public static void perm(int[] buf,int start,int end){
		if(start==end){//one number
			for(int i=0;i<=end;i++){  
				output.add((Integer)buf[i]);
			}
		}
		else{//more than one number
			for(int i=start;i<=end;i++){
				int temp=buf[start];//exchange the first and the tail 
				buf[start]=buf[i];  
				buf[i]=temp;  

				perm(buf,start+1,end);//tail permutation

				temp=buf[start];//move back
				buf[start]=buf[i];  
				buf[i]=temp;  
			}  
		}  
	}
}
