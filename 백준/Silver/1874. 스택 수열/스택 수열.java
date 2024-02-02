import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int[] arr = new int[n+1];
        for (int i=1; i<=n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        
        Stack<Integer> s = new Stack<>();
        int count = 0; // 스택에 넣은 개수
        
        for (int i=1; i<=n; i++) { // 꺼내야 하는 것
            if (s.search(arr[i]) == -1) {
                // 더 넣어야 하는 경우
                if (count < arr[i]){
                    for (int j=count+1; j<=arr[i]; j++) {
                        s.push(j);
                        sb.append('+').append('\n');
                    }
                    s.pop();
                    sb.append('-').append('\n');
                    count = arr[i];
                } else {
                    System.out.println("NO");
                    return;
                }
            } else {
                int tmp = s.search(arr[i]);
                for (int j=0; j<tmp; j++) {
                    s.pop();
                    sb.append('-').append('\n');
                }
            }
        }
        
        System.out.println(sb);
    }
    
}