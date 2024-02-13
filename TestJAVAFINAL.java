import java.util.Random;
public class TestJAVAFINAL{
    public static void main(String[] args){
        String greeting = "Hello!";
        String sameGreeting = "Hello";
            sameGreeting += "!";
        if(greeting == sameGreeting)
            System.out.println("1");
        if(greeting.equals(sameGreeting))
            System.out.println("2");

            Random rng = new Random();
            for (int i = 0; i < 20; i++)
                System.out.print((char) rng.nextInt(97,123));
            System.out.println();
            for (int i = 0; i < 20; i++){
                System.out.println(rng.nextInt(20));
            }
    }
}