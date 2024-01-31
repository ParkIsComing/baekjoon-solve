import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String input = br.readLine().toLowerCase();
      int[] alphabet = new int[26];
      
      char[] inputs = input.toCharArray();
      for (char c : inputs) {
          if ('a' <= c && c <= 'z') {
              alphabet[c-'a']++;
          }
      }
      
      int max = Integer.MIN_VALUE;
      char maxChar = ' ';
      for (int i = 0; i<alphabet.length; i++) {
          if (alphabet[i] > max) {
              max = alphabet[i];
              maxChar = (char) ('A' + i);
          } else if (alphabet[i] == max) {
              maxChar = '?';
          }
      }
      
      System.out.println(maxChar);
    }
}