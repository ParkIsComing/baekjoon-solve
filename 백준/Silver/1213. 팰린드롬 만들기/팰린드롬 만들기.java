import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        System.out.println(solution(input));
    }
    
    public static String solution(String str) {
        int[] count = countAlphabet(str);
        return makePalindrome(count);
    }
    
    public static int[] countAlphabet(String str) {
        int[] count = new int[26];
        for (int i=0; i<str.length(); i++) {
            count[str.charAt(i) - 'A']++;
        }
        return count;
    }
    
    public static String makePalindrome(int[] count) {
        StringBuilder s1 = new StringBuilder(); // 결과값의 왼쪽 부분
        StringBuilder s2 = new StringBuilder(); // 가운데 한 글자
        StringBuilder s3 = new StringBuilder(); // 오른쪽 부분
        
        for (int i=0; i<26; i++) {
            if (count[i] % 2 == 1) { // 특정 알파벳 개수가 홀수
                if (s2.length() == 1) {  // 이미 가운데 한 글자가 찼으면 불가능
                    return "I'm Sorry Hansoo";
                }
                s2.append((char) (i + 'A'));
            }
            
            for (int j=0; j<count[i]/2; j++) { // 짝수 개수의 남은 알파벳을 s1과 s3에 나눔
                s1.append((char)(i+'A'));
                s3.insert(0, (char)(i+'A'));
            }
        }
        
        
        return s1.toString()+s2.toString()+s3.toString();
    }
}