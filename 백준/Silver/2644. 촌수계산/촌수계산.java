import java.util.*;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedReader;
 
public class Main {
    public static int n,m,start, end;
    public static ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
    public static int visited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(br.readLine());
		
		visited = new int[n+1];
		Arrays.fill(visited, -1); // -1(미방문)로 초기화
		for (int i=0 ; i<=n; i++){
		    list.add(new ArrayList<Integer>());
		}
		for (int i=0; i<m; i++) {
		    st = new StringTokenizer(br.readLine());
		    int a = Integer.parseInt(st.nextToken());
		    int b = Integer.parseInt(st.nextToken());
		    list.get(a).add(b);
		    list.get(b).add(a);
		}
		
		int count = 0;
		dfs(start, count);
		
		System.out.println(visited[end]);
 
	}
 
	public static void dfs(int node, int count) {
		if (visited[node] == -1 ){
		    visited[node] = count;
		    ArrayList<Integer> adj = list.get(node);
		    for (int i : adj) {
		        dfs (i, count+1);
		    }
		}
 
	}
 
}