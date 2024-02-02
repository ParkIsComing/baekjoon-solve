import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int n = Integer.parseInt(br.readLine());
      Set<String> words = new HashSet<>();
      for (int i =0; i<n; i++) {
          words.add(br.readLine());
      }
      List<String> wordList = new ArrayList<>(words);
      Collections.sort(wordList, new Comparator<String>() {
          @Override
          public int compare(String s1, String s2) {
              if (s1.length() == s2.length()) {
                  return s1.compareTo(s2); // 길이가 같으면 사전순 정렬
              } else {
                  return s1.length() - s2.length();
              }
          }
      });
      wordList.forEach(System.out::println);

    }
}