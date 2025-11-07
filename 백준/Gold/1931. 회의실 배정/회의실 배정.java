import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[][] arr;

    public static void main(String[] args) throws IOException{
        input();
        solution();
    }

    static void input() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][2];
        StringTokenizer st;
        for (int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
    }

    static void solution() {
        Arrays.sort(arr, (a,b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });

        int end = 0;
        int cnt = 0;
        for (int[] meeting : arr) {
            if (meeting[0] >= end) {
                end = meeting[1];
                cnt++;
            }

        }

        System.out.println(cnt);
    }

}
