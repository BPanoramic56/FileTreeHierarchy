import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class C2100 {
    
    public static void main(String[] args){
        // Scanner s = new Scanner(System.in);
        // while (true)
        System.out.println(Solution(4,122421,10));
        System.out.println(Solution2 (4,122421,10));
    }

    // public static int Solution(int n){

    //     if (n < 1 || n > 50){
    //         throw new IllegalArgumentException();
    //     }

    //     int[] vowels = new int[5];
		
    //     Arrays.fill(vowels, 1); // word has to have at least one vowel
    //     int count = 5; // minimum possible value is 5 
		
    //     for(int i=1; i < n; i++){
		
    //         int nextValue = count;
    //         int previousValue = vowels[0];
    //         vowels[0] = count; 
			
    //         for(int v=1; v<5; v++){

    //             int hold = vowels[v];
    //             vowels[v] = vowels[v-1] - previousValue;
    //             previousValue = hold;
    //             nextValue += vowels[v];

    //         }
    //         count = nextValue;
    //     }
    //     return count;
    // } 
    public static double Solution(int a, int b, int t){
            return 0.5 * t * (a + b); 
    }
    public static int Solution2(int a, int b, int t){
            return (int) (t * (0.5*a + 0.5*b)); 
    }
}
