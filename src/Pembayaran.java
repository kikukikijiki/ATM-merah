public class Pembayaran extends Transaction{
    private double amount; // amount to transfer
    private Keypad keypad; // reference to keypad
    private CashDispenser cashDispenser; // reference to cash dispenser
    
    public Pembayaran(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad, CashDispenser atmCashDispenser) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
        cashDispenser = atmCashDispenser;
    }

    @Override
    public void execute() {
       amount = displayMenuPembayaran();
       if (amount == CANCELED){
           super.getScreen().displayMessageLine("Canceling transaction...\n");
       } else{
            if(super.getBankDatabase().getAvailableBalance(super.getAccountNumber()) >= amount){    
                    super.getBankDatabase().debit(super.getAccountNumber(), amount); 
            }else
                System.out.println("\nBalance not enough.");
        } 
    }
    
    private int tiketKereta(){
        Screen screen = getScreen(); // get reference to screen
        
//        accountNumbert = 0001; // input account number
        
        Account akun = super.getBankDatabase().getAccount(0001);
            double input = Integer.MIN_VALUE;
             while(input == Integer.MIN_VALUE || input < 0){
               screen.displayMessage("\nMasukan jumlah yang akan dibayarkan " + 
                "CENTS (or 0 to cancel): ");
               if(keypad.getKeypad().hasNextInt()){
                   input = keypad.getKeypad().nextInt(); // input account number                    
               }else{
                   keypad.getKeypad().nextLine();
                   screen.displayMessageLine("\nInvalid Input\n");
               }
            }
             // check whether the user canceled or entered a valid amount
             if (input == CANCELED || input / 100 > super.getBankDatabase().getAvailableBalance(super.getAccountNumber())) {
                return CANCELED;
             }
             else {
                screen.displayMessage("\nAnda berhasil melakukan pembayaran tiket kereta.\n"
                     + "NOTE: Tiket yang sudah dibayarkan tidak dapat ditukarkan.");

                return (int) (input / 100); // return dollar amount
             }
}
    
    private int OVO(){
        Screen screen = getScreen(); // get reference to screen
        
//        accountNumbert = 0001; // input account number
        
        Account akun = super.getBankDatabase().getAccount(0002);
            double input = Integer.MIN_VALUE;
             while(input == Integer.MIN_VALUE || input < 0){
               screen.displayMessage("\nMasukan jumlah yang akan dibayarkan " + 
                "CENTS (or 0 to cancel): ");
               if(keypad.getKeypad().hasNextInt()){
                   input = keypad.getKeypad().nextInt(); // input account number                    
               }else{
                   keypad.getKeypad().nextLine();
                   screen.displayMessageLine("\nInvalid Input\n");
               }
            }
             // check whether the user canceled or entered a valid amount
             if (input == CANCELED || input/100 > super.getBankDatabase().getAvailableBalance(super.getAccountNumber())) {
                return CANCELED;
             }
             else {
                screen.displayMessage("\nSaldo OVO anda sudah ditambahkan.\n"
                     + "NOTE: Uang yang sudah dibayarkan tidak dapat dikembalikan.");

                return (int) (input / 100); // return dollar amount
             }
}
    
    private int listrik(){
        Screen screen = getScreen(); // get reference to screen
        
//        accountNumbert = 0001; // input account number
        
        Account akun = super.getBankDatabase().getAccount(0003);
            double input = Integer.MIN_VALUE;
             while(input == Integer.MIN_VALUE || input < 0){
               screen.displayMessage("\nMasukan jumlah yang akan dibayarkan " + 
                "CENTS (or 0 to cancel): ");
               if(keypad.getKeypad().hasNextInt()){
                   input = keypad.getKeypad().nextInt(); // input account number                    
               }else{
                   keypad.getKeypad().nextLine();
                   screen.displayMessageLine("\nInvalid Input\n");
               }
            }
             // check whether the user canceled or entered a valid amount
             if (input == CANCELED || input/100 > super.getBankDatabase().getAvailableBalance(super.getAccountNumber())) {
                return CANCELED;
             }
             else {
                screen.displayMessage("\nAnda berhasil melakukan pembayaran listrk.\n");

                return (int) (input / 100); // return dollar amount
             }
}
    
    private int paketInternet(){
        Screen screen = getScreen(); // get reference to screen
        
//        accountNumbert = 0001; // input account number
        
        Account akun = super.getBankDatabase().getAccount(0004);
            double input = Integer.MIN_VALUE;
             while(input == Integer.MIN_VALUE || input < 0){
               screen.displayMessage("\nMasukan jumlah yang akan dibayarkan " + 
                "CENTS (or 0 to cancel): ");
               if(keypad.getKeypad().hasNextInt()){
                   input = keypad.getKeypad().nextInt(); // input account number                    
               }else{
                   keypad.getKeypad().nextLine();
                   screen.displayMessageLine("\nInvalid Input\n");
               }
            }
             // check whether the user canceled or entered a valid amount
             if (input == CANCELED || input/100 > super.getBankDatabase().getAvailableBalance(super.getAccountNumber())) {
                return CANCELED;
             }
             else {
                screen.displayMessage("\nAnda berhasil melakukan pembelian paket internet.\n");

                return (int) (input / 100); // return dollar amount
             }
}
    
    private int asuransi(){
        Screen screen = getScreen(); // get reference to screen
        
//        accountNumbert = 0001; // input account number
        
        Account akun = super.getBankDatabase().getAccount(0004);
            double input = Integer.MIN_VALUE;
             while(input == Integer.MIN_VALUE || input < 0){
               screen.displayMessage("\nMasukan jumlah yang akan dibayarkan " + 
                "CENTS (or 0 to cancel): ");
               if(keypad.getKeypad().hasNextInt()){
                   input = keypad.getKeypad().nextInt(); // input account number                    
               }else{
                   keypad.getKeypad().nextLine();
                   screen.displayMessageLine("\nInvalid Input\n");
               }
            }
             // check whether the user canceled or entered a valid amount
             if (input == CANCELED || input/100 > super.getBankDatabase().getAvailableBalance(super.getAccountNumber())) {
                return CANCELED;
             }
             else {
                screen.displayMessage("\nAnda berhasil melakukan pembayaran asuransi.\n");

                return (int) (input / 100); // return dollar amount
             }
}
    private int hotel(){
        Screen screen = getScreen(); // get reference to screen
        
//        accountNumbert = 0001; // input account number
        
        Account akun = super.getBankDatabase().getAccount(0004);
            
             double input = Integer.MIN_VALUE;
             while(input == Integer.MIN_VALUE || input < 0){
               screen.displayMessage("\nMasukan jumlah yang akan dibayarkan " + 
                "CENTS (or 0 to cancel): ");
               if(keypad.getKeypad().hasNextInt()){
                   input = keypad.getKeypad().nextInt(); // input account number                    
               }else{
                   keypad.getKeypad().nextLine();
                   screen.displayMessageLine("\nInvalid Input\n");
               }
            }
             // check whether the user canceled or entered a valid amount
             if (input == CANCELED || input/100 > super.getBankDatabase().getAvailableBalance(super.getAccountNumber())) {
                return CANCELED;
             }
             else {
                screen.displayMessage("\nAnda berhasil melakukan pembayaran hotel.\n");

                return (int) (input / 100); // return dollar amount
             }
}
    
      private int displayMenuPembayaran() {
      double userChoice = 0;
      
      boolean userExited = false; // local variable to store return value

      Screen screen = getScreen(); // get screen reference

         // display the withdrawal menu
         screen.displayMessageLine("\nPayment Menu :");
         screen.displayMessageLine("1 - Tiket Kereta");
         screen.displayMessageLine("2 - OVO");
         screen.displayMessageLine("3 - Listrik");
         screen.displayMessageLine("4 - Paket Internet");
         screen.displayMessageLine("5 - Asuransi");
         screen.displayMessageLine("6 - Hotel");
         screen.displayMessageLine("0 - Cancel transaction");
         
         int input = Integer.MIN_VALUE;
         while(input == Integer.MIN_VALUE || input < 0 || input > 7){
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
            case 1: userChoice = tiketKereta(); break;
            case 2: userChoice = OVO(); break;
            case 3: userChoice = listrik(); break;
            case 4: userChoice = paketInternet(); break;
            case 5: userChoice = asuransi(); break;
            case 6: userChoice = hotel(); break;
            case CANCELED:
               userChoice = CANCELED; // save user's choice
               break;
            default: // the user did not enter a value from 1-6
               screen.displayMessageLine(
                  "\nInvalid selection. Try again.");
         } 
      // return withdrawal amount or CANCELED
      return (int) userChoice;
   }
} 
