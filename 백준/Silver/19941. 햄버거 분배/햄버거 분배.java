import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static boolean canEat(int start, int end, char[] burger) {
        for (int i=start; i<=end; i++) {
            if (i>=0 && i<burger.length && burger[i] == 'H') {
                burger[i] = 'E';
                return true;
            }
        }
        return false;
    }
    
    static int solution(int n, int k, char[] burger) {
        int answer = 0;
        for (int i=0; i<burger.length; i++) {
            if (burger[i] != 'P') {// 햄버거나 이미 먹은 경우는 패스
                continue;
            }
            
            if (canEat(i-k, i-1, burger)) { // 왼쪽부터 탐색
                answer++;
            } else if (canEat(i+1, i+k, burger)) {
                answer++;
            }
        }
        
        return answer;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        String hamburgerStr = sc.next();
        char[] hamburger = hamburgerStr.toCharArray();
        System.out.println(solution(n,k,hamburger));

    }
}
    
    