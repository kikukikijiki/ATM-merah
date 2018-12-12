public class BalanceInquiry extends Transaction {
    private Keypad keypad; // reference to keypad
    private final static int IDR = 2;
    private final static int USD = 1;
    private Screen screen;
    // BalanceInquiry constructor
    
    public BalanceInquiry(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase,Keypad atmKeypad) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
        screen = atmScreen;
    } 
   
    public int PilihCurrency(){
        screen.displayMessageLine("\nChoose Your Currency :\n");
        screen.displayMessageLine("1. United States Dollar");
        screen.displayMessageLine("2. Indonesia Rupiah");
        screen.displayMessage("Choose your input : "); 
        int input = 0;
        try{
            input = keypad.getInput();
        }catch(Exception e){
//            System.err.println(e);
            input = 1;
//            e.printStackTrace();
        }
        BankDatabase bankDatabase = getBankDatabase();
        double availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
        // get the total balance for the account involved
        double totalBalance = bankDatabase.getTotalBalance(getAccountNumber());
        // display the balance information on the screen
        screen.displayMessageLine("\nBalance Information:");
            
        if(input == USD){
            screen.displayMessage(" - Available balance: "); 
            screen.displayDollarAmount(availableBalance);
            screen.displayMessage("\n - Total balance:     ");
            screen.displayDollarAmount(totalBalance);
            screen.displayMessageLine("");
        }      
        else if (input == IDR){
            screen.displayMessage(" - Saldo Tersedia "); 
            screen.displayRupiahAmount(availableBalance);
            screen.displayMessage("\n - Total Saldo:   ");
            screen.displayRupiahAmount(totalBalance);
            screen.displayMessageLine("");    
        }
        return input;
    }   
   
    @Override
    public void execute() {
        PilihCurrency();
    }

}



    