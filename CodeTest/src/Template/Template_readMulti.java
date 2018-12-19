package Template;

import java.util.Scanner;

public class Template_readMulti {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int[] w = new int [n];
		for(int i=0;i<n;i++){
			w[i] = in.nextInt();
		}
		
		int op,n1,n2;
		for(int j=0;j<m;j++){
			op = in.nextInt();
			n1 = in.nextInt();
			n2 = in.nextInt();
			
			if(op==1)
				w[n1-1]=n2;
			if(op==2){
				int sum = 0;
				for(int k=n1-1;k<n2;k++)
					sum+=w[k];
				System.out.println(sum);
			}
			if(op==3){
				int max = w[n1-1];
				for(int k=n1;k<n2;k++)
					max = w[k]>max ? w[k]: max;
				System.out.println(max);
			}
		}
	}
}
