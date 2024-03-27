import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N,M;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        
        for (int i=0; i<arr.length; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(arr[i]);
            find(arr, list);
        }
    }
    
    static void find(int[] arr, ArrayList<Integer> list) {
        if (list.size() == M) {
            for (int i: list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        
        for (int i=0; i<arr.length; i++) {
            if (!list.contains(Integer.valueOf(arr[i]))) {
                list.add(arr[i]);
                find(arr, list);
                list.remove(Integer.valueOf(arr[i]));
            }
        }
    }
}