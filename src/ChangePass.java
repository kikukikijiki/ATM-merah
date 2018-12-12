public class ChangePass extends Transaction{
    private Keypad keypad;
    private int oldPass;
    private int newPass;
    private boolean cek;
    
    public ChangePass(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
    }

    @Override
    public void execute() {
        Account acc = super.getBankDatabase().getAccount(super.getAccountNumber());
        while(!cek){
            super.getScreen().displayMessage("Insert Your Old Password : ");
            oldPass = keypad.getInput();
            if(oldPass == acc.getThePin()){
                cek=true;
            }else{
                super.getScreen().displayMessageLine("Our Password Not Match, Retype your password...");
            }
        }
        cek = false;
        while(!cek){
            super.getScreen().displayMessage("Insert Your New Password : ");
            newPass = keypad.getInput();
            if(newPass != oldPass){
                super.getScreen().displayMessage("Confirm Your New Password : ");
                if(keypad.getInput() == newPass){
                    super.getBankDatabase().ChangePass(super.getAccountNumber(), newPass);
                    cek = true;
                }else{
                    super.getScreen().displayMessageLine("Password not match, Retype Your Password..");
                }
            }else{
                super.getScreen().displayMessageLine("Dont Use The Old Password");
            }
        }
    }
    
}