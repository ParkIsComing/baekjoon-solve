import java.util.*;
import java.io.*;

public class Main {
    static final int MAX = 1_000_000;
    static boolean[] isPrime = new boolean[MAX + 1];

    // 소수 전처리
    private static void sieve() {
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= MAX; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= MAX; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }
    
    // n에 대한 골드바흐 검증
    private static String checkGoldbach(int n) {
        for (int a = 3; a <= n - 3; a += 2) {
            if (isPrime[a] && isPrime[n - a]) {
                return n + " = " + a + " + " + (n - a);
            }
        }
        return "Goldbach's conjecture is wrong.";
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        sieve(); 

        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            if (n == 0) break;
            sb.append(checkGoldbach(n)).append('\n');
        }

        System.out.print(sb);
    }
}
