public class Pulsa extends Transaction{
    private int amount; // amount to withdraw
    private Keypad keypad; // reference to keypad
    private final static int CANCELED = 6;
    private Screen screen;
    
    public Pulsa(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
        screen = atmScreen;
    }

    @Override
    public void execute() {
        amount = displayMenuOfPulsa();
       
        if (amount == CANCELED) {
            screen.displayMessageLine("Canceling transaction...");
        }else{
            if(super.getBankDatabase().getAvailableBalance(super.getAccountNumber()) >= amount){
                super.getBankDatabase().debit(super.getAccountNumber(), amount);
                screen.displayMessageLine("Your Pulsa has been transfered");
                screen.displayMessageLine("Please check your handphone ");
            }else {
                screen.displayMessageLine("Insufficient Funds");                
            }
        }
    }

    private int displayMenuOfPulsa() {
        int userChoice = 0; // local variable to store return value

        // array of amounts to correspond to menu numbers
        int[] amounts = {0, 1, 2, 5, 10, 100};

        // loop while no valid choice has been made
        while (userChoice == 0) {
            // display the withdrawal menu
            screen.displayMessageLine("\nPulsa Menu:");
            screen.displayMessageLine("1 - $1");
            screen.displayMessageLine("2 - $2");
            screen.displayMessageLine("3 - $5");
            screen.displayMessageLine("4 - $10");
            screen.displayMessageLine("5 - $100");
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
                case 2:
                case 3:
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

