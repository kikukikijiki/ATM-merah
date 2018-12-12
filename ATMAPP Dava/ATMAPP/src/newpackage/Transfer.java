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
public class Transfer extends Transaction {
   private double amount; // amount to deposit
   private Keypad keypad; // reference to keypad
   private final static int CANCELED = 0; // constant for cancel option    
   private int accountNumber;
   private boolean userAuthenticated; // whether user is authenticated
   private int currentAccountNumber; // current user's account number
   private BankDatabase bankDatabase;
   
   
    public Transfer(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
       keypad = atmKeypad; 
    }

   
    @Override
    public void execute() {
        screen.displayMessage("\nPlease enter your account number: ");
        int accountNumber = keypad.getInput(); // input account number
        if(super.getBankDatabase().getAccount(accountNumber)!= null){
             amount = promptForDepositAmount();
             if(amount == CANCELED) {
                screen.displayMessage("Canceling transaction...");
            }
           else if(super.getBankDatabase().getAvailableBalance(super.getAccountNumber()) >= amount){
           super.getBankDatabase().debit(super.getAccountNumber(), amount);
           super.getBankDatabase().credit (accountNumber, amount);
           screen.displayMessage("Please insert a transfer amount : ");
           super.getScreen().displayDollarAmount(amount);
           screen.displayMessage("to Account : " + accountNumber + " Success");
            }
             else{
           screen.displayMessage("Insufficient Balance In Your Account");
            }
        }
       
    }
   
    private double promptForDepositAmount() {
      Screen screen = getScreen(); // get reference to screen

      // display the prompt
      screen.displayMessage("\nPlease enter a deposit amount in " + 
         "CENTS (or 0 to cancel): ");
      int input = keypad.getInput(); // receive input of deposit amount
      
      // check whether the user canceled or entered a valid amount
      if (input == CANCELED) {
         return CANCELED;
      }
      else {
         return (double) input / 100; // return dollar amount
      }
   }
    
}