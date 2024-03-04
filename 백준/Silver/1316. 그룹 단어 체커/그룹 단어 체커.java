import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static boolean isGroupWord(String input) {
        Stack<Character> s = new Stack<>();
        s.push(input.charAt(0));
        for (int j=1; j<input.length(); j++) {
            int tmp = s.search(input.charAt(j));
            if (tmp == -1 || tmp == 1) { // 처음 나온 문자이거나 바로 전 문자와 같은 문자 
                s.push(input.charAt(j));
            } else{
                return false;
            }
        }
        return true;
            
    }
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int result = 0;
        
        for (int i=0; i<n; i++) {
            String input = br.readLine();
            if (isGroupWord(input)) {
                result++;
            }
            
        }
        
        System.out.println(result);
    }
}