public class Payment {
    private int userAccount;
    private double CreditCard;
    private double CreditHome;
    
    public Payment(int theuserAccount, double theCreditCard, double theCreditHome){
        userAccount = theuserAccount;
        CreditCard = theCreditCard;
        CreditHome = theCreditHome;
    }
    
    public double getCreditCard(){
        return CreditCard;
    }
    
    public double getCreditHome(){
        return CreditHome;
    }
    
    public int getAccountNumber(){
        return userAccount;
    }
    
    public void creditCCard(double amount){
        CreditCard -= amount;
    }
    
    public void creditCHome(double amount){
        CreditHome-= amount;
    }
    
}
