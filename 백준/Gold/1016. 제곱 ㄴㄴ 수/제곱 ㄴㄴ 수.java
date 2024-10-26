import java.util.*;

public class Main {
    static long min, max;
    static int[] sieve;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        min = sc.nextLong();
        max = sc.nextLong();
        sieve = new int[(int)(max - min + 1)];

        long prime = 2;
        int count = sieve.length; // 제곱수로 나누어 떨어지지 않는 수의 개수

        while (prime * prime <= max) {
            long start = min / (prime * prime); // 해당 제곱수로 나누기 시작할 지점
            if (min % (prime * prime) != 0) {
                start += 1;
            }

            // 제곱수로 나누어 떨어지는 지점을 체크
            while (start * prime * prime <= max) {
                int index = (int)(start * prime * prime - min);
                if (sieve[index] == 0) {
                    sieve[index] = 1;
                    count -= 1;
                }
                start += 1;
            }
            prime++;
        }

        System.out.println(count);
    }
}
