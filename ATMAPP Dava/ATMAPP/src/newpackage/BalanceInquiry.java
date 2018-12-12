package newpackage;
/**
 *
 * @author Dava
 */

public class BalanceInquiry extends Transaction {
    private Keypad keypad; // reference to keypad
    private final static int IDR = 2;
    private final static int USD = 1;
    // BalanceInquiry constructor
    
    public BalanceInquiry(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase,Keypad atmKeypad) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
    } 
   
    public int PilihCurrency(){
        screen.displayMessageLine("\nChoose Your Currency :\n");
        screen.displayMessageLine("1. United States Dollar");
        screen.displayMessageLine("2. Indonesia Rupiah");
        screen.displayMessageLine("Choose your input :"); 
        int input = keypad.getInput();
        if(input == USD){
            BankDatabase bankDatabase = getBankDatabase();
            Screen screen = getScreen();
            double availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
            // get the total balance for the account involved
            double totalBalance = bankDatabase.getTotalBalance(getAccountNumber());
            // display the balance information on the screen
            screen.displayMessageLine("\nBalance Information:");
            screen.displayMessage(" - Available balance: "); 
            screen.displayDollarAmount(availableBalance);
            screen.displayMessage("\n - Total balance:     ");
            screen.displayDollarAmount(totalBalance);
        }      
        else if (input == IDR){
            BankDatabase bankDatabase = getBankDatabase();
            Screen screen = getScreen();
            double availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
            double totalBalance = bankDatabase.getTotalBalance(getAccountNumber());
            screen.displayMessageLine("\nInformasi Saldo");
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



    