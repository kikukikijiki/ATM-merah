public class Inbox extends Transaction{
    private Keypad keypad;
    public Inbox(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
    }

    @Override
    public void execute() {
        Account acc = super.getBankDatabase().getAccount(super.getAccountNumber());
        int maxsize = acc.getNote().size();
        int counter=0;
        if(acc.getNote().isEmpty()){
            super.getScreen().displayMessageLine("Inbox Empty..");
        }else{
            while(counter<maxsize){
                super.getScreen().displayMessage("Sender : ");
                super.getScreen().displayMessageLine(String.valueOf(acc.getNote().get(counter).getNumberSender()));
                super.getScreen().displayMessage("Note : ");
                super.getScreen().displayMessageLine(acc.getNote().get(counter).getNoteSender());
                super.getScreen().displayMessageLine("");
                counter++;
            }
        }
    }
    
}
