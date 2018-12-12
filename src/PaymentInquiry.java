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
        screen.displayMessage("\nHutang Kartu Kredit : ");
        screen.displayDollarAmount(paymentDatabase.getCreditCard(userAccount));
        screen.displayMessage("\nHutnag Kredit Rumah : ");
        screen.displayDollarAmount(paymentDatabase.getCreditHome(userAccount));
        screen.displayMessageLine("");     
    }
}
