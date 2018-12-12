public class FormNote {

    private int numberSender;
    private String noteSender;
    
    public FormNote(int number, String note){
        numberSender = number;
        noteSender = note;
    }
    
    public int getNumberSender() {
        return numberSender;
    }

    public void setNumberSender(int numberSender) {
        this.numberSender = numberSender;
    }

    public String getNoteSender() {
        return noteSender;
    }

    public void setNoteSender(String noteSender) {
        this.noteSender = noteSender;
    }
}