import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    public static void main(String args[]) throws IOException {
        
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String input = br.readLine();
      int answer = 0;
      
      
      // 최소가 되려면 빼기를 기준으로 우선 분리
      String[] arr = input.split("-");
      for(int i=0; i<arr.length; i++) {
          String[] numbers = arr[i].split("\\+");
          int tmp = 0;
          for (String number: numbers) {
              tmp += Integer.parseInt(number);
          }
          
          if (i==0) {
              answer += tmp;
          } else {
              answer -= tmp;
          }
      }
      
      System.out.println(answer);
      
      
      
    }
    
}