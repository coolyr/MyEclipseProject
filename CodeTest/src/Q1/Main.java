package Q1;


import java.util.*;

public class Main
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		System.out.println(line);
		String[] arr = line.split(" ");
		
		String res = "";
		HashMap<String, Integer> arrH = new HashMap<String,Integer>(); 
		for(int i=0;i<arr.length;i++){
			arrH.put(arr[i], 1);
		}
		
		for(int i= 0;i<arr.length;i++){
			for(int j=0;j<arr.length;j++){
				String temp = arr[i]+arr[j];
				if(arrH.containsKey(temp)&&temp.length()>res.length()){
					res = temp;
				}
			}
		}
		System.out.println(res);
		
	}
}
