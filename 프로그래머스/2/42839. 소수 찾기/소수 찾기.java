import java.util.*;

class Solution {
    static boolean[] isPrime;
    static int maxCount;
    static int maxNum;
    static int answer = 0;
    
    public int solution(String numbers) {
        maxCount = numbers.length(); // 종이조각 개수

        int[] numberArr = new int[numbers.length()];
        for (int i=0; i<numbers.length(); i++) {
            numberArr[i] = Integer.valueOf(numbers.charAt(i)-'0');
        }
        Arrays.sort(numberArr);
        StringBuilder sb = new StringBuilder();
        for (int i=maxCount-1; i>=0; i--) {
            sb.append(numberArr[i]);
        }
        maxNum = Integer.parseInt(sb.toString());// 만들 수 있는 가장 큰 값 
        
        // 숫자별 개수 카운트
        int[] arr = new int[10];
        for (int i=0; i<numbers.length(); i++) {
            int x = Integer.valueOf(numbers.charAt(i) -'0');
            arr[x]++;
        }
        
        // 가장 큰 값까지 소수 확인
        isPrime = new boolean[maxNum+1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i=2; i*i<=maxNum; i++) {
            if(isPrime[i]) {
                for (int j=i*i; j<=maxNum; j+=i) {
                    isPrime[j] = false;
                }
            }
        } 
        
        for (int i=1; i<arr.length; i++) {
            if (arr[i] > 0) {
                arr[i]--;
                makeNumber(0, String.valueOf(i), arr);
                arr[i]++;
            } 
        }
                
        return answer;
    }
    
    public void makeNumber(int count, String num, int[] arr) {
        if (count == maxCount) {
            return;
        }

        if (isPrime[Integer.valueOf(num)]){
            answer++;
        }

        for (int i=0; i<arr.length; i++) {
            if (arr[i] > 0){
                arr[i]--;
                makeNumber(count+1, num + i, arr);
                arr[i]++;
            }
        }
    }
}