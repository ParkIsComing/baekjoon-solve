import java.util.*;
import java.io.*;
import java.util.StringTokenizer;


public class Main {
    public static int n, c;
    
    public static int binarySearch(int[] arr, int c, int start, int end) {
        int result = 0;
        while (start <= end) {
            int mid = (start + end) / 2; // mid가 gap
            int value = arr[0];
            int count = 1;
            for (int i : arr) {
                if (i >= value + mid) {
                    value = i;
                    count += 1;
                }
            }
            
            if (count >= c) { // gap을 증가
                start = mid + 1;
                result = mid; // 현재 최적의 gap을 저장
            } else {
                end = mid - 1; // gap을 감소
            }
        }
        return result;
    }
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        
        Arrays.sort(arr);
        int start = 1; // 최소 gap (gap을 반씩 줄여가면서 최적의 gap을 찾는 것)
        int end = arr[arr.length-1] - arr[0];
        
        System.out.println(binarySearch(arr, c, start, end));
        
    }
    
}