import java.util.*;


public class Main {
    public static int solution(int a, int b) {
        int tmp = b % 4;
        if (tmp == 0) tmp = 4;
        int result = (int) Math.pow(a, tmp) % 10;
        return result == 0 ? 10 : result;
    }
    
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
     
        for (int i=0; i<T; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            sb.append(solution(a,b));
            sb.append("\n");
        }

        System.out.println(sb.toString());
  }
}
