import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static class Judge {
        private Integer age;
        private String name;
        private Integer id;
        
        public Judge (Integer age, String name, Integer id) {
            this.age = age;
            this.name = name;
            this.id = id;
        }
        
        public Integer getAge() {
            return age;
        }
        public String getName() {
            return name;
        }
        public Integer getId() {
            return id;
        }
        
    } 

    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int n = Integer.parseInt(br.readLine());
      List<Judge> judges = new ArrayList<>();
      StringTokenizer st;
      for (int i=0; i<n; i++) {
          st = new StringTokenizer(br.readLine());
          Integer age = Integer.parseInt(st.nextToken());
          String name = st.nextToken();
          judges.add(new Judge(age, name, i));
      }
      
      Collections.sort(judges, new Comparator<Judge>() {
          @Override
          public int compare(Judge j1, Judge j2) {
              if (j1.getAge() == j2.getAge()) {
                  return j1.getId().compareTo(j2.getId());
              } else {
                  return j1.getAge() - j2.getAge();
              }
          }
      });
      
      judges.forEach(e -> {
          System.out.println(e.getAge() + " " + e.getName());
          
      });
    
    }
    
}