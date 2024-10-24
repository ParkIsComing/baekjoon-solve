import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int arr[];
    static int n,m;
    
    static int find(int a){ 
        if(a == arr[a])
            return a;
        return arr[a] = find(arr[a]); 
    }
    
    static boolean union(int from,int to){
        int a = find(from);
        int b = find(to);
        if(a != b) { 
            arr[a] = b;
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        for (int i=0; i<arr.length; i++) {
            arr[i] =i;
        }
            
        int networks[][] = new int[m][3];
        StringTokenizer st;
        
        for (int i= 0; i<m; i++) {
            st = new StringTokenizer(br.readLine()," ");
            networks[i][0] = Integer.parseInt(st.nextToken());
            networks[i][1] = Integer.parseInt(st.nextToken());
            networks[i][2] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(networks, Comparator.comparingInt(o->o[2]));

        int answer=0;
        for (int[] network : networks) {
            if(union(network[0],network[1])) { 
                answer += network[2]; 
            }
        }
        
        System.out.println(answer);

    }
    
}