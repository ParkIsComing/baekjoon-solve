import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,k;
    static int[] arr;

    public static void main(String[] args){
        input();
        solution();
    }

    static void input(){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        String str = sc.next();
        arr = str.chars()
                .map( c -> c - '0')
                .toArray();
    }

    static void solution() {
        int remove = k;
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i : arr) {
            while (!stack.isEmpty() && remove > 0 && stack.peekLast() < i) {
                stack.pollLast();
                remove--;
            }
            stack.addLast(i);
        }

        while (remove-- > 0) {
            stack.pollLast();
        }

        StringBuilder sb = new StringBuilder(stack.size());
        for (int i : stack) {
            sb.append(i);
        }
        System.out.println(sb);
    }
}
