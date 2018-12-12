public class Transfer extends Transaction{
    private int amount; // amount to transfer
    private int accountNumbert;
    private Keypad keypad; // reference to keypad
    private CashDispenser cashDispenser; // reference to cash dispenser
    private final static int CANCELED = 0; // constant for cancel option

    public Transfer(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad, CashDispenser atmCashDispenser) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
        cashDispenser = atmCashDispenser;
    }

    @Override
    public void execute() {
       amount = tranferEx();
       if (amount == CANCELED){
           screen.displayMessageLine("Canceling transaction...\n");
       } else{
           if (cashDispenser.isSufficientCashAvailable(amount)){
               if(super.getBankDatabase().getAvailableBalance(super.getAccountNumber()) >= amount){
                   cashDispenser.dispenseCash(amount);
                   super.getBankDatabase().debit(super.getAccountNumber(), amount);
               }
               super.getBankDatabase().credit(accountNumbert, amount);
           }
       }
    }
    
    private int tranferEx(){
        Screen screen = getScreen(); // get reference to screen
        
        screen.displayMessage("\nPlease enter account number tujuan : ");
        accountNumbert = keypad.getInput(); // input account number
        
        Account akun = super.getBankDatabase().getAccount(accountNumbert);
        
        if(akun == null){
//            tranferEx();
            return CANCELED;
        }else{
            screen.displayMessage("\nPlease enter a tranfer amount in " + 
                "CENTS (or 0 to cancel): ");
             int input = keypad.getInput(); // receive input of deposit amount
             
             // check whether the user canceled or entered a valid amount
             if (input == CANCELED) {
                return CANCELED;
             }
             else {
                screen.displayMessage("\nAnda berhasil transfer.\n"
                     + "NOTE: Uang yang telah anda transfer tidak dapat dikembalikan");

                return input / 100; // return dollar amount
               }
        }
}
}
