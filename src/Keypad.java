import java.util.Scanner;

public class Keypad {
    private Scanner input; // reads data from the command line
                         
    public Keypad() {
        input = new Scanner(System.in);    
    } 

    public int getInput() {
        return input.nextInt(); // user enters an integer
    }
    
    public Scanner getKeypad() {
        return input; // user enters an integer
    }
    
    public boolean isInteger(){
        return input.hasNextInt();
    }
    
    public String tonext(){
        return input.next();
    }
} 