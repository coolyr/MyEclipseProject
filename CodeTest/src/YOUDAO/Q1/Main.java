package YOUDAO.Q1;

import java.awt.image.RescaleOp;
import java.util.*;

import javax.sound.sampled.Line;



public class Main
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
        
		while(in.hasNextInt()){
			int n = in.nextInt();
			int a = in.nextInt();
			int b = in.nextInt();
			
			int[] phs = new int[n];
			
			for(int i=0;i<n;i++){
				phs[i]=in.nextInt();
			}
			
			ArrayList<Integer> res = new ArrayList<Integer>();

			int index = 1;
			while(index<n-1){
				if(phs[index+1]<0)
					index++;
				res.add(index+1);
				phs[index]-=a;
				phs[index-1]-=b;
				phs[index+1]-=b;
				if(phs[index-1]>=0&&phs[index]<0&&phs[index+1]<0){
					while(phs[index]>=0){
						res.add(index-1);
						phs[index-1]-=a;
					}
				}
				if(phs[index-1]<0&&phs[index]<0&&phs[index+1]<0){
					index ++;
				}				
			}
			
			System.out.println(res.size());
			String sen = "";
			for(int re:res){
				sen+=re+" ";
			}
			System.out.println(sen.trim());
		}
		
	}
}
