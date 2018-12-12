/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

/**
 *
 * @author linux
 */
public class Pulsa extends Transaction{
    private int amount; // amount to withdraw
    private Keypad keypad; // reference to keypad
    private CashDispenser cashDispenser; // reference to cash dispenser
    private final static int CANCELED = 6;
    
    public Pulsa(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad, CashDispenser atmCashDispenser) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
        cashDispenser = atmCashDispenser;
    }

    @Override
    public void execute() {
        amount = displayMenuOfPulsa();
       
       if (amount == CANCELED) {
           screen.displayMessageLine("Canceling transaction...");
       }
       else{
           if (cashDispenser.isSufficientCashAvailable(amount)) {
               if(super.getBankDatabase().getAvailableBalance(super.getAccountNumber()) >= amount){
                   cashDispenser.dispenseCash(amount);
                   super.getBankDatabase().debit(super.getAccountNumber(), amount);
               }
           }
           screen.displayMessageLine("Your Pulsa has been transfered");
           screen.displayMessageLine("Please check your handphone ");
       }
    
    }
 private int displayMenuOfPulsa() {
      int userChoice = 0; // local variable to store return value

      Screen screen = getScreen(); // get screen reference
      
      // array of amounts to correspond to menu numbers
      int[] amounts = {0, 1, 2, 5, 10, 100};

      // loop while no valid choice has been made
      while (userChoice == 0) {
         // display the withdrawal menu
         screen.displayMessageLine("\nPulsa Menu:");
         screen.displayMessageLine("1 - $1");
         screen.displayMessageLine("2 - $2");
         screen.displayMessageLine("3 - $5");
         screen.displayMessageLine("4 - $10");
         screen.displayMessageLine("5 - $100");
         screen.displayMessageLine("6 - Cancel transaction");
         screen.displayMessage("\nChoose a Pulsa amount: ");

         int input = keypad.getInput(); // get user input through keypad

         // determine how to proceed based on the input value
         switch (input) {
            case 1:
            {
                userChoice = amounts[input];
                break;
            }// if the user chose a withdrawal amount 
            case 2:
            {
                userChoice = amounts[input];
                break;
            }// (i.e., chose option 1, 2, 3, 4 or 5), return the
            case 3:
            {
                userChoice = amounts[input];
                break;
            }// corresponding amount from amounts array
            case 4:
            {
                userChoice = amounts[input];
                break;
            }
            case 5:
               userChoice = amounts[input]; // save user's choice
               break;       
            case CANCELED: // the user chose to cancel
               userChoice = CANCELED; // save user's choice
               break;
            default: // the user did not enter a value from 1-6
               screen.displayMessageLine(
                  "\nInvalid selection. Try again.");
         } 
      } 

      return userChoice; // return withdrawal amount or CANCELED
   }
 
}

