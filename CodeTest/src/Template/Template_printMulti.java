package Template;

import java.util.Scanner;


public class Template_printMulti {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Template_printMulti m = new Template_printMulti();
		while(sc.hasNext()) {
			int num = sc.nextInt();       //杈撳叆鐨勭涓�鏁帮紝琛ㄧず涓嬮潰鏈夊嚑缁勬暟鎹�
			for(int i=0; i<num; i++) {
				m.OutPeo(sc.nextInt());
			}
		}
	}
  
  //杈撳叆鍓╀笅浜虹紪鍙风殑鏂规硶
	public void OutPeo(int n)
	{
		int a[]=new int[n];   
		for(int i=0;i<n;i++)
			a[i]=1;   
		int index=0;
        int cou=0;
        int gg=0;
        int dd=0;
        while(true)
        {
             index=0;
             for(int i=0;i<a.length;i++)
     		 {
     			if(a[i]==1)
     			{
     				index++;	
     				if(index==2)
     				{  
     					a[i]=0;
     					cou++;
     					index=0;	
     				}
     			 }
     		   }
                if(n-cou<=3)
  		    	{
  				gg=n-cou;
  				break;
  			    }
     			index=0;
     			for(int i=0;i<a.length;i++)
     			{
     				if(a[i]==1)
     				{
     					index++;
     					if(index==3)
     					{
     						cou++;
     						a[i]=0;
     						index=0;
     					}
     				}
     			}
     			if(n-cou<=3)
     			{
     				gg=n-cou;
     				break;
     			}		
            }
			for(int i=0;i<a.length;i++)
			{
				if(a[i]==1)
				{
					dd++;
					if(dd<gg)
						System.out.print(i+1+" ");
					else
						System.out.println(i+1);
				}		
			}
    }
}
