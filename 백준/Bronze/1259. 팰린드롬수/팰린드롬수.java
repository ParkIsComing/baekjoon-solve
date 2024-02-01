import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        String input;
        while(sc.hasNextLine()) {
            input = sc.nextLine();
            if (input.equals("0")) {
                break;
            }
            
            String tmp = new StringBuilder(input).reverse().toString();
            if (input.equals(tmp)) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
            
        }
    }
}