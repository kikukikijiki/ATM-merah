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
            int oldPIN = Integer.MIN_VALUE;
            while(oldPIN == Integer.MIN_VALUE){
                super.getScreen().displayMessage("Please enter your old PIN: ");
                if(keypad.getKeypad().hasNextInt()){
                    oldPIN = keypad.getKeypad().nextInt(); // input account number                    
                }else{
                    keypad.getKeypad().nextLine();
                    super.getScreen().displayMessageLine("\nInvalid Input for Old PIN\n");
                }
            }
            if(oldPass == acc.getThePin()){
                cek=true;
            }else{
                super.getScreen().displayMessageLine("Your Password Not Match, Retype your password...");
            }
        }
        cek = false;
        while(!cek){
            int newPIN = Integer.MIN_VALUE;
            while(newPIN == Integer.MIN_VALUE){
                super.getScreen().displayMessage("Please enter your new PIN: ");
                if(keypad.getKeypad().hasNextInt()){
                    newPIN = keypad.getKeypad().nextInt(); // input account number                    
                }else{
                    keypad.getKeypad().nextLine();
                    super.getScreen().displayMessageLine("\nInvalid Input for New PIN\n");
                }
            }
            if(newPass != oldPass){
                int verPIN = Integer.MIN_VALUE;
                while(verPIN == Integer.MIN_VALUE){
                    super.getScreen().displayMessage("Confirm your new PIN: ");
                    if(keypad.getKeypad().hasNextInt()){
                        verPIN = keypad.getKeypad().nextInt(); // input account number                    
                    }else{
                        keypad.getKeypad().nextLine();
                        super.getScreen().displayMessageLine("\nInvalid Input for PIN\n");
                    }
                }
                if(verPIN == newPass){
                    super.getBankDatabase().ChangePass(super.getAccountNumber(), newPass);
                    cek = true;
                }else{
                    super.getScreen().displayMessageLine("PIN not match, Retype Your PIN..");
                }
            }else{
                super.getScreen().displayMessageLine("Don't Use The Old PIN");
            }
        }
    }
    
}