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
public class aircraftpayment extends Transaction {
    private double amount;
    private Keypad keypad;
    private CashDispenser cashDispenser;
    private final static int CANCELED = 7;
    
    public aircraftpayment(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad, CashDispenser atmCashDispenser){
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
        cashDispenser = atmCashDispenser;
    }
    
    @Override
   public void execute() {
       amount = displayaircraftpayment();
       if (amount == CANCELED){
           screen.displayMessageLine("Canceling transaction...\n");
       } else{
           if(cashDispenser.isSufficientCashAvailable((int) amount) && amount != CANCELED){
                if(super.getBankDatabase().getAvailableBalance(super.getAccountNumber()) >= amount){    
                    cashDispenser.dispenseCash((int) amount);
                    super.getBankDatabase().debit(super.getAccountNumber(), amount);

                    System.out.println("\nYour cash has been dispensed. Please take your cash now.");

                }else
                    System.out.println("\nBalance not enough.");
            }else if(!cashDispenser.isSufficientCashAvailable((int) amount))
                System.out.println("\nCash not Available in cash dispenser.");
            else
                System.out.println("\nCanceling transaction...");
            
        }
 
   }
    private int displayaircraftpayment(){
        Screen screen = getScreen(); // get reference to screen
        
//        accountNumbert = 0001; // input account number
        
        Account acc = super.getBankDatabase().getAccount(0001);
            screen.displayMessage("\nEnter the amount of money to be paid " + 
                "CENTS (or 0 to cancel): ");
             double input = keypad.getInput(); // receive input of deposit amount
             
             // check whether the user canceled or entered a valid amount
             if (input == CANCELED) {
                return CANCELED;
             }
             else {
                screen.displayMessage("\nSuccess..\n"
                     + "NOTE: Tickets that have been paid cannot be exchanged.");

                return (int) (input / 100); // return dollar amount
             }
    }
   
}
