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
        int input = Integer.MIN_VALUE;
        while(input == Integer.MIN_VALUE || input > 2 || input < 1){
            screen.displayMessage("Choose your input : "); 
            if(keypad.getKeypad().hasNextInt()){
                input = keypad.getKeypad().nextInt(); // input account number                    
            }else{
                keypad.getKeypad().nextLine();
                screen.displayMessageLine("\nInvalid Input\n");
            }
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
            screen.displayMessage(" - Available balance: "); 
            screen.displayRupiahAmount(availableBalance);
            screen.displayMessage("\n - Total balance:     ");
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



    