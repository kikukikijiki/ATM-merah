public class PaymentCredit extends Transaction{
    private int amount;
    private Keypad keypad;
    private int userAccount;
    private PaymentDatabase paymentDatabase;
    
    public PaymentCredit(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase ,Keypad thekeypad, PaymentDatabase thePaymentDatabase) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
        userAccount = userAccountNumber;
        keypad = thekeypad;
        paymentDatabase = thePaymentDatabase;
    }

    @Override
    public void execute() {
        Screen screen = super.getScreen();
        screen.displayMessage("\nJumlah Hutang Anda : ");
        screen.displayDollarAmount(paymentDatabase.getCreditCard(userAccount));
        screen.displayMessage("\nYang bisa anda gunakan : ");
        screen.displayDollarAmount(super.getBankDatabase().getAvailableBalance(userAccount));
        screen.displayMessage("\nBerapa yang akan anda Bayar : $");
        amount = keypad.getInput();  
        if(amount == 0.0){
            screen.displayMessage("\nTransaksi gagal");
        }else if(super.getBankDatabase().getAvailableBalance(userAccount) >= amount){
            if(amount > paymentDatabase.getCreditCard(userAccount)){
                screen.displayMessage("\nPembayaran hanya bisa dilakukan sejumlah atau lebihkecil dari jumlah hutang");
            }else{
                paymentDatabase.creditCCard(userAccount, amount);
                super.getBankDatabase().debit(userAccount, amount);
                screen.displayMessage("\nTransaksi Berhasil\n");
            }
        }else screen.displayMessage("\nUang melebihi batas yang dapat digunakan");
    }
    
 
   
}
