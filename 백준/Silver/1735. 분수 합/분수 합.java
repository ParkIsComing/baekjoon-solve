import java.util.Scanner;

public class Main {
    
    public static int gcd(int a, int b) {
        if (b == 0)
			return a;
            
		return gcd(b, a % b);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();

        int c = sc.nextInt();
        int d = sc.nextInt();

        int num = a * d + b * c;
        int den = b * d;

        int g = gcd(num, den);
        num /= g;
        den /= g;

        System.out.println(num + " " + den);
    }
}
