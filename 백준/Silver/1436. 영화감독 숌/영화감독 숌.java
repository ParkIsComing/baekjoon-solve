import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        int number = 666;
        int count = 1;
        
        while (count != n) {
            number++;
            if (String.valueOf(number).contains("666")) {
                count++;
            }
        }
        
        System.out.println(number);
    }
}