import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String[] arr;
    public static void main(String args[]) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String input = br.readLine();
      char[] original = input.toCharArray();
      char[] moved = new char[original.length];
      int n = Integer.parseInt(br.readLine());
      arr = new String[n];
      for (int i=0; i<n; i++) {
          arr[i] = br.readLine();
      }
      
      String result = null;
      for (int i=1; i<=26; i++) {
          for (int j=0; j<original.length; j++) {
              char shiftedChar = (char)(original[j] + i);
              if (shiftedChar > 'z') {
                  shiftedChar = (char)('a' + (shiftedChar - 'z' - 1));
              }
              
              moved[j] = shiftedChar;
          }
          
          StringBuilder sb = new StringBuilder();
          for (char c : moved) {
              sb.append(c);
          }
          String output = sb.toString();
          if (checkExist(output)) {
              result = output;
              break;
          }
      }
      System.out.println(result);
      
    }
    
    public static boolean checkExist(String output) {
        for (String s : arr) {
            if (output.contains(s)) {
                return true;
            }
        }
        return false;
    }
}