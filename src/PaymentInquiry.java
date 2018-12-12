public class PaymentInquiry extends Transaction{
    private final int userAccount;
    private PaymentDatabase paymentDatabase;
    
    public PaymentInquiry(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, PaymentDatabase atmPaymentDatabase) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
        userAccount = userAccountNumber;
        paymentDatabase = atmPaymentDatabase;
    }

    @Override
    public void execute() {
        Screen screen = getScreen();
        screen.displayMessage("\nCredit Card Debt : ");
        screen.displayDollarAmount(paymentDatabase.getCreditCard(userAccount));
        screen.displayMessage("\nHome Loan Debt : ");
        screen.displayDollarAmount(paymentDatabase.getCreditHome(userAccount));
        screen.displayMessageLine("");     
    }
}
