import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Node {
    int idx;
    int cost;
    
    Node(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }
}

public class Main {
    static int n;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
    
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        
        for (int i=0;i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for (int k=0; k<n; k++){
            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {
                    if (arr[i][k] == 1 && arr[k][j] ==1) {
                        arr[i][j] = 1;
                    } 
                }
            }
        }
        
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("");
        }
        
    }
}