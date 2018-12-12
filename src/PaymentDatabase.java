public class PaymentDatabase {
    private Payment[] payment;
    
    public PaymentDatabase(){
        payment = new Payment[2];
        payment[0]= new Payment(12345,500.0,12000.0);
        payment[1] = new Payment(8765,250.0,8000.0);
    }
    public int getAccount(int userAccount){
       for(int i=0;i<payment.length;i++){
           if(userAccount==payment[i].getAccountNumber()){
               return i;
           }
       }
      return 0; 
    }
    
    public double getCreditCard(int userAccount){
        return payment[getAccount(userAccount)].getCreditCard();
    }
    
    public double getCreditHome(int userAccount){
        return payment[getAccount(userAccount)].getCreditHome();
    }
    
    public void creditCCard(int userAccount, double amount){
       payment[getAccount(userAccount)].creditCCard(amount);
    }
    
    public void creditCHome(int userAccount, double amount){
        payment[getAccount(userAccount)].creditCHome(amount);
    }
}
