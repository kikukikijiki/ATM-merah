public class AircraftPayment extends Transaction {
    private double amount;
    private Keypad keypad;
    
    public AircraftPayment(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad){
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
    }
    
    @Override
    public void execute() {
        amount = displayaircraftpayment();
        if (amount == CANCELED){
            super.getScreen().displayMessageLine("Canceling transaction...\n");
        }else{
            if(super.getBankDatabase().getAvailableBalance(super.getAccountNumber()) >= amount){
                super.getBankDatabase().debit(super.getAccountNumber(), amount);
                super.getScreen().displayMessage("\nSuccess..\n NOTE: Tickets that have been paid cannot be exchanged.");
                }
            else
                System.out.println("\nBalance not enough.");
         }
    }
    
    private int displayaircraftpayment(){
        
        Account acc = super.getBankDatabase().getAccount(super.getAccountNumber());
        super.getScreen().displayMessage("\nEnter the amount of money to be paid for aircraft ticket in CENTS (or 0 to cancel): ");
        double input = keypad.getInput(); // receive input of deposit amount

        // check whether the user canceled or entered a valid amount
        if (input == CANCELED) {
            return CANCELED;
        }
        else {
            return (int) (input / 100); // return dollar amount
        }
    }
    
}
