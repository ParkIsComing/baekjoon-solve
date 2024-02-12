import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.*;

public class Main {
    public static int[][] map;
    public static int R,C,T;
    public static int top, bottom;
    public static int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
    
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String input = br.readLine();
      String[] s = new String[2];
      
      StringBuilder sb = new StringBuilder();
      while (input != null) {
          s = input.split(" ");
          
          int idx = 0;
          
          for (char c : s[1].toCharArray()) {
              if (s[0].charAt(idx) == c) {
                  idx++;
              } if (idx == s[0].length()) {
                  break;
              }
          }
          if(idx == s[0].length()) {
              sb.append("Yes").append("\n");
          } else {
              sb.append("No").append("\n");
          }
          input = br.readLine();
      }
      
      System.out.println(sb);
    }
}