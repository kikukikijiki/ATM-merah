public class BankDatabase {
   private Account[] accounts; // array of Accounts
   
   public BankDatabase() {
      accounts = new Account[7]; // just 2 accounts for testing
      accounts[0] = new Account(1234, 4321, 1000.0, 1200.0);
      accounts[1] = new Account(8765, 5678, 200.0, 200.0); 
      accounts[2] = new Account(2345, 5432, 1200.0, 1200.0);
      accounts[3] = new Account(0001, 1000, 1200.0, 1400.0);
      accounts[4] = new Account(0002, 2000, 2200.0, 1400.0);
      accounts[5] = new Account(0003, 3000, 1000.0, 1200.0);
      accounts[6] = new Account(0004, 4000, 900.0, 1100.0);
   }
   
   Account getAccount(int accountNumber) {
       int i;
       for(i=0;i<2;i++){
           if(accountNumber == accounts[i].getAccountNumber()){
               return accounts[i];
           }
       }
       
       return null; // if no matching account was found, return null
   } 

   public boolean authenticateUser(int userAccountNumber, int userPIN) {
      // attempt to retrieve the account with the account number
      Account userAccount = getAccount(userAccountNumber);

      // if account exists, return result of Account method validatePIN
      if (userAccount != null) {
         return userAccount.validatePIN(userPIN);
      }
      else {
         return false; // account number not found, so return false
      }
   } 

   public double getAvailableBalance(int userAccountNumber) {
      return getAccount(userAccountNumber).getAvailableBalance();
   } 

   public double getTotalBalance(int userAccountNumber) {
      return getAccount(userAccountNumber).getTotalBalance();
   } 

   public void credit(int userAccountNumber, double amount) {
      getAccount(userAccountNumber).credit(amount);
   }

   public void debit(int userAccountNumber, double amount) {
      getAccount(userAccountNumber).debit(amount);
   } 

    void tranferuang(int accountNumber, double amount, int accountNumbert) {
        getAccount(accountNumber).debit(amount);
    }
} 