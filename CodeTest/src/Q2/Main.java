package Q2;

import java.util.*;



public class Main
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		int n = new Integer(in.nextLine());
		
		ArrayList<String> names = new ArrayList<String>();
		HashMap<String, List<String>> name_condition = new HashMap<String,List<String>>();
		Main m = new Main();
		for(int i=0;i<n;i++){
			String[] name_limit = in.nextLine().split(" ");
			String param = in.nextLine();
			
			String name = name_limit[0];
			names.add(name);

			
			List<String> conditions = m.getConditions(name_limit,param);
			name_condition.put(name,conditions);
			
		}
		
		//sort by dict
		Collections.sort(names);
		
		//reconstruct them
		m.construtct(names,name_condition,n,"",0);
		
		
	}

	private void construtct(ArrayList<String> names, HashMap<String, List<String>> name_condition,int total,String req,int index)
	{
		if(req.split("&").length==total+1){
			System.out.println(req.substring(req.indexOf("&")));
		}
		if(index>names.size()-1){
			return;
		}
		
		for(String cond:name_condition.get(names.get(index))){
			if("".equals(req)){
				construtct(names,name_condition,total,cond,index+1);
			}else{
				construtct(names,name_condition,total,req+"&"+cond,index+1);
			}
			
		}		
	}

	private List<String> getConditions(String[] name_limit, String param)
	{
		List<String> res = new ArrayList<String>();
		String name = name_limit[0];
		if("0".equals(name_limit[1])){
			res.add("");
		}
		
		if("0".equals(name_limit[3])){
			for(String p:param.split(",")){
				res.add(name+"="+p);
			}
		}else{
			for(String p:param.split(",")){
				res.add(name+"="+p);
				res.add(name+"=-"+p);
			}
		}
		
		if("1".equals(name_limit[2])){
			String[] pa = param.split(",");
			for(int i=1;i<5;i++){
				if(pa.length-1>=i){
					recurseParam(i,res,pa,name,0,"","0".equals(name_limit[3]));
				}
				
			}
		}
		
		return res;
		
	}

	private void recurseParam(int paramNum, List<String> res, String[] pa, String name,int index,String parms,boolean isNegtive)
	{
		if(parms.split(",").length==paramNum+1){
			res.add(name+"="+parms.substring(parms.indexOf(",")));
			return;
		}
		
		for(int i=index;i<pa.length;i++){
			recurseParam(paramNum, res, pa, name, i, parms+","+pa[i],isNegtive);
			if(isNegtive){
				recurseParam(paramNum, res, pa, name, i, parms+",-"+pa[i],isNegtive);
			}
		}
	}
}
