import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n, max = Integer.MIN_VALUE;
    static int[] arr;
    static int[] aPlusb;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        for (int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        // a+b+c = x는 a+b = x-c로 바꿀 수 있다
        // a+b 조합 저장 (n(n+1)/2개)
        aPlusb = new int[n*(n+1)/2];
        int count = 0;
        for (int i=0; i<n; i++) {
            for (int j=i; j<n; j++) {
                aPlusb[count++] = arr[i] + arr[j];
            }
        }
        
        Arrays.sort(aPlusb); // 이진탐색을 위한 정렬

        // 가능한 x-c값이 a+b 조합에 있는지 확인하면서 최대가 되는 k값을 찾음
        for (int x=0; x<n; x++) {
            for (int c=0; c<x; c++) {
                if (binarySearch(arr[x] - arr[c])){
                    max = Math.max(arr[x], max);
                }
                
            }
        }
        
        System.out.println(max);
        
    }
    
    public static boolean binarySearch(int target) {
        int left = 0;
        int right = aPlusb.length;
        
        boolean flag = false;
        while (left <= right) {
            int mid = (left+right) / 2;
            
            if (aPlusb[mid] == target) {
                flag = true;
                break;
            } else if (aPlusb[mid] < target) {
                left = mid + 1;
            } else {
                right = mid -1;
            }
        }
        return flag;
    }
}