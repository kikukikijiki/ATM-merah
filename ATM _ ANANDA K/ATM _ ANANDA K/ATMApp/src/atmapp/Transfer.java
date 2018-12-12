/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atmapp;

/**
 *
 * @author Vaio
 */
public class Transfer extends Transaction{
   private double amount; // amount to deposit
   private Keypad keypad; // reference to keypad
   private DepositSlot depositSlot; // reference to deposit slot
   private final static int CANCELED = 0; // constant for cancel option
   private CashDispenser cashDispenser;
   private int AccountNumber;

   // Deposit constructor
   public Transfer(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad) {

      // initialize superclass variables
      super(userAccountNumber, atmScreen, atmBankDatabase);
      keypad = atmKeypad;
      
   } 

   // perform transaction
   @Override
   public void execute() {
      
       
       amount = promptForDepositAmount();
       
       
       
       if(amount == CANCELED){
           screen.displayMessage("Canceling Transcation..");
       }else{
          if(cashDispenser.isSufficientCashAvailable((int) amount)){
               if(super.getBankDatabase().getAvailableBalance(super.getAccountNumber()) >= amount)
               {
                   cashDispenser.dispenseCash((int) amount);
                   super.getBankDatabase().debit(super.getAccountNumber(), amount);
                  
               }
                   super.getBankDatabase().credit(AccountNumber, amount);   
           }
          System.out.println("Your cash has been dispensed. Please take your cash now.");
       }
 
   }
   
    private double promptForDepositAmount() {
        Screen screen = getScreen(); // get reference to screen
        System.out.print("Enter the account number that will be transferred : ");
        AccountNumber = keypad.getInput();
        Account NumberRek = getBankDatabase().getAccount(AccountNumber);
        
        

     if (NumberRek != null) {
            screen.displayMessage("\nPlease enter a deposit amount in " + 
         "CENTS (or 0 to cancel): ");
        int input = keypad.getInput(); // receive input of deposit amount
      
      // check whether the user canceled or entered a valid amount
         if (input == CANCELED) {
             return CANCELED;
            }else {
            return (double) input / 100; // return dollar amount
             }
     }else{
            screen.displayMessageLine("Account Number Doesnt Exist..");
            return CANCELED;
        } 
   }
}
         

      

 
   
    

