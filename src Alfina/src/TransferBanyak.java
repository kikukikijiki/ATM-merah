public class TransferBanyak extends Transaction{
    private int amount; // amount to transfer
    private int jmlOrang;
    private int[] accountNumbert;
    private int input;
    private int i;
    private Keypad keypad; // reference to keypad
    private Account akun;
    private CashDispenser cashDispenser; // reference to cash dispenser
    private final static int CANCELED = 0; // constant for cancel option
    private boolean transferLagi = false;

    public TransferBanyak(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad, CashDispenser atmCashDispenser) {
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
               super.getBankDatabase().credit(accountNumbert[i], amount);
           }
       }
    }
    
    private int tranferEx(){
        screen.displayMessageLine("Berapa jumlah no. rekening yang akan anda transfer : ");jmlOrang = keypad.getInput();
            
        Screen screen = getScreen(); // get reference to screen

        for (int i=0;i<jmlOrang;i++){
         screen.displayMessage("\nPlease enter account number tujuan : ");
            accountNumbert[i] = keypad.getInput(); // input account number
        
        akun = super.getBankDatabase().getAccount(accountNumbert[i]);
        }
        if(akun == null){
            return CANCELED;
        }else{
                screen.displayMessage("\nPlease enter a tranfer amount in " + 
                "CENTS (or 0 to cancel): ");
                input = keypad.getInput(); // receive input of deposit amount
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