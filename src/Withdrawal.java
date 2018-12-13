public class Withdrawal extends Transaction {
    private int amount; // amount to withdraw
    private Keypad keypad; // reference to keypad
    private CashDispenser cashDispenser; // reference to cash dispenser

    // constant corresponding to menu option to cancel
    private final static int CANCELED = 6;

    // Withdrawal constructor
    public Withdrawal(int userAccountNumber, Screen atmScreen, 
        BankDatabase atmBankDatabase, Keypad atmKeypad, 
        CashDispenser atmCashDispenser) {
        // initialize superclass variables
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
        cashDispenser = atmCashDispenser;
    }

    // perform transaction
    @Override
    public void execute() {
        amount = displayMenuOfAmounts();
        if(amount == CANCELED){
            System.out.println("Canceling Transaction...");
        }else{
            if(cashDispenser.isSufficientCashAvailable(amount)){
                if(super.getBankDatabase().getAvailableBalance(super.getAccountNumber()) >= amount){
                    cashDispenser.dispenseCash(amount);
                    super.getBankDatabase().debit(super.getAccountNumber(), amount);
                    System.out.println("");
                    System.out.println("Your cash has been dispensed. Please take your cash now.");
                }else{
                    System.out.println("Insufficient Balance in Your Account");
                }
            }else {
                System.out.println("Insufficient Cash In Dispenser");
            }
        }
    } 

    // display a menu of withdrawal amounts and the option to cancel;
    // return the chosen amount or 0 if the user chooses to cancel
    private int displayMenuOfAmounts() {
        int userChoice = 2; // local variable to store return value

        Screen screen = getScreen(); // get screen reference
      
        // array of amounts to correspond to menu numbers
        int[] amounts = {0, 20, 40, 60, 100, 200};

        // loop while no valid choice has been made
        while (userChoice == 2) {
            // display the withdrawal menu
            screen.displayMessageLine("\nWithdrawal Menu:");
            screen.displayMessageLine("1 - $20");
            screen.displayMessageLine("2 - $40");
            screen.displayMessageLine("3 - $60");
            screen.displayMessageLine("4 - $100");
            screen.displayMessageLine("5 - $200");
            screen.displayMessageLine("6 - Cancel transaction");
            
            int input = Integer.MIN_VALUE;
            while(input == Integer.MIN_VALUE || input < 0 || input > 6){
               screen.displayMessage("\nChoose Input: ");
               if(keypad.getKeypad().hasNextInt()){
                   input = keypad.getKeypad().nextInt(); // input account number                    
               }else{
                   keypad.getKeypad().nextLine();
                   screen.displayMessageLine("\nInvalid Input\n");
               }
            }
            // determine how to proceed based on the input value
            switch (input) {
                case 1:  
                case 2: // (i.e., chose option 1, 2, 3, 4 or 5), return the
                case 3: // corresponding amount from amounts array
                case 4:
                case 5:
                    userChoice = amounts[input]; // save user's choice
                    break;       
                case CANCELED: // the user chose to cancel
                    userChoice = CANCELED; // save user's choice
                    break;
                default: // the user did not enter a value from 1-6
                    screen.displayMessageLine("\nInvalid selection. Try again.");
            } 
        }
        return userChoice; // return withdrawal amount or CANCELED
    }
} 