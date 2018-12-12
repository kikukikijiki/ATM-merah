
import java.util.Calendar;

public class ATM implements iInputChoice{
    private boolean userAuthenticated; // whether user is authenticated
    private int currentAccountNumber; // current user's account number
    private Screen screen; // ATM's screen
    private Keypad keypad; // ATM's keypad
    private CashDispenser cashDispenser; // ATM's cash dispenser
    private DepositSlot atmDepositSlot;
    private BankDatabase bankDatabase; // account information database
    private PaymentDatabase paymentDatabase;
    private Stamptime time = new Stamptime();
    
    // no-argument ATM constructor initializes instance variables
    public ATM() {
        userAuthenticated = false; // user is not authenticated to start
        currentAccountNumber = 0; // no current account number to start
        screen = new Screen(); // create screen
        keypad = new Keypad(); // create keypad 
        cashDispenser = new CashDispenser(); // create cash dispenser
        bankDatabase = new BankDatabase(); // create acct info database
        paymentDatabase = new PaymentDatabase();
    }

    // start ATM 
    public void run() {
        // welcome and authenticate user; perform transactions
        while (true) {
            // loop while user is not yet authenticated
            while (!userAuthenticated) {
                screen.displayMessageLine("\nWelcome!");       
                authenticateUser(); // authenticate user
            } 
            performTransactions(); // user is now authenticated
            userAuthenticated = false; // reset before next ATM session
            currentAccountNumber = 0; // reset before next ATM session
            screen.displayMessageLine("\nThank you! Goodbye!");
        }
    }

    // attempts to authenticate user against database
    private void authenticateUser() {
        int count = 0;
        while(!userAuthenticated && count < 3){
            /*INI UNTUK VERIFIKASI APAKAH INTEGER ATAU BUKAN*/
            int accountNumber = Integer.MIN_VALUE;
            while(accountNumber == Integer.MIN_VALUE){
                screen.displayMessage("\nPlease enter your account number: ");
                if(keypad.getKeypad().hasNextInt()){
                    accountNumber = keypad.getKeypad().nextInt(); // input account number                    
                }else{
                    keypad.getKeypad().next();
                }
            }
            /*END*/
            
            screen.displayMessage("\nEnter your PIN: "); // prompt for PIN
            int pin = keypad.getInput(); // input PIN

            // set userAuthenticated to boolean value returned by database
            userAuthenticated = bankDatabase.authenticateUser(accountNumber, pin);

            // check whether authentication succeeded
            if (userAuthenticated) {
               currentAccountNumber = accountNumber; // save user's account #
            } 
            else {
               count++;
               screen.displayMessageLine("Invalid account number or PIN. Please try again.");
            }
        }
        if(count > 2){
            System.err.println("INVALID ACCOUNT NUMBER OR PIN FOR 3 TIME....\nYOUR CARD HAS BEEN BLOCKED\nSYSTEM EXIT");
            System.exit(0);
        }
     } 

    // display the main menu and perform transactions
    private void performTransactions() {
        // local variable to store transaction currently being processed
        Transaction currentTransaction = null;

        boolean userExited = false; // user has not chosen to exit

        // loop while user has not chosen option to exit system
        while (!userExited) {
            // show main menu and get user selection
            System.out.println("Current Date : "+ time.cal.get(Calendar.DATE) + "/" + (time.cal.get(Calendar.MONTH)+1 ) + "/" + time.cal.get(Calendar.YEAR));
            int mainMenuSelection = displayMainMenu();

            // decide how to proceed based on user's menu selection
            switch (mainMenuSelection) {
                // user chose to perform one of three transaction types
                case BALANCE_INQUIRY:
                case WITHDRAWAL:
                case DEPOSIT:
                case TRANSFER:
                case CHANGEPASS : 
                case INBOX :
                case PAYMENTCREDIT:
                case PAYMENTHOME:
                case PAYMENTINQUIRY:
                case HELP:
                case AIRCRAFT:
                case PULSA:
                case OTHER:                    
                    currentTransaction = createTransaction(mainMenuSelection);
                    currentTransaction.execute();
                    break;
                case EXIT: // user chose to terminate session
                   screen.displayMessageLine("\nExiting the system...");
                   userExited = true; // this ATM session should end
                   break;
                default: 
                    screen.displayMessageLine("\nYou did not enter a valid selection. Try again.");
                    break;
            }
        } 
    } 

   // display the main menu and return an input selection
    private int displayMainMenu() {
        screen.displayMessageLine("\nMain menu:");
        screen.displayMessageLine("1 - View my balance");
        screen.displayMessageLine("2 - Withdraw cash");
        screen.displayMessageLine("3 - Deposit funds");
        screen.displayMessageLine("4 - Transfer");
        screen.displayMessageLine("5 - Change Password");
        screen.displayMessageLine("6 - Inbox");
        screen.displayMessageLine("7 - Payment Credit Card");
        screen.displayMessageLine("8 - Payment Credit Home");
        screen.displayMessageLine("9 - View my Dept");
        screen.displayMessageLine("10 - Aircraft Ticket Payment");
        screen.displayMessageLine("11 - Pulsa");
        screen.displayMessageLine("12 - Other Transaction");
        screen.displayMessageLine("13 - Help");
        screen.displayMessageLine("14 - Exit\n");
        screen.displayMessage("Enter a choice: ");
        return keypad.getInput(); // return user's selection
    }
         
    private Transaction createTransaction(int type) {
        Transaction temp = null; 
          
        switch (type) {
            case BALANCE_INQUIRY: 
                temp = new BalanceInquiry(currentAccountNumber, screen, bankDatabase, keypad);
                break;
            case WITHDRAWAL:
                temp = new Withdrawal(currentAccountNumber,screen,bankDatabase,keypad,cashDispenser);
                break;
            case DEPOSIT:
                temp = new Deposit(currentAccountNumber, screen, bankDatabase, keypad, atmDepositSlot);
                break;
            case TRANSFER:
                temp = new Transfer(currentAccountNumber, screen, bankDatabase, keypad);
                break;
            case CHANGEPASS:
                temp = new ChangePass(currentAccountNumber, screen, bankDatabase, keypad);
                break;
            case INBOX:
                temp = new Inbox(currentAccountNumber, screen, bankDatabase, keypad);
                break;
            case PAYMENTCREDIT:
                temp = new PaymentCredit(currentAccountNumber,screen,bankDatabase, keypad, paymentDatabase);
                break;
            case PAYMENTHOME:
                temp = new PaymentHome(currentAccountNumber,screen,bankDatabase, keypad, paymentDatabase);
                break;
            case HELP:
                temp = new HelpandCallcenter(currentAccountNumber,screen,bankDatabase);
                break;
            case PAYMENTINQUIRY:
                temp = new PaymentInquiry(currentAccountNumber, screen, bankDatabase, paymentDatabase);
                break;
            case AIRCRAFT:
                temp = new AircraftPayment(currentAccountNumber, screen, bankDatabase, keypad);
                break;
            case PULSA:
                temp = new Pulsa(currentAccountNumber, screen, bankDatabase, keypad);
                break;
            case OTHER:
                temp = new Pembayaran(currentAccountNumber, screen, bankDatabase, keypad, cashDispenser);
                break;
        }
        return temp;
    } 
}
