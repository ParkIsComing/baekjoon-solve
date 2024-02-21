import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.*;

public class Main {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        for (int i=1; i<n+1; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        
        Stack<Integer> s = new Stack<>();
        int count = 0; // 어디까지 넣었는지 나타냄
        StringBuilder sb = new StringBuilder();
        
        for (int i=1; i<=n; i++) {
            if (s.search(arr[i]) == -1) {// 스택에서 꺼내야하는 수가 없으면
                if (count < arr[i]) { // 더 넣으면 되는 경우
                    for (int j=count+1; j<= arr[i]; j++) {
                        s.push(j);
                        sb.append('+').append('\n');
                        count++;
                    }
                    s.pop();
                    sb.append('-').append('\n');
                } else { // 더 넣을 수 없는 경우
                    System.out.println("NO");
                    return;
                }
            } else {
                while (s.pop() != arr[i]) {
                    sb.append('-').append('\n');
                }
                sb.append('-').append('\n');
            }
              
        }
        
        System.out.println(sb);
    }
    
   
    
}