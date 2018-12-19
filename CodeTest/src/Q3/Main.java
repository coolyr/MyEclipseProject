package Q3;

import java.util.Scanner;

public class Main {
	static int result = 1;
	static int m;
	static int n;
	static int L;
	static int s;
	public static void main (String[] args) throws java.lang.Exception{
		Scanner in = new Scanner(System.in);
		int count = in.nextInt();
		n = in.nextInt();
		m = in.nextInt();
		L = in.nextInt();
		s = in.nextInt();
		char[][] road = new char[m][n];
		for(int i=1; i<=count; i++){
			//read matrix
			for(int x=0;x<m;x++){
				String temp = in.next();
				for(int y=0;y<n;y++){
					road[x][y] = temp.charAt(y);
				}
			}
			boolean[][] visited = new boolean[m][n];
			visited[m-1][0] = true;
			recursive(road,visited,m-1,0);
			System.out.println("Test Case " + count + ":" + result);
		}

		
	}
	
	public static void recursive(char[][] road, boolean[][] visited, int cur_x, int cur_y)
	{
		for(int i=1;i<=L;i++){
			if(cur_x + i < m && road[cur_x+i][cur_y] == '#' && visited[cur_x+i][cur_y] == false){
				visited[cur_x+i][cur_y] = true;
				result++;
				recursive(road,visited,cur_x+i,cur_y);
			}
			if(cur_x - i >=0 && road[cur_x-i][cur_y] == '#' && visited[cur_x-i][cur_y] == false){
				visited[cur_x-i][cur_y] = true;
				result++;
				recursive(road,visited,cur_x-i,cur_y);
			}
		}
		int step = 1;
		while(cur_y + step < n){
			if(road[cur_x][cur_y+step] == '#' && visited[cur_x][cur_y+step] == false){
				visited[cur_x][cur_y+step] = true;
				result++;
				recursive(road,visited,cur_x,cur_y+step);
			}
			else
			{
				break;
			}
		}
		step = 1;
		while(cur_y - step >= 0){
			if(road[cur_x][cur_y-step] == '#' && visited[cur_x][cur_y-step] == false){
				visited[cur_x][cur_y-step] = true;
				result++;
				recursive(road,visited,cur_x,cur_y-step);
			}
			else
			{
				break;
			}
		}
	}
   
}
