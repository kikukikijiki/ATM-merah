import java.util.Scanner;

public class Transfer extends Transaction{
    private Keypad keypad; // reference to keypad
    private boolean cek=false;
    private int numberReceiver;
    private String Note;
    private PrintTransferStruct pr;
    public Transfer(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
        pr = new PrintTransferStruct();
    }

    @Override
    public void execute() {
        System.out.print("Masukan Nomer Akun yang akan ditransfer : ");
        numberReceiver = keypad.getInput();
        if(super.getBankDatabase().getAccount(numberReceiver) == null){
            System.out.print("Account Doesn't Exist");
        }else{
            double amount = displayTransfer();
            double availbalance = super.getBankDatabase().getAvailableBalance(super.getAccountNumber());
            if(availbalance>=amount)
            {
                super.getBankDatabase().debit(super.getAccountNumber(), amount);
                super.getBankDatabase().credit(numberReceiver, amount);
                pr.write("Sender : "+super.getAccountNumber()+"|");
                pr.write("Receiver : "+numberReceiver+"|");
                pr.write("Amount : " +amount);
                pr.closePrinter();
                super.getScreen().displayMessageLine("Your Transaction has been save to Transfer.txt");
                ceksendnote();
            }else{
                System.out.print("tidak bisa transfer");
            }
        }  
    }
    
    private int displayTransfer(){
        System.out.print("Masukan Jumlah Transfer : ");
        return  keypad.getInput();
    }
    
    public void ceksendnote(){
        super.getScreen().displayMessageLine("Do you want add Note?");
        super.getScreen().displayMessageLine("1. Yes");
        super.getScreen().displayMessageLine("2. No");
        super.getScreen().displayMessage("Input Choice : ");
        if(keypad.getInput() == 1){
            sendnote();
        }else{
            super.getScreen().displayMessageLine("Thank You, Have a nice day..");
        }
    }
    
    public void sendnote(){
        Account acc = super.getBankDatabase().getAccount(numberReceiver);
        Scanner scan = new Scanner(System.in);
        
        super.getScreen().displayMessage("Type Your Note : ");
        Note = scan.nextLine();
        acc.addNote(super.getAccountNumber(), Note);
        
    }
    
}
