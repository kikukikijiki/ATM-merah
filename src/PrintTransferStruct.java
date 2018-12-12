
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrintTransferStruct {
    FileWriter printer;
    Calendar cal;
    
    
    public PrintTransferStruct(){
        try {
            cal = Calendar.getInstance();
            printer = new FileWriter("Transfer.txt");
            printer.write("Date : "+cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH)+1 ) + "/" + cal.get(Calendar.YEAR)+"|");
        } catch (IOException ex) {
            Logger.getLogger(PrintTransferStruct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void write(String text){
        try {
            printer.write(text);
        } catch (IOException ex) {
            Logger.getLogger(PrintTransferStruct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closePrinter(){
        try {
            printer.close();
        } catch (IOException ex) {
            Logger.getLogger(PrintTransferStruct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
