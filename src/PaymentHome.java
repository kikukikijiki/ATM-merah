public class PaymentHome extends Transaction{
    private int amount;
    private Keypad keypad;
    private int userAccount;
    private PaymentDatabase paymentDatabase;
    
    public PaymentHome(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad thekeypad, PaymentDatabase thePaymentDatabase) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
        userAccount = userAccountNumber;
        keypad = thekeypad;
        paymentDatabase = thePaymentDatabase;
    }

    @Override
    public void execute() {
        Screen screen = super.getScreen();
        screen.displayMessage("\nYour debt amount : ");
        screen.displayDollarAmount(paymentDatabase.getCreditHome(userAccount));
        screen.displayMessage("\nWhat you can pay : ");
        screen.displayDollarAmount(super.getBankDatabase().getAvailableBalance(userAccount));
        screen.displayMessage("\nWhat will be used to pay : $");
        amount = keypad.getInput();
        if(amount<0){
             screen.displayMessage("\nThe value must not be smaller then 0\n");
        }else{
        if(amount == 0.0){
            screen.displayMessage("\nTransaction failed\n");
        }else if(super.getBankDatabase().getAvailableBalance(userAccount) >= amount){
            if(amount > paymentDatabase.getCreditHome(userAccount)){
                screen.displayMessage("\nPayments can only be made in a number or less than the amount of debt");
            }else{
                paymentDatabase.creditCHome(userAccount, amount);
                super.getBankDatabase().debit(userAccount, amount);
                screen.displayMessage("\nTransaction Success\n");
            }
        }else screen.displayMessage("\nThe value exceeds the limit\n");
    }
}
}