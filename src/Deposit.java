public class Deposit extends Transaction {
    private double amount; // amount to deposit
    private Keypad keypad; // reference to keypad
    private DepositSlot depositSlot; // reference to deposit slot
    
    // Deposit constructor
    public Deposit(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad, DepositSlot atmDepositSlot) {
        // initialize superclass variables
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
        depositSlot = atmDepositSlot;
    } 

    // perform transaction
    @Override
    public void execute() {
        amount = promptForDepositAmount();
        if(amount == CANCELED){
            System.out.println("Canceling Transaction...");
        }else{
            super.getBankDatabase().credit(super.getAccountNumber(), amount);
            System.out.print("\nPlease insert a deposit envelope containing ");
            super.getScreen().displayDollarAmount(amount);
            System.out.println("\n\nYour envelope has been received.\n "
                                + "NOTE: The money just deposited will not be available until "
                                + "we verify the amount of any enclosed cash and your checks clear.");
        }
    }

    // prompt user to enter a deposit amount in cents 
    private double promptForDepositAmount() {
        
        // display the prompt
        getScreen().displayMessage("\nPlease enter a deposit amount in " + 
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