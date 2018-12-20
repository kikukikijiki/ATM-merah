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
    
    private double displayaircraftpayment(){
        
        Account acc = super.getBankDatabase().getAccount(super.getAccountNumber());
        double input = Double.MIN_VALUE;
        while(input == Double.MIN_VALUE){
            super.getScreen().displayMessage("\nEnter the amount of money to be paid for aircraft ticket in CENTS (or 0 to cancel): ");
            if(keypad.getKeypad().hasNextDouble()){
                input = keypad.getKeypad().nextDouble(); // input account number
                if(input >= 0)
                    return input/100;
            }else{
                keypad.getKeypad().nextLine();
                super.getScreen().displayMessageLine("\nInvalid Input for Account Number\n");
            }
        }
        // check whether the user canceled or entered a valid amount
        return input/100;
    }
    
}
