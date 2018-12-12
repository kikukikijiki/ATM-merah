/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atmapp;

/**
 *
 * @author Vaio
 */
public class HelpandCallcenter extends Transaction{

    public HelpandCallcenter(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
    }

   
    
    @Override
   public void execute() {
       
       
        System.out.println("==========================================================\n");
        System.out.println("                           HELP                           \n");
        System.out.println("==========================================================\n");
        System.out.println("- Press '1' to view my available balance and total balance\n");
        System.out.println("- Press '2' to do withdraw cash\n");
        System.out.println("- Press '3' to do deposit funds\n");
        System.out.println("- Press '4' to make a transfer\n");
        System.out.println("- Press '5' to pay for airline tickets\n");
        System.out.println("- Press '7' to end the transaction\n");
        
        System.out.println("==========================================================\n");
        System.out.println("                     CALL CENTER                          \n");
        System.out.println("==========================================================\n");
        System.out.println("     Contact the call center if there are complaints      \n");
        System.out.println("                        1500046                           \n");
        
       
            
   }
 
}
    
    

